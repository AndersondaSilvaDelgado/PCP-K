package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipProprioModel

interface RecoverDetalheMovEquipProprio {

    suspend operator fun invoke(pos: Int): DetalheMovEquipProprioModel

}