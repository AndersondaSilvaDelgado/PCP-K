package br.com.usinasantafe.pcpk.features.infra.models.webservice

import kotlinx.serialization.Serializable

@Serializable
data class ConfigWebServiceModelOutput(
    val nroAparelho: Long,
    val versionCurrent: String,
    val token: String,
)

@Serializable
data class ConfigWebServiceModelInput(
    var idBD: Long,
)