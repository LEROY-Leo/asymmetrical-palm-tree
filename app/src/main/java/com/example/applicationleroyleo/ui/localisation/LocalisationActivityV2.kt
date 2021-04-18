package com.example.applicationleroyleo.ui.localisation

import android.Manifest
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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.example.applicationleroyleo.BuildConfig
import com.example.applicationleroyleo.R
import com.example.applicationleroyleo.databinding.ActivityLocalisationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class LocalisationActivityV2 : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityLocalisationBinding  // <-- Référence à notre ViewBinding
    val PERMISSION_REQUEST_LOCATION = 9999

    companion object {
        fun  getStartIntent(context: Context): Intent {
            return Intent(context, LocalisationActivityV2::class.java)
        }
    }
/*
    @SuppressWarnings("deprecation") public static Boolean isLocationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // This is a new method provided in API 28 LocationManager
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return lm.isLocationEnabled();
        }
        else {
            // This was deprecated in API 28 int
            mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);
            return (mode != Settings.Secure.LOCATION_MODE_OFF);
        }
    }

 */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localisation)

        // --> Indique que l'on utilise le ViewBinding
        binding = ActivityLocalisationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.buttonLocalisationDifference.setOnClickListener {
            // Click sur le Button nommé « Button » dans notre Layout.
            Toast.makeText(this, ("Appui bouton"), Toast.LENGTH_SHORT).show()
            requestPermission()
        }

    }

    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (!hasPermission()) {
            Toast.makeText(this, ("Permission NOK"), Toast.LENGTH_LONG).show()

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_LOCATION)
        } else {
            Toast.makeText(this, ("Permission OK"), Toast.LENGTH_LONG).show()
            getLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission obtenue, Nous continuons la suite de la logique.
                    Toast.makeText(this, ("In onRequestPermission..."), Toast.LENGTH_LONG).show()
                    getLocation()

                } else {
                    // TODO
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
                val locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager?
                locationManager?.run {
                    //Toast.makeText(this@MainActivity2, ("in getLocation"), Toast.LENGTH_SHORT).show()
                    locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)?.run {
                        geoCode(this)
                    }
                }
            }
        }

    /*
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (hasPermission()) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token)
                    .addOnSuccessListener { geoCode(it) }
                    .addOnFailureListener {
                        // Remplacer par un vrai bon message
                        Toast.makeText(this, "Localisation impossible", Toast.LENGTH_SHORT).show()
                    }
        }
    }

     */


    @SuppressLint("ResourceType")
    private fun geoCode(location: Location){
        val geocoder = Geocoder(this, Locale.getDefault())
        val results = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        val locationText = findViewById<TextView>(R.string.text_phone_localisation)
        Toast.makeText(this, ("In geoCode"), Toast.LENGTH_SHORT).show()

        if (results.isNotEmpty()) {
            //Toast.makeText(this, ("In geoCode"), Toast.LENGTH_LONG).show()
            locationText.text = results[0].getAddressLine(0)


        }
    }

}