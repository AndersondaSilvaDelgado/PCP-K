package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

import br.com.usinasantafe.pcpk.features.presenter.model.HeaderModel

interface RecoverHeader {

    suspend operator fun invoke(): HeaderModel

}