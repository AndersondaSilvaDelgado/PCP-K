package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.StartMovEquipProprio
import javax.inject.Inject

class StartMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): StartMovEquipProprio {

    override suspend fun invoke(typeMov: TypeMov): Boolean {
        try {
            movEquipProprioRepository.startMovEquipProprio(typeMov)
        } catch (e: Exception){
            return false
        }
        return true
    }

}