package br.com.usinasantafe.pcpk.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.StatusData
import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.utils.TB_MOV_EQUIP_RESIDENCIA
import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Entity(tableName = TB_MOV_EQUIP_RESIDENCIA)
data class MovEquipResidenciaRoomModel(
    @PrimaryKey(autoGenerate = true)
    var idMovEquipResidencia: Long? = null,
    var nroMatricVigiaMovEquipResidencia: Long,
    var idLocalMovEquipResidencia: Long,
    var tipoMovEquipResidencia: TypeMov,
    var dthrMovEquipResidencia: Long,
    var motoristaMovEquipResidencia: String,
    var veiculoMovEquipResidencia: String,
    var placaMovEquipResidencia: String,
    var observMovEquipResidencia: String?,
    var statusMovEquipResidencia: StatusData,
    var statusSendMovEquipResidencia: StatusSend,
)

fun br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia.entityToMovEquipResidenciaRoomModel(matricVigia: Long, idLocal: Long): MovEquipResidenciaRoomModel{
    return with(this){
        MovEquipResidenciaRoomModel(
            idMovEquipResidencia = this.idMovEquipResidencia,
            nroMatricVigiaMovEquipResidencia = matricVigia,
            idLocalMovEquipResidencia = idLocal,
            dthrMovEquipResidencia = Date().time,
            tipoMovEquipResidencia = this.tipoMovEquipResidencia!!,
            motoristaMovEquipResidencia = this.motoristaMovEquipResidencia!!,
            veiculoMovEquipResidencia = this.veiculoMovEquipResidencia!!,
            placaMovEquipResidencia = this.placaMovEquipResidencia!!,
            observMovEquipResidencia = this.observMovEquipResidencia,
            statusMovEquipResidencia = this.statusMovEquipResidencia,
            statusSendMovEquipResidencia = this.statusSendMovEquipResidencia,
        )
    }
}

fun MovEquipResidenciaRoomModel.modelRoomToMovEquipResidencia(): br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia(
            idMovEquipResidencia = this.idMovEquipResidencia,
            nroMatricVigiaMovEquipResidencia = this.nroMatricVigiaMovEquipResidencia,
            idLocalMovEquipResidencia = this.idLocalMovEquipResidencia,
            dthrMovEquipResidencia = Date(this.dthrMovEquipResidencia),
            tipoMovEquipResidencia = this.tipoMovEquipResidencia,
            motoristaMovEquipResidencia = this.motoristaMovEquipResidencia,
            veiculoMovEquipResidencia = this.veiculoMovEquipResidencia,
            placaMovEquipResidencia = this.placaMovEquipResidencia,
            observMovEquipResidencia = this.observMovEquipResidencia,
            statusMovEquipResidencia = this.statusMovEquipResidencia,
            statusSendMovEquipResidencia = this.statusSendMovEquipResidencia,
        )
    }
}