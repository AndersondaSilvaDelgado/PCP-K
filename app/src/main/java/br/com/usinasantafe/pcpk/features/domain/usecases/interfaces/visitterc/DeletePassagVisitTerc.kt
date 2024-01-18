package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante

interface DeletePassagVisitTerc {

    suspend operator fun invoke(posList: Int, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean

}