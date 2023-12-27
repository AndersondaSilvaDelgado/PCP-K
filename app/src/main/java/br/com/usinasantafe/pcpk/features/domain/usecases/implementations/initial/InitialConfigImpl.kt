package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.BuildConfig
import br.com.usinasantafe.pcpk.common.utils.TEXT_FINISH_CONFIG
import br.com.usinasantafe.pcpk.common.utils.TEXT_RECEIVE_TOKEN
import br.com.usinasantafe.pcpk.common.utils.TEXT_SAVE_DATA_CONFIG
import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.InitialConfig
import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InitialConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository,
): InitialConfig {
    override suspend fun invoke(
        nroAparelho: String,
        password: String,
        contador: Int,
        qtde: Int
    ): Flow<Result<ResultUpdateDatabase>> = flow {
        val config = Config()
        var contRecoverToken = contador
        config.nroAparelhoConfig = nroAparelho.toLong()
        config.passwordConfig = password
        config.version = BuildConfig.VERSION_NAME
        emit(Result.success(ResultUpdateDatabase(++contRecoverToken, TEXT_RECEIVE_TOKEN, qtde)))
        configRepository.recoverToken(config)
            .catch { exception -> emit(Result.failure(exception)) }
            .collect{ result ->
                result.fold(
                    onSuccess = {
                        config.idBDConfig = it.idBDConfig
                        emit(Result.success(ResultUpdateDatabase(++contRecoverToken, TEXT_SAVE_DATA_CONFIG, qtde)))
                        configRepository.saveConfig(config)
                        emit(Result.success(ResultUpdateDatabase(qtde, TEXT_FINISH_CONFIG, qtde)))
                    },
                    onFailure = { exception -> emit(Result.failure(exception)) }
                )
            }
    }
}