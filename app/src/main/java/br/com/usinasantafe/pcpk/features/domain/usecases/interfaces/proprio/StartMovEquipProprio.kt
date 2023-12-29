package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface StartMovEquipProprio {

    suspend operator fun invoke(typeMov: TypeMov): Boolean

}