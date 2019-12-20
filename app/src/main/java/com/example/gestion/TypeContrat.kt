package com.example.gestion

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class TypeContract() : RealmObject(){
    var Nom: String = "name"
}