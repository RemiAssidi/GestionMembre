package com.example.gestion

import org.junit.Test

import org.junit.Assert.*

class ContratTest {

    @Test
    fun getDateDebut() {
        var contrat: Contrat = Contrat()
        var date = "01/02/2019"
        contrat.DateDebut = date
        assertEquals(date, contrat.DateDebut)
    }

    @Test
    fun setDateDebut() {
        var contrat: Contrat = Contrat()
        var date = "01/02/2019"
        contrat.DateDebut = date
        assertEquals(date, contrat.DateDebut)
    }

    @Test
    fun getDateDeFin() {
        var contrat: Contrat = Contrat()
        var date = "01/02/2019"
        contrat.DateDeFin = date
        assertEquals(date, contrat.DateDeFin)
    }

    @Test
    fun setDateDeFin() {
        var contrat: Contrat = Contrat()
        var date = "01/02/2019"
        contrat.DateDeFin = date
        assertEquals(date, contrat.DateDeFin)
    }

    @Test
    fun getHoraire() {
        var contrat: Contrat = Contrat()
        var horaire = 35
        contrat.Horaire = horaire
        assertEquals(horaire, contrat.Horaire)
    }

    @Test
    fun setHoraire() {
        var contrat: Contrat = Contrat()
        var horaire = 35
        contrat.Horaire = horaire
        assertEquals(horaire, contrat.Horaire)
    }

    @Test
    fun getType() {
        var contrat: Contrat = Contrat()
        var type = TypeContract()
        type.Nom = "CDI"
        contrat.Type = type
        assertEquals(type, contrat.Type)
    }

    @Test
    fun setType() {
        var contrat: Contrat = Contrat()
        var type = TypeContract()
        type.Nom = "CDI"
        contrat.Type = type
        assertEquals(type, contrat.Type)
    }
}