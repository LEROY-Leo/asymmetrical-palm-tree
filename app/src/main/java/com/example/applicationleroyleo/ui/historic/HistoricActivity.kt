package com.example.applicationleroyleo.ui.historic

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationleroyleo.R
import com.example.applicationleroyleo.data.LocalPreferences
import com.example.applicationleroyleo.databinding.ActivityHistoricBinding
import com.example.applicationleroyleo.ui.historic.adapter.HistoricItemAdapter
import com.example.applicationleroyleo.ui.home.HomeActivity

// this class is used to display the historic activity
class HistoricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricBinding

    companion object {
        fun  getStartIntent(context: Context): Intent {
            return Intent(context, HistoricActivity::class.java)
        }
    }

    // activate the return action in the toolbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    // link the button to their actions and create the rv of the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)

        binding = ActivityHistoricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // clear the rv historic and reload the activity
        binding.buttonClearHisto.setOnClickListener {
            LocalPreferences.getInstance(this).clearHistory()
            finish()
            overridePendingTransition(0, 0);
            startActivity(getStartIntent(this))
            overridePendingTransition(0, 0);
        }

        // setup the activity toolbar
        supportActionBar?.apply {
            setTitle(getString(R.string.toolbar_title_historic))
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // create the rv and link its data
        var rv = findViewById<RecyclerView>(R.id.rvHistoric)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HistoricItemAdapter((LocalPreferences.getInstance(this).getHistory()))
    }
}