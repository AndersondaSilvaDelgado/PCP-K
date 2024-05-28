package br.com.usinasantafe.pcp.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcp.utils.StatusData
import br.com.usinasantafe.pcp.utils.StatusSend
import br.com.usinasantafe.pcp.utils.TB_MOV_EQUIP_PROPRIO
import br.com.usinasantafe.pcp.utils.TypeMov
import br.com.usinasantafe.pcp.domain.entities.variable.MovEquipProprio
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

fun MovEquipProprioRoomModel.modelRoomToMovEquipProprio(): MovEquipProprio {
    println("Data Room = ${this.dthrMovEquipProprio}")
    println("Data Entidade Convertido = ${Date(this.dthrMovEquipProprio)}")
    return with(this){
        MovEquipProprio(
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

fun MovEquipProprio.entityToMovEquipProprioRoomModel(matricVigia: Long, idLocal: Long): MovEquipProprioRoomModel{
    return with(this){
        MovEquipProprioRoomModel(
            idMovEquipProprio = idMovEquipProprio,
            nroMatricVigiaMovEquipProprio = matricVigia,
            idLocalMovEquipProprio = idLocal,
            tipoMovEquipProprio = this.tipoMovEquipProprio!!,
            dthrMovEquipProprio = this.dthrMovEquipProprio.time,
            idEquipMovEquipProprio = this.idEquipMovEquipProprio!!,
            nroMatricColabMovEquipProprio = this.nroMatricColabMovEquipProprio!!,
            destinoMovEquipProprio = this.destinoMovEquipProprio!!,
            nroNotaFiscalMovEquipProprio = this.nroNotaFiscalMovEquipProprio,
            observMovEquipProprio = this.observMovEquipProprio,
            statusSendMovEquipProprio = this.statusSendMovEquipProprio,
        )
    }
}