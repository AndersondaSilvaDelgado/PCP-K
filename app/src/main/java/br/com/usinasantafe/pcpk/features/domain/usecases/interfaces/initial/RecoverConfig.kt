package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config

interface RecoverConfig {

    suspend operator fun invoke(): Config?

}