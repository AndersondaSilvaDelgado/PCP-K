package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante

interface SetMatricMotoristaPassag {

    suspend operator fun invoke(matricColab: String, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean

}