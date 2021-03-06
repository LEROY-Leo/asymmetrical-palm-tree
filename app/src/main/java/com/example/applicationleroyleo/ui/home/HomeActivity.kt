package com.example.applicationleroyleo.ui.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.applicationleroyleo.R
import com.example.applicationleroyleo.data.LocalPreferences
import com.example.applicationleroyleo.databinding.ActivityHomeBinding
import com.example.applicationleroyleo.ui.historic.HistoricActivity
import com.example.applicationleroyleo.ui.localisation.LocalisationActivity
import com.example.applicationleroyleo.ui.settings.SettingsActivity

// this class is used to display the home activity
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    companion object {
        fun  getStartIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    // link the button to their actions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // launch the historic activity, if the historic is not null
        binding.buttonHistoric.setOnClickListener {
            if (!LocalPreferences.getInstance(this).getHistory().isNullOrEmpty()) {
                val historicIntent : Intent = Intent(this, HistoricActivity::class.java)
                startActivity(historicIntent)
            }

            else {
                Toast.makeText(this, (getString(R.string.text_historic_empty)), Toast.LENGTH_LONG).show()
            }
        }

        // launch the localisation activity
        binding.buttonLocalisation.setOnClickListener {
            val localisationIntent : Intent = Intent(this, LocalisationActivity::class.java)
            startActivity(localisationIntent)
        }

        // launch the settings activity
        binding.buttonSettings.setOnClickListener {
            val settingsIntent : Intent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }
    }
}