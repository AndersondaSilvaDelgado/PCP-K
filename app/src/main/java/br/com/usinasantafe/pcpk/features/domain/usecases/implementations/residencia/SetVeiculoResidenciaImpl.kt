package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetVeiculoResidencia
import javax.inject.Inject

class SetVeiculoResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SetVeiculoResidencia {

    override suspend fun invoke(veiculo: String): Boolean {
        return try {
            movEquipResidenciaRepository.setVeiculoMovEquipResidencia(veiculo)
        } catch (exception: Exception) {
            false
        }
    }

}