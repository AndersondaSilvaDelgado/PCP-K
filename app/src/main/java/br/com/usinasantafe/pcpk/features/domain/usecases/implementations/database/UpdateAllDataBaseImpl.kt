package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database

import br.com.usinasantafe.pcpk.common.utils.TEXT_SUCESS_UPDATE
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.UpdateAllDataBase
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateColab
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateEquip
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateLocal
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateTerceiro
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateVisitante
import br.com.usinasantafe.pcpk.features.presenter.model.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateAllDataBaseImpl @Inject constructor(
    private val updateColab: UpdateColab,
    private val updateEquip: UpdateEquip,
    private val updateLocal: UpdateLocal,
    private val updateTerceiro: UpdateTerceiro,
    private val updateVisitante: UpdateVisitante,
) : UpdateAllDataBase {

    override suspend fun invoke(count: Int, size: Int): Flow<ResultUpdateDatabase> {
        return flow {
            val size = size
            var count = count
            updateColab(count, size).collect{
                emit(it)
                count = it.count;
            }
            updateEquip(count, size).collect{
                emit(it)
                count = it.count;
            }
            updateLocal(count, size).collect{
                emit(it)
                count = it.count;
            }
            updateTerceiro(count, size).collect{
                emit(it)
                count = it.count;
            }
            updateVisitante(count, size).collect{
                emit(it)
                count = it.count;
            }
            emit(ResultUpdateDatabase(size, TEXT_SUCESS_UPDATE, size))
        }
    }

}