package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.FlowApp

interface SetNotaFiscalProprio {

    suspend operator fun invoke(notaFiscal: String, flowApp: FlowApp, pos: Int): Boolean

}