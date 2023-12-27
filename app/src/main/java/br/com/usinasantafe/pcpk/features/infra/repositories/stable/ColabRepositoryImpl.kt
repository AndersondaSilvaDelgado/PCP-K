package br.com.usinasantafe.pcpk.features.infra.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Colab
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.stable.ColabDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable.ColabDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toColab
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toColabModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ColabRepositoryImpl @Inject constructor(
    private val colabDatasourceRoom: ColabDatasourceRoom,
    private val colabDatasourceWebService: ColabDatasourceWebService,
): ColabRepository {

    override suspend fun addAllColab(list: List<Colab>) {
        colabDatasourceRoom.addAllColab(*list.map { it.toColabModel() }.toTypedArray())
    }

    override suspend fun deleteAllColab() {
        colabDatasourceRoom.deleteAllColab()
    }

    override suspend fun recoverAllColab(nroAparelho: Long): Flow<Result<List<Colab>>> = flow {
        colabDatasourceWebService.getAllColab(nroAparelho)
            .catch { exception -> emit(Result.failure(exception)) }
            .collect { result ->
                result.fold(
                    onSuccess = { colabModelList ->
                        emit(Result.success(colabModelList.map { it.toColab() }))
                    },
                    onFailure = { exception -> emit(Result.failure(exception)) }
                )
            }
    }

    override suspend fun checkColabMatric(matric: Long): Boolean {
        return colabDatasourceRoom.checkColabMatric(matric)
    }

    override suspend fun getColabMatric(matric: Long): Colab {
        return colabDatasourceRoom.getColabMatric(matric).toColab()
    }

}