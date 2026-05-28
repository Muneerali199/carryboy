const admin = require('firebase-admin');

// Initialize Firebase Admin securely using Environment Variables in Vercel
if (!admin.apps.length) {
  try {
    if (process.env.FIREBASE_SERVICE_ACCOUNT) {
      const serviceAccount = JSON.parse(process.env.FIREBASE_SERVICE_ACCOUNT);
      admin.initializeApp({
        credential: admin.credential.cert(serviceAccount)
      });
    } else {
      console.warn("FIREBASE_SERVICE_ACCOUNT env variable is missing!");
      // Fallback for local testing if needed, though Vercel needs the env var
      admin.initializeApp();
    }
  } catch (error) {
    console.error("Firebase Admin initialization error", error);
  }
}

module.exports = async (req, res) => {
  // CORS Headers
  res.setHeader('Access-Control-Allow-Credentials', true);
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET,OPTIONS,PATCH,DELETE,POST,PUT');
  res.setHeader('Access-Control-Allow-Headers', 'X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version');

  if (req.method === 'OPTIONS') {
    res.status(200).end();
    return;
  }

  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method Not Allowed. Use POST.' });
  }

  try {
    const { hirerId, workerId, pickupLat, pickupLon, price } = req.body;
    
    if (!hirerId) {
      return res.status(400).json({ error: 'hirerId is required' });
    }

    const db = admin.firestore();
    
    // Server-side logic replacing the Firebase Cloud Function
    console.log(`[Vercel Backend] Processing new booking from hirer: ${hirerId}`);
    
    // 1. You can add logic here to send FCM Push Notifications to the worker!
    if (workerId) {
       console.log(`Notification trigger: Informing worker ${workerId} about booking.`);
    }

    // 2. Save data to Firestore securely from the Vercel backend
    const docRef = db.collection('bookings').doc();
    const bookingData = {
        bookingId: docRef.id,
        hirerId,
        workerId: workerId || null,
        pickupLat: pickupLat || 0.0,
        pickupLon: pickupLon || 0.0,
        price: price || 0.0,
        status: "PENDING",
        createdAt: admin.firestore.FieldValue.serverTimestamp()
    };

    await docRef.set(bookingData);

    return res.status(200).json({ 
      success: true, 
      message: "Booking created successfully via Vercel",
      bookingId: docRef.id,
      data: bookingData
    });

  } catch (error) {
    console.error("Error creating booking:", error);
    return res.status(500).json({ error: 'Internal Server Error', details: error.message });
  }
};
