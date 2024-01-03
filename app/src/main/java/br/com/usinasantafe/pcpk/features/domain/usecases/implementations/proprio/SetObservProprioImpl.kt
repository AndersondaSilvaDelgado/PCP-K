package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetObservProprio
import javax.inject.Inject

class SetObservProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): SetObservProprio {

    override suspend fun invoke(observ: String): Boolean {
        return try {
            movEquipProprioRepository.setObservMovEquipProprio(observ)
        } catch (exception: Exception) {
            false
        }
    }

}