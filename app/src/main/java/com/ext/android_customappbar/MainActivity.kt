package com.ext.android_customappbar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.customappbar.components.PrimaryAppBarView
import com.ext.customappbar.components.SecondaryAppBarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val appBar = findViewById<SecondaryAppBarView>(R.id.secondaryAppBar)

        appBar.setOnBackClick {
            finish()
        }

        appBar.setOnActionClick {
            Toast.makeText(this, "Action Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}