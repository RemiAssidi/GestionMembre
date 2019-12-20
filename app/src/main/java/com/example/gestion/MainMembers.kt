package com.example.gestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main_members.*
import kotlinx.android.synthetic.main.cell_member.view.*

class MainMembers : AppCompatActivity() {

    lateinit var _realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_members)
        _realm = Realm.getDefaultInstance()
        monRecyclerMember.layoutManager = LinearLayoutManager(this)
        monRecyclerMember.adapter = TopPlayerAdapter2()
    }

    inner class TopPlayerAdapter2 : RecyclerView.Adapter<TopPlayerAdapter2.PlayerCellHolder>() {

        val ContractList = _realm.where(Member::class.java).findAll()

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

            fun displayPlayer(display: Member) {
                lblName.text = "${display.Nom}"
                lblScore.text = "${display.Contrat!!.Type!!.Nom}"
            }
        }

        private fun onCellTouched(playerIndex: Int) {
            val selectedPlayer = ContractList[playerIndex]
            move(selectedPlayer!!)
        }
    }

    fun clickAddMember(button: View){
        var intent = Intent(this, addMember::class.java)
        startActivity(intent)
    }

    fun move(Member: Member){
        var intent = Intent(this, modifyMember::class.java)
        intent.putExtra("DateDeNaissance", Member.DateDeNaissance)
        intent.putExtra("Nom", Member.Nom)
        intent.putExtra("Prenom", Member.Prenom)
        startActivity(intent)
    }

    fun back(button: View){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
