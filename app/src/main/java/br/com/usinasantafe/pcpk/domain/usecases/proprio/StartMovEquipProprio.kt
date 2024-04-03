package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioSegRepository
import javax.inject.Inject

interface StartMovEquipProprio {
    suspend operator fun invoke(typeMov: TypeMov): Boolean
}

class StartMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
): StartMovEquipProprio {

    override suspend fun invoke(typeMov: TypeMov): Boolean {
        return try {
            movEquipProprioRepository.startMovEquipProprio(typeMov)
            movEquipProprioSegRepository.clearEquipSeg()
            movEquipProprioPassagRepository.clearPassag()
        } catch (e: Exception){
            false
        }
    }

}