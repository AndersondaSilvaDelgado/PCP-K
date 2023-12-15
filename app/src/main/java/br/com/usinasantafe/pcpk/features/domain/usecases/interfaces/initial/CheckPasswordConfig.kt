package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial

interface CheckPasswordConfig {

    suspend operator fun invoke(senha: String): Boolean

}