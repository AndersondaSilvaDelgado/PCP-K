package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetPlacaVisitTerc
import javax.inject.Inject

class SetPlacaVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): SetPlacaVisitTerc {

    override suspend fun invoke(placa: String): Boolean {
        return try {
            movEquipVisitTercRepository.setPlacaMovEquipVisitTerc(placa)
        } catch (exception: Exception) {
            false
        }
    }

}