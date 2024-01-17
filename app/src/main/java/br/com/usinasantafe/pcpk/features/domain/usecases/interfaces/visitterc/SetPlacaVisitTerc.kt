package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

interface SetPlacaVisitTerc {

    suspend operator fun invoke(placa: String): Boolean

}