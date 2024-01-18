package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip

interface DeleteEquipSeg {

    suspend operator fun invoke(posList: Int, typeAddEquip: TypeAddEquip, pos: Int): Boolean

}