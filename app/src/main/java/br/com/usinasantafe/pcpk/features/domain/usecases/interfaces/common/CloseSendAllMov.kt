package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

interface CloseSendAllMov {

    suspend operator fun invoke(): Boolean

}