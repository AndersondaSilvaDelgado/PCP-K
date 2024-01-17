package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface RecoverNomeColab {

    suspend operator fun invoke(matricColab: String): String

}