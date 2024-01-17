package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetDestinoVisitTerc
import javax.inject.Inject

class SetDestinoVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): SetDestinoVisitTerc {

    override suspend fun invoke(destino: String): Boolean {
        return try {
            movEquipVisitTercRepository.setDestinoMovEquipVisitTerc(destino)
        } catch (exception: Exception) {
            false
        }
    }

}