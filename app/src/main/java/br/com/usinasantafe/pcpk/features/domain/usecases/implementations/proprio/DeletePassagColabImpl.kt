package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeletePassagColab
import javax.inject.Inject

class DeletePassagColabImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
): DeletePassagColab {

    override suspend fun invoke(posList: Int, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean {
        return when (typeAddOcupante) {
            TypeAddOcupante.ADDMOTORISTA,
            TypeAddOcupante.ADDPASSAGEIRO -> movEquipProprioPassagRepository.deletePassag(posList)
            TypeAddOcupante.CHANGEMOTORISTA,
            TypeAddOcupante.CHANGEPASSAGEIRO -> {
                val movEquip = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
                movEquipProprioPassagRepository.deletePassag(posList, movEquip.idMovEquipProprio!!)
            }
        }
    }

}