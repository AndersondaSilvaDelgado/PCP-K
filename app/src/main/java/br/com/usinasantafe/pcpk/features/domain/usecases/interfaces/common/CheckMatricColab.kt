package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

interface CheckMatricColab {
    suspend operator fun invoke(matric: String): Boolean

}