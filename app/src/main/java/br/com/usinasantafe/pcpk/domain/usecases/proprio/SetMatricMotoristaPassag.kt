package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import javax.inject.Inject

interface SetMatricMotoristaPassag {
    suspend operator fun invoke(matricColab: String, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean
}

class SetMatricMotoristaPassagImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
): SetMatricMotoristaPassag {

    override suspend fun invoke(matricColab: String, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean {
        return try {
            when(typeAddOcupante){
                TypeAddOcupante.ADDMOTORISTA -> movEquipProprioRepository.setMotoristaMovEquipProprio(matricColab.toLong())
                TypeAddOcupante.ADDPASSAGEIRO -> movEquipProprioPassagRepository.addPassag(matricColab.toLong())
                TypeAddOcupante.CHANGEMOTORISTA -> {
                    val movEquip = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
                    movEquipProprioRepository.updateMotoristaMovEquipProprio(matricColab.toLong(), movEquip)
                }
                TypeAddOcupante.CHANGEPASSAGEIRO -> {
                    val movEquip = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
                    movEquipProprioPassagRepository.addPassag(matricColab.toLong(), movEquip.idMovEquipProprio!!)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}