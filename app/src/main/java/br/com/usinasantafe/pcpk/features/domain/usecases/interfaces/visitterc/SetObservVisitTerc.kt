package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface SetObservVisitTerc {

    suspend operator fun invoke(observ: String?, typeMov: TypeMov?, pos: Int, flowApp: FlowApp): Boolean

}