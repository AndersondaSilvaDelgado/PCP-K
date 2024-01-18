package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetVeiculoVisitTerc {

    suspend operator fun invoke(veiculo: String, flowApp: FlowApp, pos: Int): Boolean

}