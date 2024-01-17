package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeletePassagColab
import javax.inject.Inject

class DeletePassagColabImpl @Inject constructor(
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
): DeletePassagColab {

    override suspend fun invoke(pos: Int): Boolean {
        return try {
            movEquipProprioPassagRepository.deletePassag(pos)
        } catch (exception: Exception) {
            false
        }
    }

}