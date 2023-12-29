package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface SetMatricMotoristaPassag {

    suspend operator fun invoke(matricColab: String): Boolean

}