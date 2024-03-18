package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import android.util.Log
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetNroEquip
import javax.inject.Inject

class SetNroEquipImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val equipRepository: EquipRepository,
): SetNroEquip {

    override suspend fun invoke(nroEquip: String, typeAddEquip: TypeAddEquip, pos: Int): Boolean {
        return try {
            val idEquip = equipRepository.getEquipNro(nroEquip.toLong()).idEquip
            when(typeAddEquip){
                TypeAddEquip.ADDVEICULO -> movEquipProprioRepository.setVeiculoMovEquipProprio(idEquip)
                TypeAddEquip.ADDVEICULOSEG -> movEquipProprioSegRepository.addEquipSeg(idEquip)
                TypeAddEquip.CHANGEVEICULO -> {
                    val movEquip = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
                    movEquipProprioRepository.updateVeiculoMovEquipProprio(idEquip, movEquip)
                }
                TypeAddEquip.CHANGEVEICULOSEG -> {
                    val movEquip = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
                    movEquipProprioSegRepository.addEquipSeg(idEquip, movEquip.idMovEquipProprio!!)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}