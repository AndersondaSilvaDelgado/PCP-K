package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

interface SetStatusSendCloseMovVisitTerc {

    suspend operator fun invoke(pos: Int): Boolean

}