package br.com.usinasantafe.pcpk.features.domain.usecases.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipResidenciaModel
import br.com.usinasantafe.pcpk.features.presenter.model.toMovEquipResidenciaModel
import javax.inject.Inject

interface RecoverListMovEquipResidenciaStarted {
    suspend operator fun invoke(): List<MovEquipResidenciaModel>
}

class RecoverListMovEquipResidenciaStartedImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): RecoverListMovEquipResidenciaStarted {

    override suspend fun invoke(): List<MovEquipResidenciaModel> {
        return movEquipResidenciaRepository.listMovEquipResidenciaStarted().map { it.toMovEquipResidenciaModel() }
    }

}