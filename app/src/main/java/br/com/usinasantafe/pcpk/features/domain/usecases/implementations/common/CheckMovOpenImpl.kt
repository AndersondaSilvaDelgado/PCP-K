package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckMovOpen
import javax.inject.Inject

class CheckMovOpenImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): CheckMovOpen {
    override suspend fun invoke(): Boolean {
        if (movEquipProprioRepository.listMovEquipProprioStarted().isNotEmpty()) return false
        if (movEquipVisitTercRepository.listMovEquipVisitTercStarted().isNotEmpty()) return false
        if (movEquipResidenciaRepository.listMovEquipResidenciaStarted().isNotEmpty()) return false
        return true
    }
}