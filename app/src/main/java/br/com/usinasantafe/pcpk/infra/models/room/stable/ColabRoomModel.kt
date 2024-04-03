package br.com.usinasantafe.pcpk.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.TB_COLAB
import br.com.usinasantafe.pcpk.domain.entities.stable.Colab
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TB_COLAB)
data class ColabRoomModel (
    @PrimaryKey
    val matricColab: Long,
    val nomeColab: String,
)

fun ColabRoomModel.toColab(): br.com.usinasantafe.pcpk.domain.entities.stable.Colab {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.stable.Colab(
            matricColab = this.matricColab,
            nomeColab = this.nomeColab,
        )
    }
}

fun br.com.usinasantafe.pcpk.domain.entities.stable.Colab.toColabModel(): ColabRoomModel{
    return with(this){
        ColabRoomModel(
            matricColab = this.matricColab,
            nomeColab = this.nomeColab,
        )
    }
}