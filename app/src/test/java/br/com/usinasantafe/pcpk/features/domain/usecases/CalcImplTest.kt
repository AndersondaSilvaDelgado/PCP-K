package br.com.usinasantafe.pcpk.features.domain.usecases

import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner

@RunWith(MockitoJUnitRunner::class)
class CalcImplTest {

    @Test
    fun test() {
        assertTrue(CalcImpl().invoke("19759"))
    }
}