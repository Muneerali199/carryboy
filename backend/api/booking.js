const { createClient } = require('@supabase/supabase-js');

// Initialize Supabase Client
const supabaseUrl = process.env.NEXT_PUBLIC_SUPABASE_URL || 'https://bvzfewiwextobtpjhuzl.supabase.co';
const supabaseKey = process.env.NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY || 'sb_publishable_6_AsR6wXy-07vqq_S9UJGw_vN_wt-Hd';
const supabase = createClient(supabaseUrl, supabaseKey);

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

    console.log(`[Vercel Backend] Processing new booking for Supabase from hirer: ${hirerId}`);

    // Insert data into Supabase "bookings" table
    const { data, error } = await supabase
      .from('bookings')
      .insert([
        {
          hirer_id: hirerId,
          worker_id: workerId || null,
          pickup_lat: pickupLat || 0.0,
          pickup_lon: pickupLon || 0.0,
          price: price || 0.0,
          status: 'PENDING'
          // created_at is automatically handled by Supabase Postgres default values
        }
      ])
      .select();

    if (error) {
      console.error("Supabase Insertion Error:", error);
      return res.status(500).json({ error: 'Database Error', details: error.message });
    }

    return res.status(200).json({ 
      success: true, 
      message: "Booking created successfully in Supabase",
      booking: data[0]
    });

  } catch (error) {
    console.error("Error creating booking:", error);
    return res.status(500).json({ error: 'Internal Server Error', details: error.message });
  }
};
