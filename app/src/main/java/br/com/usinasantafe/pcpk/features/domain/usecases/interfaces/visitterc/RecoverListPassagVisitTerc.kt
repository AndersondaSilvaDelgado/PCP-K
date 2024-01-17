package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

interface RecoverListPassagVisitTerc {

    suspend operator fun invoke(): List<String>

}