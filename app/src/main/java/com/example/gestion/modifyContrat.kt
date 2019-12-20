package com.example.gestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_modify_contrat.*

class modifyContrat : AppCompatActivity() {

    lateinit var _realm:Realm
    lateinit var selectedContrat:Contrat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_contrat)
        _realm = Realm.getDefaultInstance()
        var type = intent.getStringExtra("ContratType")
        var debut = intent.getStringExtra("Debut")
        var fin = intent.getStringExtra("Fin")
        selectedContrat = _realm.where(Contrat::class.java).equalTo("Type.Nom",type).equalTo("DateDebut", debut).equalTo("DateDeFin", fin).findFirst()!!
        editText3.setText(selectedContrat!!.DateDebut)
        editText4.setText(selectedContrat!!.DateDeFin)
        editText8.setText(selectedContrat!!.Horaire.toString())

    }

    fun change(button: View){
        _realm.beginTransaction()
        selectedContrat.DateDebut = editText3.text.toString()
        selectedContrat.DateDeFin = editText4.text.toString()
        val date = editText8.text.toString()
        selectedContrat.Horaire = date.toInt()
        _realm.commitTransaction()
        val intent = Intent(this, MainContracts::class.java)
        startActivity(intent)
    }

    fun delete(button: View){
        _realm.beginTransaction()
        selectedContrat.deleteFromRealm()
        _realm.commitTransaction()
        val intent = Intent(this, MainContracts::class.java)
        startActivity(intent)
    }
}
