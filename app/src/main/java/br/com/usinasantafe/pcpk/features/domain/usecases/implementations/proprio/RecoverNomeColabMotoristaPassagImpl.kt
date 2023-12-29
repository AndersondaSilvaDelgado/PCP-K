package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository

import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverNomeColabMotoristaPassag
import javax.inject.Inject

class RecoverNomeColabMotoristaPassagImpl @Inject constructor (
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val colabRepository: ColabRepository,
): RecoverNomeColabMotoristaPassag {

    override suspend fun invoke(): String {
        if(movEquipProprioPassagRepository.countPassag() == 0) {
            var matric = movEquipProprioRepository.getMatricMotoristaMovEquipProprio()
            return colabRepository.getColabMatric(matric).nomeColab
        }
        var matric = movEquipProprioPassagRepository.listPassag()[movEquipProprioPassagRepository.countPassag() - 1].nroMatricMovEquipProprioPassag!!
        return colabRepository.getColabMatric(matric).nomeColab
    }

}