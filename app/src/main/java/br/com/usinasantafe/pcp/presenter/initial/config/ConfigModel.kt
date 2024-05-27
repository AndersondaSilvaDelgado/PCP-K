package br.com.usinasantafe.pcp.presenter.initial.config

data class ConfigModel(
    val nroAparelho: Long,
    val senha: String,
)

fun br.com.usinasantafe.pcp.domain.entities.variable.Config.toConfigModel(): ConfigModel {
    return with(this){
        ConfigModel(
            nroAparelho = this.nroAparelhoConfig!!,
            senha = this.passwordConfig!!,
        )
    }
}
