package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository

import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetNotaFiscalProprio
import javax.inject.Inject

class SetNotaFiscalProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): SetNotaFiscalProprio {

    override suspend fun invoke(notaFiscal: String): Boolean {
        return try {
            movEquipProprioRepository.setNotaFiscalMovEquipProprio(notaFiscal.toLong())
        } catch (exception: Exception) {
            false
        }
    }

}