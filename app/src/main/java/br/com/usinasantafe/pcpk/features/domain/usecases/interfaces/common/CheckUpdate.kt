package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

interface CheckUpdate {

    suspend operator fun invoke(): Boolean

}