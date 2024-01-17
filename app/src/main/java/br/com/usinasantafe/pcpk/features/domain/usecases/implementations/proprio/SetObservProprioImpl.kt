package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SaveMovEquipProprioOpen
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetObservProprio
import javax.inject.Inject

class SetObservProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val saveMovEquipProprioOpen: SaveMovEquipProprioOpen,
): SetObservProprio {

    override suspend fun invoke(observ: String?): Boolean {
        try {
            if(!movEquipProprioRepository.setObservMovEquipProprio(observ)) return false
            return saveMovEquipProprioOpen()
        } catch (exception: Exception) {
            return false
        }
    }

}