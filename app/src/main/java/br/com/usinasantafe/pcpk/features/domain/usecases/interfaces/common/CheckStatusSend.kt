package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

interface CheckStatusSend {

    suspend operator fun invoke(): Boolean

}