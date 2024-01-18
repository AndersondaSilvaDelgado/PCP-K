package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

interface SetStatusSendCloseMovResidencia {

    suspend operator fun invoke(pos: Int): Boolean

}