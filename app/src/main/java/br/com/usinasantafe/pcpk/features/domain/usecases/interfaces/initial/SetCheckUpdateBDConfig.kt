package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial

import br.com.usinasantafe.pcpk.common.utils.FlagUpdate

interface SetCheckUpdateBDConfig {

    suspend operator fun invoke(flagUpdate: FlagUpdate)

}