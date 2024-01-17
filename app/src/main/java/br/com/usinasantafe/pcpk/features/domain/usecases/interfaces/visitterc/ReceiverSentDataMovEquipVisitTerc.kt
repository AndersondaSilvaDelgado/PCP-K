package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc

interface ReceiverSentDataMovEquipVisitTerc {

    suspend operator fun invoke(movEquipVisitTercList: List<MovEquipVisitTerc>): Boolean

}