package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipResidenciaModel

interface RecoverListMovEquipResidenciaStarted {

    suspend operator fun invoke(): List<MovEquipResidenciaModel>

}