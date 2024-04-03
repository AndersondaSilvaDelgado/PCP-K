package br.com.usinasantafe.pcpk.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.TB_EQUIP
import br.com.usinasantafe.pcpk.domain.entities.stable.Equip
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TB_EQUIP)
data class EquipRoomModel (
    @PrimaryKey
    val idEquip: Long,
    val nroEquip: Long,
)

fun EquipRoomModel.toEquip(): br.com.usinasantafe.pcpk.domain.entities.stable.Equip {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.stable.Equip(
            idEquip = this.idEquip,
            nroEquip = this.nroEquip,
        )
    }
}

fun br.com.usinasantafe.pcpk.domain.entities.stable.Equip.toEquipModel(): EquipRoomModel{
    return with(this){
        EquipRoomModel(
            idEquip = this.idEquip,
            nroEquip = this.nroEquip,
        )
    }
}