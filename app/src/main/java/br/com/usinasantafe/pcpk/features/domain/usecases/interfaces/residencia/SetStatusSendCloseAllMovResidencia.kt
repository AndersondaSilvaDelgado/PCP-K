package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

interface SetStatusSendCloseAllMovResidencia {

    suspend operator fun invoke(): Boolean

}