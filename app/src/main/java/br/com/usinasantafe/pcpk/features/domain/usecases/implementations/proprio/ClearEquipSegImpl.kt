package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.ClearEquipSeg
import javax.inject.Inject

class ClearEquipSegImpl @Inject constructor(
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
): ClearEquipSeg {

    override suspend fun invoke(): Boolean {
        return try {
            movEquipProprioSegRepository.clearEquipSeg()
        } catch (exception: Exception) {
            false
        }
        return true
    }

}