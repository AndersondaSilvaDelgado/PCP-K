package br.com.usinasantafe.pcpk.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.TB_TERCEIRO
import br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TB_TERCEIRO)
data class TerceiroRoomModel (
    @PrimaryKey(autoGenerate = true)
    val idTerceiro: Long,
    val idBDTerceiro: Long,
    val cpfTerceiro: String,
    val nomeTerceiro: String,
    val empresaTerceiro: String,
)

fun TerceiroRoomModel.toTerceiro(): br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro(
            idTerceiro = this.idTerceiro,
            idBDTerceiro = this.idBDTerceiro,
            cpfTerceiro = this.cpfTerceiro,
            nomeTerceiro = this.nomeTerceiro,
            empresaTerceiro = this.empresaTerceiro,
        )
    }
}

fun br.com.usinasantafe.pcpk.domain.entities.stable.Terceiro.toTerceiroModel(): TerceiroRoomModel{
    return with(this){
        TerceiroRoomModel(
            idTerceiro = this.idTerceiro,
            idBDTerceiro = this.idBDTerceiro,
            cpfTerceiro = this.cpfTerceiro,
            nomeTerceiro = this.nomeTerceiro,
            empresaTerceiro = this.empresaTerceiro,
        )
    }
}