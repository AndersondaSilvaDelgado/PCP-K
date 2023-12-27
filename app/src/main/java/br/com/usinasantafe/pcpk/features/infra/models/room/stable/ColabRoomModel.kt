package br.com.usinasantafe.pcpk.features.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.common.utils.TB_COLAB
import br.com.usinasantafe.pcpk.features.domain.entities.stable.Colab
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TB_COLAB)
data class ColabRoomModel (
    @PrimaryKey
    val matricColab: Long,
    val nomeColab: String,
)

fun ColabRoomModel.toColab(): Colab {
    return with(this){
        Colab(
            matricColab = this.matricColab,
            nomeColab = this.nomeColab,
        )
    }
}

fun Colab.toColabModel(): ColabRoomModel{
    return with(this){
        ColabRoomModel(
            matricColab = this.matricColab,
            nomeColab = this.nomeColab,
        )
    }
}