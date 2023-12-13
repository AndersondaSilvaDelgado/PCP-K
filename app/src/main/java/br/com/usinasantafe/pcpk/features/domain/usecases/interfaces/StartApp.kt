package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces

import br.com.usinasantafe.cmm.common.utils.PointerStart

interface StartApp {

    suspend operator fun invoke(): PointerStart

}