package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.common.utils.PointerStart
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartApp
import javax.inject.Inject

class StartAppImpl @Inject constructor (
): StartApp {

    override suspend fun invoke(): PointerStart {
        return PointerStart.MENUINICIAL
    }

}