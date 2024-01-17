package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeleteEquipSeg
import javax.inject.Inject

class DeleteEquipSegImpl @Inject constructor(
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
): DeleteEquipSeg {

    override suspend fun invoke(pos: Int): Boolean {
        return try {
            movEquipProprioSegRepository.deleteEquipSeg(pos)
        } catch (exception: Exception) {
            false
        }
        return true
    }

}