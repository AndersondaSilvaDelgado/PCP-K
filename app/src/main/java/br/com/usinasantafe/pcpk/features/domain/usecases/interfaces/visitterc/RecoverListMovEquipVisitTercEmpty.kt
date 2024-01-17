package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipVisitTercModel

interface RecoverListMovEquipVisitTercEmpty {

    suspend operator fun invoke(): List<MovEquipVisitTercModel>

}