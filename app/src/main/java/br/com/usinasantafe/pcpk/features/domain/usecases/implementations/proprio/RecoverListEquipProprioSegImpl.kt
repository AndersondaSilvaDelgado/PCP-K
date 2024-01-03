package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListEquipProprioSeg
import javax.inject.Inject

class RecoverListEquipProprioSegImpl @Inject constructor(
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val equipRepository: EquipRepository,
): RecoverListEquipProprioSeg {

    override suspend fun invoke(): List<String> {
        return movEquipProprioSegRepository.listEquipSeg().map { equipRepository.getEquipId(it.idEquipMovEquipProprioSeg!!).nroEquip.toString() }
    }

}