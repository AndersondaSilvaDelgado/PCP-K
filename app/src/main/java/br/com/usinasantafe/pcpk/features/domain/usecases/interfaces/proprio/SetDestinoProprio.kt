package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetDestinoProprio {

    suspend operator fun invoke(destino: String, flowApp: FlowApp, pos: Int): Boolean

}