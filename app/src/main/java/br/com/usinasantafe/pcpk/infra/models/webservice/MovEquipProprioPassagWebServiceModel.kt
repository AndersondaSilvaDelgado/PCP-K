package br.com.usinasantafe.pcpk.infra.models.webservice

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioPassag
import kotlinx.serialization.Serializable

@Serializable
data class MovEquipProprioPassagWebServiceModelOutput(
    var idMovEquipProprioPassag: Long,
    var idMovEquipProprio: Long,
    var nroMatricMovEquipProprioPassag: Long,
)

fun br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioPassag.entityToMovEquipProprioPassagWebServiceModel(): MovEquipProprioPassagWebServiceModelOutput {
    return with(this){
        MovEquipProprioPassagWebServiceModelOutput(
            idMovEquipProprioPassag = this.idMovEquipProprioPassag!!,
            idMovEquipProprio = this.idMovEquipProprio!!,
            nroMatricMovEquipProprioPassag = this.nroMatricMovEquipProprioPassag!!,
        )
    }
}