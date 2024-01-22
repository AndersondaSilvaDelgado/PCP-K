package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetObservProprio {

    suspend operator fun invoke(observ: String?, flowApp: FlowApp, pos: Int): Boolean

}