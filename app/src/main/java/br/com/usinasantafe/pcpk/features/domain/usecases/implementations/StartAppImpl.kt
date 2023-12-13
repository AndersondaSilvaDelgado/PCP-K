package br.com.usinasantafe.pcpk.features.domain.usecases.implementations

import br.com.usinasantafe.cmm.common.utils.PointerStart
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.StartApp
import javax.inject.Inject

class StartAppImpl @Inject constructor (
): StartApp {

    override suspend fun invoke(): PointerStart {
        return PointerStart.MENUINICIAL
    }

}