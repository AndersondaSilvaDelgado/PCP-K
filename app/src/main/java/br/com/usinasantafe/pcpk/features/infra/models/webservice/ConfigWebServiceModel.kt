package br.com.usinasantafe.pcpk.features.infra.models.webservice

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
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


fun Config.toConfigWebServiceModel(): ConfigWebServiceModelOutput {
    return with(this){
        ConfigWebServiceModelOutput(
            nroAparelho = this.nroAparelhoConfig!!,
            version = this.version!!,
        )
    }
}

fun ConfigWebServiceModelInput.toConfig(): Config {
    return with(this){
        Config(
            idBDConfig = this.idBD
        )
    }
}