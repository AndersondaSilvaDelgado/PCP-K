package br.com.usinasantafe.pcpk.infra.models.room.stable

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.usinasantafe.pcpk.utils.TB_VISITANTE
import br.com.usinasantafe.pcpk.domain.entities.stable.Visitante
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = TB_VISITANTE)
data class VisitanteRoomModel(
    @PrimaryKey
    val idVisitante: Long,
    val cpfVisitante: String,
    val nomeVisitante: String,
    val empresaVisitante: String,
)

fun VisitanteRoomModel.toVisitante(): br.com.usinasantafe.pcpk.domain.entities.stable.Visitante {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.stable.Visitante(
            idVisitante = this.idVisitante,
            cpfVisitante = this.cpfVisitante,
            nomeVisitante = this.nomeVisitante,
            empresaVisitante = this.empresaVisitante,
        )
    }
}

fun br.com.usinasantafe.pcpk.domain.entities.stable.Visitante.toVisitanteModel(): VisitanteRoomModel{
    return with(this){
        VisitanteRoomModel(
            idVisitante = this.idVisitante,
            cpfVisitante = this.cpfVisitante,
            nomeVisitante = this.nomeVisitante,
            empresaVisitante = this.empresaVisitante,
        )
    }
}