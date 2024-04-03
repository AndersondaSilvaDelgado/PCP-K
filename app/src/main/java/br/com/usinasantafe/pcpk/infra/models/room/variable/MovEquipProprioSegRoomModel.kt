package br.com.usinasantafe.pcpk.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.TB_MOV_EQUIP_PROPRIO_SEG
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioSeg
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TB_MOV_EQUIP_PROPRIO_SEG)
data class MovEquipProprioSegRoomModel(
    @PrimaryKey(autoGenerate = true)
    var idMovEquipProprioSeg: Long? = null,
    var idMovEquipProprio: Long,
    var idEquipMovEquipProprioSeg: Long,
)

fun br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioSeg.entityToMovEquipProprioSegRoomModel(idMov: Long): MovEquipProprioSegRoomModel{
    return with(this){
        MovEquipProprioSegRoomModel(
            idMovEquipProprio = idMov,
            idEquipMovEquipProprioSeg = this.idEquipMovEquipProprioSeg!!,
        )
    }
}

fun MovEquipProprioSegRoomModel.modelRoomToMovEquipSegPassag(): br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioSeg {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioSeg(
            idMovEquipProprioSeg = this.idMovEquipProprioSeg,
            idMovEquipProprio = this.idMovEquipProprio,
            idEquipMovEquipProprioSeg = this.idEquipMovEquipProprioSeg,
        )
    }
}
