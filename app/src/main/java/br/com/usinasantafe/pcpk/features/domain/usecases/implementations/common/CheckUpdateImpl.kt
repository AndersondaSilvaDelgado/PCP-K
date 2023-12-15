package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckUpdate
import javax.inject.Inject

class CheckUpdateImpl @Inject constructor (
): CheckUpdate {

    override suspend fun invoke(): Boolean {
        return false
    }
}