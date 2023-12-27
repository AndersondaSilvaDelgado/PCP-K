package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.LocalRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverListLocal
import javax.inject.Inject

class RecoverListLocalImpl @Inject constructor(
    private val localRepository: LocalRepository,
): RecoverListLocal {

    override suspend fun invoke(): List<String> {
        return localRepository.listAllLocal().map { it.descrLocal }
    }

}