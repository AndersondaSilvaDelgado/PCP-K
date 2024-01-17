package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio

interface SendDataMovEquipProprio {

    suspend operator fun invoke(): Result<List<MovEquipProprio>>

}