package br.com.usinasantafe.pcpk.features.domain.usecases.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipResidenciaModel
import br.com.usinasantafe.pcpk.features.presenter.model.toMovEquipResidenciaModel
import javax.inject.Inject

interface RecoverListMovEquipResidenciaOpen {
    suspend operator fun invoke(): List<MovEquipResidenciaModel>
}

class RecoverListMovEquipResidenciaOpenImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): RecoverListMovEquipResidenciaOpen {

    override suspend fun invoke(): List<MovEquipResidenciaModel> {
        return movEquipResidenciaRepository.listMovEquipResidenciaOpen().map { it.toMovEquipResidenciaModel() }
    }

}