package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel

interface RecoverListMovEquipProprio {

    suspend operator fun invoke(): List<MovEquipProprioModel>

}