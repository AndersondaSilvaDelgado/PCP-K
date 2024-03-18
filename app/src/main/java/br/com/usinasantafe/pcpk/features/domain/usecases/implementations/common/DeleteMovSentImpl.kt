package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.common.utils.dateToDelete
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.DeleteMovSent
import javax.inject.Inject

class DeleteMovSentImpl @Inject constructor (
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): DeleteMovSent {

    override suspend fun invoke() {
        val movEquipProprioList = movEquipProprioRepository.listMovEquipProprioSent()
        for (movEquipProprio in movEquipProprioList) {
            if(movEquipProprio.dthrMovEquipProprio < dateToDelete()) {
                movEquipProprioPassagRepository.deletePassag(movEquipProprio.idMovEquipProprio!!)
                movEquipProprioSegRepository.deleteEquipSeg(movEquipProprio.idMovEquipProprio!!)
                movEquipProprioRepository.deleteMovEquipProprio(movEquipProprio)
            }
        }
        val movEquipVisitTercList = movEquipVisitTercRepository.listMovEquipVisitTercSent()
        for (movEquipVisitTerc in movEquipVisitTercList) {
            if(movEquipVisitTerc.dthrMovEquipVisitTerc < dateToDelete()) {
                movEquipVisitTercPassagRepository.deletePassag(movEquipVisitTerc.idMovEquipVisitTerc!!)
                movEquipVisitTercRepository.deleteMovEquipVisitTerc(movEquipVisitTerc)
            }
        }
        val movEquipResidenciaList = movEquipResidenciaRepository.listMovEquipResidenciaSent()
        for (movEquipResidencia in movEquipResidenciaList) {
            if(movEquipResidencia.dthrMovEquipResidencia < dateToDelete()) {
                movEquipResidenciaRepository.deleteMovEquipResidencia(movEquipResidencia)
            }
        }
    }

}