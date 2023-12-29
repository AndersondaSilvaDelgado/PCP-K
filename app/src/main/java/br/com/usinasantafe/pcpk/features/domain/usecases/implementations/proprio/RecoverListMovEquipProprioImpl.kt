package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListMovEquipProprio
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel
import javax.inject.Inject

class RecoverListMovEquipProprioImpl @Inject constructor(
): RecoverListMovEquipProprio {

    override suspend fun invoke(): List<MovEquipProprioModel> {
        TODO("Not yet implemented")
    }

}