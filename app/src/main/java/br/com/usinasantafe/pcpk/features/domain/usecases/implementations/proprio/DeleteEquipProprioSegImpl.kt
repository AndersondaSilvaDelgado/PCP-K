package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeleteEquipProprioSeg
import javax.inject.Inject

class DeleteEquipProprioSegImpl @Inject constructor(
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
): DeleteEquipProprioSeg {

    override suspend fun invoke(pos: Int): Boolean {
        return try {
            movEquipProprioSegRepository.deleteEquipSeg(pos)
        } catch (exception: Exception) {
            false
        }
        return true
    }

}