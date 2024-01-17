package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository

import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverNomeColab
import javax.inject.Inject

class RecoverNomeColabImpl @Inject constructor (
    private val colabRepository: ColabRepository,
): RecoverNomeColab {

    override suspend fun invoke(matricColab: String): String {
        return colabRepository.getColabMatric(matricColab.toLong()).nomeColab
    }

}