package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel

interface RecoverListMovEquipProprioOpen {

    suspend operator fun invoke(): List<MovEquipProprioModel>

}