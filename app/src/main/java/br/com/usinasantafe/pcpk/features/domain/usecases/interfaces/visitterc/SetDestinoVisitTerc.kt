package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

interface SetDestinoVisitTerc {

    suspend operator fun invoke(destino: String): Boolean

}