package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.StatusMovEquipProprio

interface GetStatusMov {

    suspend operator fun invoke(): StatusMovEquipProprio

}