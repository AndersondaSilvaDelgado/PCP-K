package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.StatusMovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioPassag
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetMatricMotoristaPassag
import javax.inject.Inject

class SetMatricMotoristaPassagImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val configRepository: ConfigRepository,
): SetMatricMotoristaPassag {

    override suspend fun invoke(matricColab: String): Boolean {
        return try {
            if(configRepository.getConfig().statusMovEquipProprio == StatusMovEquipProprio.ADDMOTORISTA){
                movEquipProprioRepository.setMotoristaMovEquipProprio(matricColab.toLong())
            } else {
                movEquipProprioPassagRepository.addPassag(MovEquipProprioPassag(nroMatricMovEquipProprioPassag = matricColab.toLong()))
            }
        } catch (exception: Exception) {
            false
        }
    }

}