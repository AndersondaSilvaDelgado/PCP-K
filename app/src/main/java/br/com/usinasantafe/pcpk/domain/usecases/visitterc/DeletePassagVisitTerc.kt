package br.com.usinasantafe.pcpk.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface DeletePassagVisitTerc {
    suspend operator fun invoke(posList: Int, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean
}

class DeletePassagVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
): DeletePassagVisitTerc {

    override suspend fun invoke(posList: Int, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean {
        return when(typeAddOcupante) {
            TypeAddOcupante.ADDMOTORISTA,
            TypeAddOcupante.ADDPASSAGEIRO -> movEquipVisitTercPassagRepository.deletePassag(posList)
            TypeAddOcupante.CHANGEMOTORISTA,
            TypeAddOcupante.CHANGEPASSAGEIRO -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                movEquipVisitTercPassagRepository.deletePassag(posList, movEquip.idMovEquipVisitTerc!!)
            }
        }
    }

}