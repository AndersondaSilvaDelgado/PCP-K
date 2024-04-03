package br.com.usinasantafe.pcpk.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.TB_MOV_EQUIP_PROPRIO_PASSAG
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioPassag
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TB_MOV_EQUIP_PROPRIO_PASSAG)
data class MovEquipProprioPassagRoomModel(
    @PrimaryKey(autoGenerate = true)
    var idMovEquipProprioPassag: Long? = null,
    var idMovEquipProprio: Long,
    var nroMatricMovEquipProprioPassag: Long,
)

fun br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioPassag.entityToMovEquipProprioPassagRoomModel(idMov: Long): MovEquipProprioPassagRoomModel{
    return with(this){
        MovEquipProprioPassagRoomModel(
            idMovEquipProprio = idMov,
            nroMatricMovEquipProprioPassag = this.nroMatricMovEquipProprioPassag!!,
        )
    }
}

fun MovEquipProprioPassagRoomModel.modelRoomToMovEquipProprioPassag(): br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioPassag {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioPassag(
            idMovEquipProprioPassag = this.idMovEquipProprioPassag,
            idMovEquipProprio = this.idMovEquipProprio,
            nroMatricMovEquipProprioPassag = this.nroMatricMovEquipProprioPassag,
        )
    }
}
