package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.StartMovEquipVisitTerc
import javax.inject.Inject

class StartMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): StartMovEquipVisitTerc {
    override suspend fun invoke(): Boolean {
        return try {
            movEquipVisitTercRepository.startMovEquipVisitTerc()
        } catch (e: Exception){
            false
        }
    }

}