package com.example.gestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_contract.*

class addContract : AppCompatActivity() {

    lateinit var _realm:Realm
    val Contrat = Contrat()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contract)
        _realm = Realm.getDefaultInstance()


        // Initializing a String Array
        val typeContracts = _realm.where(TypeContract::class.java).findAll()
        val listType = mutableListOf<String>()
        for (type in typeContracts){
            listType.add(type.Nom)
        }

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            listType // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        spinner.adapter = adapter;

        // Set an on item selected listener for spinner object
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                val name = spinner.selectedItem.toString()
                val type = _realm.where(TypeContract::class.java).equalTo("Nom",name).findFirst()
                Contrat.Type = type
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }
    }

    fun buttonClickCreateType(button: View){

        var intent = Intent(this, addTypeContract::class.java)
        startActivity(intent)
    }

    fun buttonClickCreate(button: View){
        val test:String = horairesNumberEdit.text.toString()
        var num:Int? = test.toIntOrNull()
        if (num != null) {
            Contrat.Horaire = num
        }
        println(editDateDebutEdit.toString())
        Contrat.DateDebut = editDateDebutEdit.text.toString()
        Contrat.DateDeFin = editDateFinEdit.text.toString()
        val horaire : String = horairesNumberEdit.text.toString()
        Contrat.Horaire = horaire.toInt()
        _realm.beginTransaction()
        _realm.copyToRealm(Contrat)
        _realm.commitTransaction()
        var intent = Intent(this, MainContracts::class.java)
        startActivity(intent)
    }
}
