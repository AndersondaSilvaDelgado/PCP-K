package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia

interface SaveMovEquipResidencia {

    suspend operator fun invoke(): Boolean

    suspend operator fun invoke(movEquipResidencia: MovEquipResidencia): Boolean

}