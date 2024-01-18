package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetDestinoVisitTerc {

    suspend operator fun invoke(destino: String, flowApp: FlowApp, pos: Int): Boolean

}