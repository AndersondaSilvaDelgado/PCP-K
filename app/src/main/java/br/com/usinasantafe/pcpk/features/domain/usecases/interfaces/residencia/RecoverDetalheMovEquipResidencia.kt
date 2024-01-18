package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipResidenciaModel

interface RecoverDetalheMovEquipResidencia {

    suspend operator fun invoke(pos: Int): DetalheMovEquipResidenciaModel

}