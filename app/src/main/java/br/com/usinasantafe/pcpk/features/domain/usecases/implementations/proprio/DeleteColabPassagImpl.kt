package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeleteColabPassag
import javax.inject.Inject

class DeleteColabPassagImpl @Inject constructor(
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
): DeleteColabPassag {

    override suspend fun invoke(pos: Int): Boolean {
        return try {
            movEquipProprioPassagRepository.deletePassag(pos)
        } catch (exception: Exception) {
            false
        }
        return true
    }

}