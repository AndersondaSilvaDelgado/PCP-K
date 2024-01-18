package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip

interface RecoverListEquipProprioSeg {

    suspend operator fun invoke(typeAddEquip: TypeAddEquip, pos: Int): List<String>

}