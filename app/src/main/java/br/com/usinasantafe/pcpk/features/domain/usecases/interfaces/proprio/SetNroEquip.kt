package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip

interface SetNroEquip {

    suspend operator fun invoke(nroEquip: String, typeAddEquip: TypeAddEquip, pos: Int): Boolean

}