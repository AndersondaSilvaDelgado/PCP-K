package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.RecoverNomeColab
import javax.inject.Inject

class RecoverNomeColabImpl @Inject constructor (
    private val colabRepository: ColabRepository
): RecoverNomeColab {

    override suspend fun invoke(matricColab: Long): String {
        return colabRepository.getColabMatric(matricColab).nomeColab
    }

}