package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database

import br.com.usinasantafe.pcpk.features.presenter.model.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow

interface UpdateAllDataBase {

    suspend operator fun invoke(count: Int = 0, size: Int = 61): Flow<ResultUpdateDatabase>

}