package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.recover

import br.com.usinasantafe.pcpk.common.utils.TEXT_FINISH_UPDATE
import br.com.usinasantafe.pcpk.common.utils.TEXT_SAVE_DATA_TB
import br.com.usinasantafe.pcpk.common.utils.WEB_RETURN_CLEAR_EQUIP
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.recover.RecoverToken
import br.com.usinasantafe.pcpk.features.presenter.model.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecoverTokenImpl @Inject constructor(
    private val configRepository: ConfigRepository,
): RecoverToken {
    override suspend fun invoke(
        nroAparelho: String,
        contador: Int,
        qtde: Int
    ): Flow<ResultUpdateDatabase> {
        return flow {
            configRepository.recoverToken(nroAparelho)
                .collect{ result ->
                    result.onSuccess { equipList ->
                        if(equipList.isNotEmpty()){
//                            emit(ResultUpdateDatabase(++contRecoverEquip, TEXT_SAVE_DATA_TB + TB_EQUIP, qtde))
//                            configRepository.addAllEquip(equipList)
                            emit(ResultUpdateDatabase(qtde, TEXT_FINISH_UPDATE, qtde))
                        } else {
                            emit(ResultUpdateDatabase(qtde, WEB_RETURN_CLEAR_EQUIP, qtde))
                        }
                    }
                }
        }
    }
}