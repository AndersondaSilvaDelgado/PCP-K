package br.com.usinasantafe.pcpk.features.presenter.model

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config

data class ConfigModelOutput(
    val nomeVigia: String? = null,
    val local: String? = null,
    val nroAparelho: Long? = null,
    val senha: String? = null,
)

fun Config.toConfigModel(): ConfigModelOutput {
    return with(this){
        ConfigModelOutput(
            nroAparelho = this.nroAparelhoConfig,
            senha = this.passwordConfig,
        )
    }
}
