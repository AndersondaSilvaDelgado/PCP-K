package br.com.usinasantafe.pcpk.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.StatusData
import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.utils.TB_MOV_EQUIP_PROPRIO
import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
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
    var dthrMovEquipProprio: Long,
    var idEquipMovEquipProprio: Long,
    var nroMatricColabMovEquipProprio: Long,
    var destinoMovEquipProprio: String,
    var nroNotaFiscalMovEquipProprio: Long?,
    var observMovEquipProprio: String?,
    var statusSendMovEquipProprio: StatusSend,
)

fun MovEquipProprioRoomModel.modelRoomToMovEquipProprio(): br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio(
            idMovEquipProprio = this.idMovEquipProprio,
            nroMatricVigiaMovEquipProprio = this.nroMatricVigiaMovEquipProprio,
            idLocalMovEquipProprio = this.idLocalMovEquipProprio,
            tipoMovEquipProprio = this.tipoMovEquipProprio,
            dthrMovEquipProprio = Date(this.dthrMovEquipProprio),
            idEquipMovEquipProprio = this.idEquipMovEquipProprio,
            nroMatricColabMovEquipProprio = this.nroMatricColabMovEquipProprio,
            destinoMovEquipProprio = this.destinoMovEquipProprio,
            nroNotaFiscalMovEquipProprio = this.nroNotaFiscalMovEquipProprio,
            observMovEquipProprio = this.observMovEquipProprio,
            statusSendMovEquipProprio = this.statusSendMovEquipProprio,
        )
    }
}

fun br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio.entityToMovEquipProprioRoomModel(matricVigia: Long, idLocal: Long): MovEquipProprioRoomModel{
    return with(this){
        MovEquipProprioRoomModel(
            idMovEquipProprio = idMovEquipProprio,
            nroMatricVigiaMovEquipProprio = matricVigia,
            idLocalMovEquipProprio = idLocal,
            tipoMovEquipProprio = this.tipoMovEquipProprio!!,
            dthrMovEquipProprio = Date().time,
            idEquipMovEquipProprio = this.idEquipMovEquipProprio!!,
            nroMatricColabMovEquipProprio = this.nroMatricColabMovEquipProprio!!,
            destinoMovEquipProprio = this.destinoMovEquipProprio!!,
            nroNotaFiscalMovEquipProprio = this.nroNotaFiscalMovEquipProprio,
            observMovEquipProprio = this.observMovEquipProprio,
            statusSendMovEquipProprio = this.statusSendMovEquipProprio,
        )
    }
}