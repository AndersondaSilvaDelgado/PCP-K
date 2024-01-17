package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

interface CheckCPFVisitTerc {

    suspend operator fun invoke(cpf: String): Boolean

}