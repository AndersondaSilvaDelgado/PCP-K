package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.StatusMovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository

import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverNomeColab
import javax.inject.Inject

class RecoverNomeColabImpl @Inject constructor (
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val colabRepository: ColabRepository,
    private val configRepository: ConfigRepository,
): RecoverNomeColab {

    override suspend fun invoke(): String {
        if(configRepository.getConfig().statusMovEquipProprio == StatusMovEquipProprio.ADDMOTORISTA) {
            var matric = movEquipProprioRepository.getMatricMotoristaMovEquipProprio()
            return colabRepository.getColabMatric(matric).nomeColab
        }
        var matric = movEquipProprioPassagRepository.listPassag()[movEquipProprioPassagRepository.countPassag() - 1].nroMatricMovEquipProprioPassag!!
        return colabRepository.getColabMatric(matric).nomeColab
    }

}