package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeleteEquipSeg
import javax.inject.Inject

class DeleteEquipSegImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
) : DeleteEquipSeg {

    override suspend fun invoke(posList: Int, typeAddEquip: TypeAddEquip, pos: Int): Boolean {
        return when (typeAddEquip) {
            TypeAddEquip.ADDVEICULO,
            TypeAddEquip.ADDVEICULOSEG -> movEquipProprioSegRepository.deleteEquipSeg(posList)
            TypeAddEquip.CHANGEVEICULO,
            TypeAddEquip.CHANGEVEICULOSEG -> {
                val movEquip = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
                movEquipProprioSegRepository.deleteEquipSeg(posList, movEquip.idMovEquipProprio!!)
            }
        }
    }

}