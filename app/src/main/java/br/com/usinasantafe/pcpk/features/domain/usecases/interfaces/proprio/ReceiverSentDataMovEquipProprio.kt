package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio

interface ReceiverSentDataMovEquipProprio {

    suspend operator fun invoke(movEquipProprioList: List<MovEquipProprio>): Boolean

}