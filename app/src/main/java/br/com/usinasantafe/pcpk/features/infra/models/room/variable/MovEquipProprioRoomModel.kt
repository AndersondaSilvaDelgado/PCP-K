package br.com.usinasantafe.pcpk.features.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_PROPRIO
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Entity(tableName = TB_MOV_EQUIP_PROPRIO)
data class MovEquipProprioRoomModel(
    @PrimaryKey(autoGenerate = true)
    var idMovEquipProprio: Long? = null,
    var nroMatricVigiaMovEquipProprio: Long,
    var idLocalMovEquipProprio: Long,
    var tipoMovEquipProprio: TypeMov,
    var idEquipMovEquipProprio: Long,
    var dthrMovEquipProprio: Long,
    var nroMatricColabMovEquipProprio: Long,
    var destinoMovEquipProprio: String,
    var nroNotaFiscalMovEquipProprio: Long?,
    var observMovEquipProprio: String?,
    var statusMovEquipProprio: StatusData,
    var statusSendMovEquipProprio: StatusSend,
)

fun MovEquipProprio.entityToMovEquipProprioRoomModel(matricVigia: Long, idLocal: Long): MovEquipProprioRoomModel{
    return with(this){
        MovEquipProprioRoomModel(
            nroMatricVigiaMovEquipProprio = matricVigia,
            idLocalMovEquipProprio = idLocal,
            tipoMovEquipProprio = this.tipoMovEquipProprio!!,
            idEquipMovEquipProprio = this.idEquipMovEquipProprio!!,
            dthrMovEquipProprio = Date().time,
            nroMatricColabMovEquipProprio = this.nroMatricColabMovEquipProprio!!,
            destinoMovEquipProprio = this.destinoMovEquipProprio!!,
            nroNotaFiscalMovEquipProprio = this.nroNotaFiscalMovEquipProprio,
            observMovEquipProprio = this.observMovEquipProprio,
            statusMovEquipProprio = StatusData.OPEN,
            statusSendMovEquipProprio = StatusSend.EMPTY,
        )
    }
}

fun MovEquipProprioRoomModel.modelRoomToMovEquipProprio(): MovEquipProprio{
    return with(this){
        MovEquipProprio(
            idMovEquipProprio = this.idMovEquipProprio,
            tipoMovEquipProprio = this.tipoMovEquipProprio,
            idLocalMovEquipProprio = this.idLocalMovEquipProprio,
            dthrMovEquipProprio = Date(this.dthrMovEquipProprio),
            nroMatricVigiaMovEquipProprio = this.nroMatricVigiaMovEquipProprio,
            nroMatricColabMovEquipProprio = this.nroMatricColabMovEquipProprio,
            destinoMovEquipProprio = this.destinoMovEquipProprio,
            nroNotaFiscalMovEquipProprio = this.nroNotaFiscalMovEquipProprio,
            observMovEquipProprio = this.observMovEquipProprio,
            statusMovEquipProprio = this.statusMovEquipProprio,
            statusSendMovEquipProprio = this.statusSendMovEquipProprio,
        )
    }
}