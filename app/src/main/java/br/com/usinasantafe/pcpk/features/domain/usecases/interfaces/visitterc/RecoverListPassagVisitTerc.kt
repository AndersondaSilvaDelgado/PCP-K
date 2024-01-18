package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante

interface RecoverListPassagVisitTerc {

    suspend operator fun invoke(typeAddOcupante: TypeAddOcupante, pos: Int): List<String>

}