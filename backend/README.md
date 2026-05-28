# CarryBoy Vercel Backend

This is the free Node.js backend alternative for Carryboy, ready to be hosted on Vercel or Netlify.

### How this works
Firebase Cloud Functions (background triggers) require a paid Blaze plan. Vercel allows you to host Serverless Node.js APIs (HTTP triggers) for **100% Free**.

Instead of writing to Firestore directly and having a background function trigger, your Android app can make a standard POST request to this Vercel API. This API then handles the logic (like sending notifications) and securely writes to Firestore using `firebase-admin`.

### How to Deploy to Vercel (Free)

1. Push this `carryboy` folder to a GitHub repository.
2. Go to [vercel.com](https://vercel.com/) and log in with GitHub.
3. Click **Add New -> Project** and import your Carryboy repository.
4. Set the **Framework Preset** to `Other`.
5. Set the **Root Directory** to `backend`.
6. Go to **Environment Variables** in Vercel before clicking deploy:
   - You need to generate a Firebase Service Account Key.
   - Go to Firebase Console -> Project Settings -> Service Accounts -> Generate New Private Key.
   - Copy the entire JSON content from the downloaded file.
   - In Vercel, add a new Environment Variable named `FIREBASE_SERVICE_ACCOUNT`.
   - Paste the JSON content as the value.
7. Click **Deploy**.

Once deployed, you will get a URL like `https://carryboy-backend.vercel.app/booking`. You can call this URL from your Android app using Retrofit/OkHttp to handle heavy backend tasks for free!
