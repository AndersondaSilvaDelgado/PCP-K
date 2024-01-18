package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetPlacaResidencia {

    suspend operator fun invoke(placa: String, flowApp: FlowApp, pos: Int): Boolean

}