package com.example.gestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_type_contract.*

class addTypeContract : AppCompatActivity() {

    lateinit var _realm:Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_type_contract)
        _realm = Realm.getDefaultInstance()
    }

    fun buttonAddClick(button: View){
        var typeName = editText.text.toString()
        if(typeName.isNotEmpty()) {
            var typeContract = TypeContract()
            typeContract.Nom = typeName
            _realm.beginTransaction()
            _realm.copyToRealm(typeContract)
            _realm.commitTransaction()
        }
        var intent = Intent(this, addContract::class.java)
        startActivity(intent)
    }

}
