package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository

import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetNotaFiscalProprio
import javax.inject.Inject

class SetNotaFiscalProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): SetNotaFiscalProprio {

    override suspend fun invoke(notaFiscal: String, flowApp: FlowApp, pos: Int): Boolean {
        return try {
            when(flowApp){
                FlowApp.ADD -> movEquipProprioRepository.setNotaFiscalMovEquipProprio(notaFiscal.toLong())
                FlowApp.CHANGE -> {
                    val movEquip = movEquipProprioRepository.listMovEquipProprioOpen()[pos]
                    movEquipProprioRepository.updateNotaFiscalMovEquipProprio(notaFiscal.toLong(), movEquip)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}