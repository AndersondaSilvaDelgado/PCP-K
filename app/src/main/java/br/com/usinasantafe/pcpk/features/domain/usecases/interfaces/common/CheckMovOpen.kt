package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

interface CheckMovOpen {

    suspend operator fun invoke(): Boolean

}