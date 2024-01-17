package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipVisitTercModel

interface RecoverDetalheMovEquipVisitTerc {

    suspend operator fun invoke(pos: Int): DetalheMovEquipVisitTercModel

}