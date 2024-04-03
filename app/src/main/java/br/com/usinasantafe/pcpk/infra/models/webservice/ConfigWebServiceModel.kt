package br.com.usinasantafe.pcpk.infra.models.webservice

import br.com.usinasantafe.pcpk.domain.entities.variable.Config
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
data class ConfigWebServiceModelOutput(
    val nroAparelho: Long,
    val version: String,
)

@Serializable
data class ConfigWebServiceModelInput(
    var idBD: Long,
)


fun br.com.usinasantafe.pcpk.domain.entities.variable.Config.toConfigWebServiceModel(): ConfigWebServiceModelOutput {
    return with(this){
        ConfigWebServiceModelOutput(
            nroAparelho = this.nroAparelhoConfig!!,
            version = this.version!!,
        )
    }
}

fun ConfigWebServiceModelInput.toConfig(): br.com.usinasantafe.pcpk.domain.entities.variable.Config {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.Config(
            idBDConfig = this.idBD
        )
    }
}