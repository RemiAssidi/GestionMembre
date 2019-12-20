package com.example.gestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_modify_member.*

class modifyMember : AppCompatActivity() {

    lateinit var _realm:Realm
    lateinit var selectedMember:Member

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_member)
        _realm = Realm.getDefaultInstance()
        var DateDeNaissance = intent.getStringExtra("DateDeNaissance")
        var Nom = intent.getStringExtra("Nom")
        var Prenom = intent.getStringExtra("Prenom")
        selectedMember = _realm.where(Member::class.java).equalTo("DateDeNaissance",DateDeNaissance).equalTo("Nom", Nom).equalTo("Prenom", Prenom).findFirst()!!
        editText5.setText(selectedMember!!.Nom)
        editText9.setText(selectedMember!!.Prenom)
        editText10.setText(selectedMember!!.DateDeNaissance.toString())
    }

    fun change(button: View){
        _realm.beginTransaction()
        selectedMember.Nom = editText5.text.toString()
        selectedMember.Prenom = editText9.text.toString()
        selectedMember.DateDeNaissance = editText10.text.toString()
        _realm.commitTransaction()
        val intent = Intent(this, MainMembers::class.java)
        startActivity(intent)
    }

    fun delete(button: View){
        _realm.beginTransaction()
        selectedMember.deleteFromRealm()
        _realm.commitTransaction()
        val intent = Intent(this, MainMembers::class.java)
        startActivity(intent)
    }
}
