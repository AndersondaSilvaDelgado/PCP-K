package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import javax.inject.Inject

interface DeletePassagColab {
    suspend operator fun invoke(posList: Int, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean
}

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