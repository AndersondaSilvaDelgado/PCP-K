package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.features.presenter.model.DisplayDataVisitTercModel

interface RecoverDataVisitTerc {

    suspend operator fun invoke(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int): DisplayDataVisitTercModel

}