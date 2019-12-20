package com.example.gestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add_contract.*
import kotlinx.android.synthetic.main.activity_add_member.*

class addMember : AppCompatActivity() {

    lateinit var _realm: Realm
    val member = Member()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)
        _realm = Realm.getDefaultInstance()



        // Initializing a String Array
        val contracts = _realm.where(Contrat::class.java).findAll()
        val listType = mutableListOf<String>()
        for (type in contracts){
            listType.add(type.Type!!.Nom)
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
        spinner2.adapter = adapter;

        // Set an on item selected listener for spinner object
        spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                val name = spinner2.selectedItem.toString()
                val type = _realm.where(Contrat::class.java).equalTo("Type.Nom",name).findFirst()
                member.Contrat = type
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }
    }

    fun buttonClickCreate(button: View){
        member.Nom = editText2.text.toString()
        member.Prenom = editText6.text.toString()
        member.DateDeNaissance = editText7.text.toString()
        _realm.beginTransaction()
        _realm.copyToRealm(member)
        _realm.commitTransaction()
        var intent = Intent(this, MainMembers::class.java)
        startActivity(intent)
    }
}
