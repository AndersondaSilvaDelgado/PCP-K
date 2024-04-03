package br.com.usinasantafe.pcpk.infra.models.room.variable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.StatusData
import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.utils.TB_MOV_EQUIP_VISIT_TERC
import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Entity(tableName = TB_MOV_EQUIP_VISIT_TERC)
data class MovEquipVisitTercRoomModel(
    @PrimaryKey(autoGenerate = true)
    var idMovEquipVisitTerc: Long? = null,
    var nroMatricVigiaMovEquipVisitTerc: Long,
    var idLocalMovEquipVisitTerc: Long,
    var tipoMovEquipVisitTerc: TypeMov,
    var idVisitTercMovEquipVisitTerc: Long,
    var tipoVisitTercMovEquipVisitTerc: TypeVisitTerc,
    var dthrMovEquipVisitTerc: Long,
    var veiculoMovEquipVisitTerc: String,
    var placaMovEquipVisitTerc: String,
    var destinoMovEquipVisitTerc: String?,
    var observMovEquipVisitTerc: String?,
    var statusMovEquipVisitTerc: StatusData,
    var statusSendMovEquipVisitTerc: StatusSend,
)

fun br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc.entityToMovEquipVisitTercRoomModel(matricVigia: Long, idLocal: Long): MovEquipVisitTercRoomModel{
    return with(this){
        MovEquipVisitTercRoomModel(
            idMovEquipVisitTerc = this.idMovEquipVisitTerc,
            nroMatricVigiaMovEquipVisitTerc = matricVigia,
            idLocalMovEquipVisitTerc = idLocal,
            tipoMovEquipVisitTerc = this.tipoMovEquipVisitTerc!!,
            idVisitTercMovEquipVisitTerc = this.idVisitTercMovEquipVisitTerc!!,
            tipoVisitTercMovEquipVisitTerc = this.tipoVisitTercMovEquipVisitTerc!!,
            dthrMovEquipVisitTerc = Date().time,
            veiculoMovEquipVisitTerc = this.veiculoMovEquipVisitTerc!!,
            placaMovEquipVisitTerc = this.placaMovEquipVisitTerc!!,
            destinoMovEquipVisitTerc = this.destinoMovEquipVisitTerc,
            observMovEquipVisitTerc = this.observMovEquipVisitTerc,
            statusMovEquipVisitTerc = this.statusMovEquipVisitTerc,
            statusSendMovEquipVisitTerc = this.statusSendMovEquipVisitTerc,
        )
    }
}

fun MovEquipVisitTercRoomModel.modelRoomToMovEquipVisitTerc(): br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc(
            idMovEquipVisitTerc = this.idMovEquipVisitTerc,
            nroMatricVigiaMovEquipVisitTerc = this.nroMatricVigiaMovEquipVisitTerc,
            idLocalMovEquipVisitTerc = this.idLocalMovEquipVisitTerc,
            tipoMovEquipVisitTerc = this.tipoMovEquipVisitTerc,
            idVisitTercMovEquipVisitTerc = this.idVisitTercMovEquipVisitTerc,
            tipoVisitTercMovEquipVisitTerc = this.tipoVisitTercMovEquipVisitTerc,
            dthrMovEquipVisitTerc = Date(this.dthrMovEquipVisitTerc),
            veiculoMovEquipVisitTerc = this.veiculoMovEquipVisitTerc,
            placaMovEquipVisitTerc = this.placaMovEquipVisitTerc,
            destinoMovEquipVisitTerc = this.destinoMovEquipVisitTerc,
            observMovEquipVisitTerc = this.observMovEquipVisitTerc,
            statusMovEquipVisitTerc = this.statusMovEquipVisitTerc,
            statusSendMovEquipVisitTerc = this.statusSendMovEquipVisitTerc,
        )
    }
}