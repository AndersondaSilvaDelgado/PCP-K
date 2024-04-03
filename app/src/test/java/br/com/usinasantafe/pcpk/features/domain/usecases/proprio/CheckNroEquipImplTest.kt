package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.domain.repositories.stable.EquipRepository
import org.junit.Assert.*

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CheckNroEquipImplTest {

    @Test
    fun test() = runBlocking {
        val equipRepository = mock<EquipRepository>()
        whenever(equipRepository.checkEquipNro(100L)).thenReturn(true)
        val usecase = CheckNroEquipImpl(equipRepository)
        val result = usecase("100")
        assertTrue(result)
    }
}