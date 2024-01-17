package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

interface SetVeiculoVisitTerc {

    suspend operator fun invoke(veiculo: String): Boolean

}