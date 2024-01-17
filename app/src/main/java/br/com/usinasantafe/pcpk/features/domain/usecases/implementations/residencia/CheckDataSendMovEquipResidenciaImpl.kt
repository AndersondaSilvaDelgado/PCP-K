package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.CheckDataSendMovEquipResidencia

import javax.inject.Inject

class CheckDataSendMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): CheckDataSendMovEquipResidencia {

    override suspend fun invoke(): Boolean {
        return movEquipResidenciaRepository.checkMovSend()
    }

}