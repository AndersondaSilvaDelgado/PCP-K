package br.com.usinasantafe.pcpk.features.domain.usecases.common

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import javax.inject.Inject

interface CheckMatricColab {
    suspend operator fun invoke(matric: String): Boolean
}

class CheckMatricColabImpl @Inject constructor (
    private val colabRepository: ColabRepository
): CheckMatricColab {

    override suspend fun invoke(matric: String): Boolean {
        return colabRepository.checkColabMatric(matric.toLong())
    }

}