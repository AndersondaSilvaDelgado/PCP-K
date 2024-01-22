package br.com.usinasantafe.pcpk.features.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_PROPRIO_PASSAG
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioPassag
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TB_MOV_EQUIP_PROPRIO_PASSAG)
data class MovEquipProprioPassagRoomModel(
    @PrimaryKey(autoGenerate = true)
    var idMovEquipProprioPassag: Long? = null,
    var idMovEquipProprio: Long,
    var nroMatricMovEquipProprioPassag: Long,
)

fun MovEquipProprioPassag.entityToMovEquipProprioPassagRoomModel(idMov: Long): MovEquipProprioPassagRoomModel{
    return with(this){
        MovEquipProprioPassagRoomModel(
            idMovEquipProprio = idMov,
            nroMatricMovEquipProprioPassag = this.nroMatricMovEquipProprioPassag!!,
        )
    }
}

fun MovEquipProprioPassagRoomModel.modelRoomToMovEquipProprioPassag(): MovEquipProprioPassag {
    return with(this){
        MovEquipProprioPassag(
            idMovEquipProprioPassag = this.idMovEquipProprioPassag,
            idMovEquipProprio = this.idMovEquipProprio,
            nroMatricMovEquipProprioPassag = this.nroMatricMovEquipProprioPassag,
        )
    }
}
