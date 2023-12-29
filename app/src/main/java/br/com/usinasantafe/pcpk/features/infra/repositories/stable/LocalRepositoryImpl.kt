package br.com.usinasantafe.pcpk.features.infra.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Local
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.LocalRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.stable.LocalDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable.LocalDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toEquip
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toLocal
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toLocalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDatasourceRoom: LocalDatasourceRoom,
    private val localDatasourceWebService: LocalDatasourceWebService,
): LocalRepository {

    override suspend fun addAllLocal(list: List<Local>) {
        localDatasourceRoom.addAllLocal(*list.map { it.toLocalModel() }.toTypedArray())
    }

    override suspend fun deleteAllLocal() {
        localDatasourceRoom.deleteAllLocal()
    }

    override suspend fun listAllLocal(): List<Local> {
        return localDatasourceRoom.listAllLocal().map { it.toLocal() }
    }

    override suspend fun recoverAllLocal(nroAparelho: Long): Flow<Result<List<Local>>> = flow {
        localDatasourceWebService.getAllLocal(nroAparelho)
            .catch { exception -> emit(Result.failure(exception)) }
            .collect { result ->
                result.fold(
                    onSuccess = { localModelList ->
                        emit(Result.success(localModelList.map { it.toLocal() }))
                    },
                    onFailure = { exception -> emit(Result.failure(exception)) }
                )
            }
    }

    override suspend fun getLocalId(id: Long): Local {
        return localDatasourceRoom.getLocalId(id).toLocal()
    }

}