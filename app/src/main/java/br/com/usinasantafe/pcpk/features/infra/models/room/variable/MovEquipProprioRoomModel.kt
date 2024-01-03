package br.com.usinasantafe.pcpk.features.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_PROPRIO
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio
import java.util.Date

@Entity(tableName = TB_MOV_EQUIP_PROPRIO)
data class MovEquipProprioRoomModel(
    @PrimaryKey(autoGenerate = true)
    var idMovEquipProprio: Long? = null,
    var tipoMovEquipProprio: TypeMov,
    var idEquipMovEquipProprio: Long,
    var idLocalMovEquipProprio: Long,
    var dthrMovEquipProprio: Long,
    var nroMatricVigiaMovEquipProprio: Long,
    var nroMatricColabMovEquipProprio: Long,
    var destinoMovEquipProprio: String,
    var nroNotaFiscalMovEquipProprio: Long,
    var observMovEquipProprio: String,
    var nroAparelhoMovEquipProprio: Long,
    var statusMovEquipProprio: StatusData,
    var statusSendMovEquipProprio: StatusSend,
)

fun MovEquipProprio.toMovEquipProprioModel(): MovEquipProprioRoomModel{
    return with(this){
        MovEquipProprioRoomModel(
            tipoMovEquipProprio = this.tipoMovEquipProprio!!,
            idEquipMovEquipProprio = this.idEquipMovEquipProprio!!,
            idLocalMovEquipProprio = this.idLocalMovEquipProprio!!,
            dthrMovEquipProprio = Date().time,
            nroMatricVigiaMovEquipProprio = this.nroMatricVigiaMovEquipProprio!!,
            nroMatricColabMovEquipProprio = this.nroMatricColabMovEquipProprio!!,
            destinoMovEquipProprio = this.destinoMovEquipProprio!!,
            nroNotaFiscalMovEquipProprio = this.nroNotaFiscalMovEquipProprio!!,
            observMovEquipProprio = this.observMovEquipProprio!!,
            nroAparelhoMovEquipProprio = this.nroAparelhoMovEquipProprio!!,
            statusMovEquipProprio = StatusData.OPEN,
            statusSendMovEquipProprio = StatusSend.SEND,
        )
    }
}

fun MovEquipProprioRoomModel.toMovEquipProprio(): MovEquipProprio{
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
            nroAparelhoMovEquipProprio = this.nroAparelhoMovEquipProprio,
            statusMovEquipProprio = this.statusMovEquipProprio,
            statusSendMovEquipProprio = this.statusSendMovEquipProprio,
        )
    }
}