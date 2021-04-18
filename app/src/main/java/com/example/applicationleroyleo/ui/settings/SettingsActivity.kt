package com.example.applicationleroyleo.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationleroyleo.R
import com.example.applicationleroyleo.data.SettingsItem
import com.example.applicationleroyleo.ui.settings.adapter.SettingsItemAdapter

class SettingsActivity : AppCompatActivity() {

    companion object {
        fun  getStartIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.apply {
            setTitle(getString(R.string.toolbar_title_settings_menu))
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        var rv = findViewById<RecyclerView>(R.id.rvSettings)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = SettingsItemAdapter(arrayOf(

                //ajouter les params de l'application genre activer loc, supprimmer histo etc.
                SettingsItem(getString(R.string.item_name_application_settings), R.drawable.ic_baseline_settings_24) {
                    val intent1 = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val uri1 = Uri.fromParts("package", packageName, null)
                    intent1.data = uri1
                    startActivity(intent1)
                },

                SettingsItem(getString(R.string.item_name_phone_localisation), R.drawable.ic_baseline_not_listed_location_24) {
                    val viewIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(viewIntent)
                },

                SettingsItem(getString(R.string.item_name_email), R.drawable.ic_baseline_email_24) {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto: Example@gmail.com")
                    intent.putExtra(Intent.EXTRA_EMAIL, "test")
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World")
                    startActivity(intent)
                },

                SettingsItem(getString(R.string.item_name_maps), R.drawable.ic_baseline_location_on_24) {
                    // TO DO : adresse drôle à mettre par défaut
                    val gmmIntentUri = Uri.parse("https://www.google.fr/maps/@50.7786889,2.808517,3a,75y,114.84h,91.8t/data=!3m6!1e1!3m4!1suovDnI3sf1XkRJCZ0E7V_A!2e0!7i16384!8i8192")
                    val mapIntent: Intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    startActivity(mapIntent)
                },

                SettingsItem(getString(R.string.item_name_site_eseo), R.drawable.logo) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://eseo.fr")));
                },

                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },

                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
            // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                SettingsItem("Autre", R.drawable.ic_baseline_link_off_24) {
                    // Action au clique
                },
                // TODO : mettre la chaine du voxytech gamin
                SettingsItem("Chaine de Voxitech", R.drawable.ic_baseline_videogame_asset_24) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCYYE059OudTZsYxXrbfwBuw")));

                }

        ))

    }
}