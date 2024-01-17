package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia

interface ReceiverSentDataMovEquipResidencia {

    suspend operator fun invoke(movEquipResidenciaList: List<MovEquipResidencia>): Boolean

}