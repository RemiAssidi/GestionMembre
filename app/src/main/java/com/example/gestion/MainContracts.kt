package com.example.gestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main_contracts.*
import kotlinx.android.synthetic.main.cell_member.view.*

class MainContracts : AppCompatActivity() {

    lateinit var _realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_contracts)
        _realm = Realm.getDefaultInstance()
        monRecycler.layoutManager = LinearLayoutManager(this)
        monRecycler.adapter = TopPlayerAdapter()
    }

    inner class TopPlayerAdapter : RecyclerView.Adapter<TopPlayerAdapter.PlayerCellHolder>() {

        val ContractList = _realm.where(Contrat::class.java).findAll()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerCellHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.cell_member, parent, false)
            val holder = PlayerCellHolder(view)
            return holder
        }

        override fun getItemCount(): Int {
            return ContractList.size
        }

        override fun onBindViewHolder(holder: PlayerCellHolder, position: Int) {

            holder.displayPlayer(ContractList[position]!!)
        }

        inner class PlayerCellHolder(view: View) : RecyclerView.ViewHolder(view) {
            val lblName = view.lbl_name
            val lblScore = view.lbl_score

            init {
                view.setOnClickListener { onCellTouched(adapterPosition) }
            }

            fun displayPlayer(display: Contrat) {
                lblName.text = "${display.Type!!.Nom}"
                lblScore.text = "${display.Horaire.toString()} h"
            }
        }

        private fun onCellTouched(playerIndex: Int) {
            val selectedPlayer = ContractList[playerIndex]
            move(selectedPlayer!!)
        }
    }

    fun createContratClick(button: View){
        var intent = Intent(this,addContract::class.java)
        startActivity(intent)
    }

    fun move(Contrat: Contrat){
        var intent = Intent(this, modifyContrat::class.java)
        intent.putExtra("ContratType", Contrat.Type!!.Nom)
        intent.putExtra("ContratTime", Contrat.Horaire)
        intent.putExtra("Debut", Contrat.DateDebut)
        intent.putExtra("Fin", Contrat.DateDeFin)
        startActivity(intent)
    }

    fun back(button: View){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
