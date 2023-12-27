package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database

import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow

interface UpdateAllDataBase {

    suspend operator fun invoke(count: Int = 0, size: Int = 16): Flow<Result<ResultUpdateDatabase>>

}