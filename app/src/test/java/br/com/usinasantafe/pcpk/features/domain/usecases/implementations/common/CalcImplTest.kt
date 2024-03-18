package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import org.junit.Assert.*

import org.junit.Test

class CalcImplTest {

    @Test
    fun testCalc() {
        assertTrue(CalcImpl().invoke("19759"))
    }
}