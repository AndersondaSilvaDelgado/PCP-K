package br.com.usinasantafe.pcpk.features.domain.usecases.database

import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import br.com.usinasantafe.pcpk.common.utils.TB_VISITANTE
import br.com.usinasantafe.pcpk.common.utils.TEXT_CLEAR_TB
import br.com.usinasantafe.pcpk.common.utils.TEXT_RECEIVE_WS_TB
import br.com.usinasantafe.pcpk.common.utils.TEXT_SAVE_DATA_TB
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UpdateVisitante {
    suspend operator fun invoke(contador: Int = 0, qtde: Int = 3): Flow<Result<ResultUpdateDatabase>>
}

class UpdateVisitanteImpl @Inject constructor(
    private val visitanteRepository: VisitanteRepository,
    private val configRepository: ConfigRepository,
): UpdateVisitante {

    override suspend fun invoke(contador: Int, qtde: Int): Flow<Result<ResultUpdateDatabase>> = flow {
        var contUpdate = contador
        emit(Result.success(ResultUpdateDatabase(++contUpdate, TEXT_CLEAR_TB + TB_VISITANTE, qtde)))
        visitanteRepository.deleteAllVisitante()
        emit(Result.success(ResultUpdateDatabase(++contUpdate, TEXT_RECEIVE_WS_TB + TB_VISITANTE, qtde)))
        val config = configRepository.getConfig()
        visitanteRepository.recoverAllVisitante(config.nroAparelhoConfig!!)
            .collect{ result ->
                result.fold(
                    onSuccess = { list ->
                        emit(Result.success(ResultUpdateDatabase(++contUpdate, TEXT_SAVE_DATA_TB + TB_VISITANTE, qtde)))
                        visitanteRepository.addAllVisitante(list)
                    },
                    onFailure = { exception -> emit(Result.failure(Throwable("$exception - $TB_VISITANTE"))) }
                )
            }
    }

}