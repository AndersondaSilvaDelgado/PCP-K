package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetVeiculoResidencia {

    suspend operator fun invoke(veiculo: String, flowApp: FlowApp, pos: Int): Boolean

}