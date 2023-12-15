package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

import br.com.usinasantafe.pcpk.common.utils.PointerStart


interface StartApp {

    suspend operator fun invoke(): PointerStart

}