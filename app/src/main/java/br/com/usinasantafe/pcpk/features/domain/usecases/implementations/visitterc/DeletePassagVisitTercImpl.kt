package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.DeletePassagVisitTerc
import javax.inject.Inject

class DeletePassagVisitTercImpl @Inject constructor(
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
): DeletePassagVisitTerc {

    override suspend fun invoke(pos: Int): Boolean {
        return try {
            movEquipVisitTercPassagRepository.deletePassag(pos)
        } catch (exception: Exception) {
            false
        }
    }

}