package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

interface CloseSendMovVisitTerc {

    suspend operator fun invoke(pos: Int): Boolean

}