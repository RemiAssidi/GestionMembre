package com.example.gestion

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import org.jetbrains.annotations.NotNull
import java.util.*
import kotlin.contracts.contract

@RealmClass
open class Contrat() : RealmObject(){
    var DateDebut: String = "01/01/2019"
    var DateDeFin: String = "01/01/2019"
    var Horaire: Int = 35
    var Type: TypeContract? = null
}