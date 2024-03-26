package br.com.usinasantafe.pcpk.features.domain.usecases.common

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface CheckMovOpen {
    suspend operator fun invoke(): Boolean
}

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