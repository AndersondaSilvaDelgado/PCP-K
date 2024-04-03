package br.com.usinasantafe.pcpk.domain.usecases.residencia

import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.presenter.residencia.movequip.MovEquipResidenciaModel
import br.com.usinasantafe.pcpk.presenter.residencia.movequip.toMovEquipResidenciaModel
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