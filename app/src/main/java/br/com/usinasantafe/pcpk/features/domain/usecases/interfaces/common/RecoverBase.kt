package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

import br.com.usinasantafe.pcpk.features.presenter.model.ConfigModelOutput

interface RecoverBase {

    suspend operator fun invoke(): ConfigModelOutput

}