package br.com.usinasantafe.pcpk.features.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.common.utils.TB_MOV_EQUIP_RESIDENCIA
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
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

fun MovEquipResidencia.entityToMovEquipResidenciaRoomModel(matricVigia: Long, idLocal: Long): MovEquipResidenciaRoomModel{
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

fun MovEquipResidenciaRoomModel.modelRoomToMovEquipResidencia(): MovEquipResidencia {
    return with(this){
        MovEquipResidencia(
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