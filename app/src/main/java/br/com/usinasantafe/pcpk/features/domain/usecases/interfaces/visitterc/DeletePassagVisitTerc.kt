package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

interface DeletePassagVisitTerc {

    suspend operator fun invoke(pos: Int): Boolean

}