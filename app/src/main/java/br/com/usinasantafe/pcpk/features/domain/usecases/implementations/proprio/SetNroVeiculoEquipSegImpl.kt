package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.StatusMovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.entities.stable.Equip
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioSeg
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetNroVeiculoEquipSeg
import javax.inject.Inject

class SetNroVeiculoEquipSegImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val equipRepository: EquipRepository,
    private val configRepository: ConfigRepository,
): SetNroVeiculoEquipSeg {

    override suspend fun invoke(nroEquip: String): Boolean {
        return try {
            if(configRepository.getConfig().statusMovEquipProprio == StatusMovEquipProprio.ADDVEICULO){
                movEquipProprioRepository.setVeiculoMovEquipProprio(equipRepository.getEquipNro(nroEquip.toLong()).idEquip)
            } else {
                movEquipProprioSegRepository.addEquipSeg(MovEquipProprioSeg(idEquipMovEquipProprioSeg = equipRepository.getEquipNro(nroEquip.toLong()).idEquip))
            }
        } catch (exception: Exception) {
            false
        }
    }

}