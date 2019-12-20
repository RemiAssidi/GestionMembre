package com.example.gestion

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class Member() : RealmObject(){
    var Nom: String = ""
    var Prenom: String = ""
    var DateDeNaissance: String = "01/01/2019"
    var Contrat: Contrat? = null
}