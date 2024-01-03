package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetDestinoProprio
import javax.inject.Inject

class SetDestinoProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): SetDestinoProprio {

    override suspend fun invoke(destino: String): Boolean {
        return try {
            movEquipProprioRepository.setDestinoMovEquipProprio(destino)
        } catch (exception: Exception) {
            false
        }
    }

}