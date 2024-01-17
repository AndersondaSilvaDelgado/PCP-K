package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

interface SetStatusSendCloseAllMov {

    suspend operator fun invoke(): Boolean

}