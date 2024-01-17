package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

import br.com.usinasantafe.pcpk.common.utils.StatusSend

interface SetStatusSendConfig {

    suspend operator fun invoke(statusSend: StatusSend)

}