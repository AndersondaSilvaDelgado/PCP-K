package br.com.usinasantafe.pcpk.domain.usecases.residencia

import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.presenter.residencia.movequip.MovEquipResidenciaModel
import br.com.usinasantafe.pcpk.presenter.residencia.movequip.toMovEquipResidenciaModel
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