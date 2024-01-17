package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetVeiculoVisitTerc
import javax.inject.Inject

class SetVeiculoVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): SetVeiculoVisitTerc {

    override suspend fun invoke(veiculo: String): Boolean {
        return try {
            movEquipVisitTercRepository.setVeiculoMovEquipVisitTerc(veiculo)
        } catch (exception: Exception) {
            false
        }
    }

}