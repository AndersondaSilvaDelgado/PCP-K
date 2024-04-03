package br.com.usinasantafe.pcpk.presenter.initial.config

data class ConfigModel(
    val nroAparelho: Long,
    val senha: String,
)

fun br.com.usinasantafe.pcpk.domain.entities.variable.Config.toConfigModel(): ConfigModel {
    return with(this){
        ConfigModel(
            nroAparelho = this.nroAparelhoConfig!!,
            senha = this.passwordConfig!!,
        )
    }
}
