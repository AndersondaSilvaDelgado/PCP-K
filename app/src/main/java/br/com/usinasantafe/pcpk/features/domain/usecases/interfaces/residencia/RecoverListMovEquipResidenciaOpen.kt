package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipResidenciaModel
interface RecoverListMovEquipResidenciaOpen {

    suspend operator fun invoke(): List<MovEquipResidenciaModel>

}