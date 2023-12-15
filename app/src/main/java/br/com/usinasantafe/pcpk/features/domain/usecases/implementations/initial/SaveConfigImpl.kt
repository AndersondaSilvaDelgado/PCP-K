package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.SaveConfig
import br.com.usinasantafe.pcpk.features.presenter.model.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository
): SaveConfig {
    override suspend fun invoke(nroAparelho: String, senha: String): Flow<ResultUpdateDatabase> {
        return flow {
//            recoverEquip(nroEquip)
//                .collect{
//                    emit(it)
//                    if(it.describe == TEXT_FINISH_UPDATE){
//                        configRepository.saveConfig(nroEquip, senha)
//                        emit(ResultUpdateDatabase(100, "Termino de Salvamento de Configurações", 100))
//                    }
//                }
            emit(ResultUpdateDatabase(100, "Termino de Salvamento de Configurações", 100))
        }
    }
}