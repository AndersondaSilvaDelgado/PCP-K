package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.utils.FlowApp
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import javax.inject.Inject

interface SetDestinoProprio {
    suspend operator fun invoke(destino: String, flowApp: FlowApp, pos: Int): Boolean
}

class SetDestinoProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): SetDestinoProprio {

    override suspend fun invoke(destino: String, flowApp: FlowApp, pos: Int): Boolean {
        return try {
            when(flowApp) {
                FlowApp.ADD -> movEquipProprioRepository.setDestinoMovEquipProprio(destino)
                FlowApp.CHANGE -> {
                    val movEquip = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
                    movEquipProprioRepository.updateDestinoMovEquipProprio(destino, movEquip)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}