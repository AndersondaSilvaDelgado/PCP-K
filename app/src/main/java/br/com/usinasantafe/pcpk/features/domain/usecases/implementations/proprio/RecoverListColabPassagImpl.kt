package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListColabPassag
import javax.inject.Inject

class RecoverListColabPassagImpl @Inject constructor(
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val colabRepository: ColabRepository,
): RecoverListColabPassag {

    override suspend fun invoke(): List<String> {
        return movEquipProprioPassagRepository.listPassag().map {
            val colab = colabRepository.getColabMatric(it.nroMatricMovEquipProprioPassag!!)
            return@map "${colab.matricColab} - ${colab.nomeColab}"
        }
    }

}