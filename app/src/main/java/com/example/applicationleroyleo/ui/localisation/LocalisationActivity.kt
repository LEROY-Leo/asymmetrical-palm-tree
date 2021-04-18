package com.example.applicationleroyleo.ui.localisation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.afollestad.materialdialogs.BuildConfig
import com.afollestad.materialdialogs.MaterialDialog
import com.example.applicationleroyleo.R
import com.example.applicationleroyleo.data.LocalPreferences
import com.example.applicationleroyleo.databinding.ActivityLocalisationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*


class LocalisationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityLocalisationBinding
    val PERMISSION_REQUEST_LOCATION = 9999

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LocalisationActivity::class.java)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localisation)

        // set the toolbar title for the location activity
        supportActionBar?.apply {
            setTitle(getString(R.string.toolbar_title_distance))
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding = ActivityLocalisationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // this is used for the localisation action
        binding.buttonLocalisationDifference.setOnClickListener {
            requestPermission()
        }
    }


    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (!hasPermission()) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_LOCATION)
        } else {
            getLocation()
        }
    }

    // PermissionResult action with a dialog
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    getLocation()

                } else {
                    // TODO : handle the acceptance case
                    // Permission non accepté, expliqué ici via une activité ou une dialog pourquoi nous avons besoin de la permission
                    //faire un bouton positif

                    MaterialDialog(this).show {
                        title(R.string.dialog_title)
                        message(R.string.dialog_message)
                        positiveButton(R.string.dialog_accept) { dialog ->
                            startActivity(
                                    Intent(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                                    )
                            )
                        }
                        negativeButton(R.string.dialog_decline)
                    }
                }
                return
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (hasPermission()) {
            val locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager?.run {
                locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)?.run {
                    geoCode(this)
                }
            }
        }
    }

    private fun geoCode(location: Location){
        val geocoder = Geocoder(this, Locale.getDefault())
        val results = geocoder.getFromLocation(location.latitude, location.longitude, 1)
/*
        if (results.isEmpty()) {
            results[0].setAddressLine(0, "Pas d'adresse précise à cette localisation")
        }

 */

        // set the ESEO (Angers) location
        val eseo_loc = Location("")
        eseo_loc.latitude = 47.49305270909435
        eseo_loc.longitude = -0.5513441003661954

        if (!results.isEmpty()) {

            // display the phone localisation adress
            val realLocationText = findViewById<TextView>(R.id.text_real_loc)
            val stringAdresse = results[0].getAddressLine(0).split(",").toList()
            realLocationText.text = (stringAdresse[0] + "\n" + stringAdresse[1] + "," + stringAdresse[2])

            // display the distance between the phone location and ESEO
            var distanceTo = ((eseo_loc.distanceTo(location)) / 1000).toInt()
            val distanceToText = findViewById<TextView>(R.id.text_localisation)
            distanceToText.text = (getString(R.string.localisation_text_first_part) + " " + distanceTo.toString() + " km " + getString(R.string.localisation_text_second_part))

            // add the distance to the historic
            LocalPreferences.getInstance(this).addToHistory(results[0].getAddressLine(0))
            }
    }

}
