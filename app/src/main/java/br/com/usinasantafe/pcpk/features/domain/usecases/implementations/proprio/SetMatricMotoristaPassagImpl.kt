package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetMatricMotoristaPassag
import javax.inject.Inject

class SetMatricMotoristaPassagImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): SetMatricMotoristaPassag {

    override suspend fun invoke(matricColab: String): Boolean {
        try {
            if(movEquipProprioRepository.checkAddMotoristaMovEquipProprio()){
                return movEquipProprioRepository.setMotoristaMovEquipProprio(matricColab.toLong())
            }
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}