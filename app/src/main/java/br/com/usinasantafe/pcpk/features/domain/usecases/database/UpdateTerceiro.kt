package br.com.usinasantafe.pcpk.features.domain.usecases.database

import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import br.com.usinasantafe.pcpk.common.utils.TB_TERCEIRO
import br.com.usinasantafe.pcpk.common.utils.TEXT_CLEAR_TB
import br.com.usinasantafe.pcpk.common.utils.TEXT_RECEIVE_WS_TB
import br.com.usinasantafe.pcpk.common.utils.TEXT_SAVE_DATA_TB
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UpdateTerceiro {
    suspend operator fun invoke(contador: Int = 0, qtde: Int = 3): Flow<Result<ResultUpdateDatabase>>
}

class UpdateTerceiroImpl @Inject constructor(
    private val terceiroRepository: TerceiroRepository,
    private val configRepository: ConfigRepository,
): UpdateTerceiro {

    override suspend fun invoke(contador: Int, qtde: Int): Flow<Result<ResultUpdateDatabase>> = flow {
        var contUpdate = contador
        emit(Result.success(ResultUpdateDatabase(++contUpdate, TEXT_CLEAR_TB + TB_TERCEIRO, qtde)))
        terceiroRepository.deleteAllTerceiro()
        emit(Result.success(ResultUpdateDatabase(++contUpdate, TEXT_RECEIVE_WS_TB + TB_TERCEIRO, qtde)))
        val config = configRepository.getConfig()
        terceiroRepository.recoverAllTerceiro(config.nroAparelhoConfig!!)
            .collect{ result ->
                result.fold(
                    onSuccess = { list ->
                        emit(Result.success(ResultUpdateDatabase(++contUpdate, TEXT_SAVE_DATA_TB + TB_TERCEIRO, qtde)))
                        terceiroRepository.addAllTerceiro(list)
                    },
                    onFailure = { exception -> emit(Result.failure(Throwable("$exception - $TB_TERCEIRO"))) }
                )
            }
    }

}