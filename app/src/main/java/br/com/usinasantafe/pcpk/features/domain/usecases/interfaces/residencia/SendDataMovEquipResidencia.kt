package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia

interface SendDataMovEquipResidencia {

    suspend operator fun invoke(): Result<List<MovEquipResidencia>>

}