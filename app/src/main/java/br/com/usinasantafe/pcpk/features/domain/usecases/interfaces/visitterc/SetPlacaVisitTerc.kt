package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetPlacaVisitTerc {

    suspend operator fun invoke(placa: String, flowApp: FlowApp, pos: Int): Boolean

}