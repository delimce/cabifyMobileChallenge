package com.delimce.cabifymobilechallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.delimce.cabifymobilechallenge.R
import com.delimce.cabifymobilechallenge.ui.fragments.ProductFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val catalog = ProductFragment()
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.mainContainer, catalog).commit()

    }
}
