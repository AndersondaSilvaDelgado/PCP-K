package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetMotoristaResidencia {

    suspend operator fun invoke(motorista: String, flowApp: FlowApp, pos: Int): Boolean

}