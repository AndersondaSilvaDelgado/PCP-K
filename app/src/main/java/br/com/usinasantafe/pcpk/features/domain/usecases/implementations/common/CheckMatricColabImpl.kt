package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckMatricColab
import javax.inject.Inject

class CheckMatricColabImpl @Inject constructor (
    private val colabRepository: ColabRepository
): CheckMatricColab {
    override suspend fun invoke(matric: String): Boolean {
        return colabRepository.checkColabMatric(matric.toLong())
    }
}