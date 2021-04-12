package com.example.applicationleroyleo.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applicationleroyleo.databinding.ActivityHomeBinding
import com.example.applicationleroyleo.databinding.ActivityMainBinding
import com.example.applicationleroyleo.historic.HistoricActivity
import com.example.applicationleroyleo.localisation.LocalisationActivity
import com.example.applicationleroyleo.settings.SettingsActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding // <-- Référence à notre ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // L'ensemble de nos références sont disponibles directement dans « binding »
        binding.buttonHistoric.setOnClickListener {
            // Click sur le Button nommé « Button » dans notre Layout.
            val historicIntent : Intent = Intent(this, HistoricActivity::class.java)
            startActivity(historicIntent)
        }

        binding.buttonLocalisation.setOnClickListener {
            // Click sur le Button nommé « Button » dans notre Layout.
            val localisationIntent : Intent = Intent(this, LocalisationActivity::class.java)
            startActivity(localisationIntent)
        }

        binding.buttonSettings.setOnClickListener {
            // Click sur le Button nommé « Button » dans notre Layout.
            val settingsIntent : Intent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }

    }

    companion object {
        fun  getStartIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}