package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante

interface RecoverListColabPassag {

    suspend operator fun invoke(typeAddOcupante: TypeAddOcupante, pos: Int): List<String>

}