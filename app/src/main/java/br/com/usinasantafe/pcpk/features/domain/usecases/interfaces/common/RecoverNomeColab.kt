package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

interface RecoverNomeColab {

    suspend operator fun invoke(matricColab: Long): String

}