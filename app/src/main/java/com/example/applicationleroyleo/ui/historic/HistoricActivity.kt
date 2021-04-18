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

class HistoricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricBinding // <-- Référence à notre ViewBinding

    companion object {
        fun  getStartIntent(context: Context): Intent {
            return Intent(context, HistoricActivity::class.java)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

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

        supportActionBar?.apply {
            setTitle(getString(R.string.toolbar_title_historic))
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        var rv = findViewById<RecyclerView>(R.id.rvHistoric)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HistoricItemAdapter((LocalPreferences.getInstance(this).getHistory()))
    }
}