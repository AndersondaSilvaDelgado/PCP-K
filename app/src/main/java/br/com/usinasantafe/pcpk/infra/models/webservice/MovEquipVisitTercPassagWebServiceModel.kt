package br.com.usinasantafe.pcpk.infra.models.webservice

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTercPassag
import kotlinx.serialization.Serializable

@Serializable
data class MovEquipVisitTercPassagWebServiceModelOutput(
    var idMovEquipVisitTercPassag: Long,
    var idMovEquipVisitTerc: Long,
    var idVisitTercMovEquipVisitTercPassag: Long,
)

fun br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTercPassag.entityToMovEquipVisitTercPassagWebServiceModel(): MovEquipVisitTercPassagWebServiceModelOutput {
    return with(this){
        MovEquipVisitTercPassagWebServiceModelOutput(
            idMovEquipVisitTercPassag = this.idMovEquipVisitTercPassag!!,
            idMovEquipVisitTerc = this.idMovEquipVisitTerc!!,
            idVisitTercMovEquipVisitTercPassag = this.idVisitTercMovEquipVisitTercPassag!!,
        )
    }
}
