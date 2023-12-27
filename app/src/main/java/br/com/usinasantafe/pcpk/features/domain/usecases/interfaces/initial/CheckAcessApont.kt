package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial

interface CheckAcessApont {

    suspend operator fun invoke(): Boolean

}