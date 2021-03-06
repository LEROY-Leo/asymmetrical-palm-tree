package com.example.applicationleroyleo.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.applicationleroyleo.R
import com.example.applicationleroyleo.data.LocalPreferences
import com.example.applicationleroyleo.ui.home.HomeActivity

// this class is used to display the home activity
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    // switch to another activity after a timer
    override fun onResume() {
        super.onResume()
        LocalPreferences.getInstance(this).clearHistory()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(HomeActivity.getStartIntent(this))
            finish()
        },3000)
    }
}