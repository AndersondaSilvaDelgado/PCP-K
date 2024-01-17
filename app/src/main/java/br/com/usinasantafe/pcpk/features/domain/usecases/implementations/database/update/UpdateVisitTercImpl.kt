package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.update

import br.com.usinasantafe.pcpk.common.utils.FlagUpdate
import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import br.com.usinasantafe.pcpk.common.utils.TEXT_SUCESS_UPDATE
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateTerceiro
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateVisitTerc
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateVisitante
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateVisitTercImpl @Inject constructor(
    private val updateTerceiro: UpdateTerceiro,
    private val updateVisitante: UpdateVisitante,
) : UpdateVisitTerc {

    override suspend fun invoke(count: Int, size: Int): Flow<Result<ResultUpdateDatabase>> = flow {
        val size = size
        var count = count
        updateTerceiro(count, size).collect { result ->
            result.fold(
                onSuccess = {
                    emit(Result.success(it))
                    count = it.count;
                },
                onFailure = { exception -> emit(Result.failure(exception)) }
            )
        }
        updateVisitante(count, size).collect { result ->
            result.fold(
                onSuccess = {
                    emit(Result.success(it))
                    count = it.count;
                },
                onFailure = { exception -> emit(Result.failure(exception)) }
            )
        }
        emit(Result.success(ResultUpdateDatabase(size, TEXT_SUCESS_UPDATE, size)))
    }

}