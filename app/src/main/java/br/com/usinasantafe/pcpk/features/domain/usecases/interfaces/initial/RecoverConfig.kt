package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial

import br.com.usinasantafe.pcpk.features.presenter.model.ConfigModelOutput

interface RecoverConfig {

    suspend operator fun invoke(): ConfigModelOutput?

}