package com.example.gestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun memberClick(button: View){
        val intent = Intent(this, MainMembers::class.java)
        startActivity(intent)
    }

    fun contractClick(button: View){
        val intent = Intent(this, MainContracts::class.java)
        startActivity(intent)
    }
}
