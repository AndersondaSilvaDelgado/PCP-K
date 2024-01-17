package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetPlacaResidencia
import javax.inject.Inject

class SetPlacaResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SetPlacaResidencia {

    override suspend fun invoke(placa: String): Boolean {
        return try {
            movEquipResidenciaRepository.setPlacaMovEquipResidencia(placa)
        } catch (exception: Exception) {
            false
        }
    }

}