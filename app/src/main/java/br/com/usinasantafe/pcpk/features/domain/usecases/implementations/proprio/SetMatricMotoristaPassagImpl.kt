package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetMatricMotoristaPassag
import javax.inject.Inject

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
                    val movEquip = movEquipProprioRepository.listMovEquipProprioOpen()[pos]
                    movEquipProprioRepository.updateMotoristaMovEquipProprio(matricColab.toLong(), movEquip)
                }
                TypeAddOcupante.CHANGEPASSAGEIRO -> {
                    val movEquip = movEquipProprioRepository.listMovEquipProprioOpen()[pos]
                    movEquipProprioPassagRepository.addPassag(matricColab.toLong(), movEquip.idMovEquipProprio!!)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}