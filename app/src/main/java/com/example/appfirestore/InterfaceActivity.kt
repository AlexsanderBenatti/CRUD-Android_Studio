package com.example.appfirestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InterfaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface)
        val createBtn = findViewById<Button>(R.id.createBtn)
        val listBtn = findViewById<Button>(R.id.listBtn)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)
        val updateBtn = findViewById<Button>(R.id.updateBtn)

        createBtn.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}