package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update

import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow

interface UpdateVisitTerc {

    suspend operator fun invoke(count: Int = 0, size: Int = 7): Flow<Result<ResultUpdateDatabase>>

}