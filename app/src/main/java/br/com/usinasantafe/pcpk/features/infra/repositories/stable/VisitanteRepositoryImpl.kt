package br.com.usinasantafe.pcpk.features.infra.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Visitante
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.stable.VisitanteDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable.VisitanteDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toTerceiro
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toVisitante
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toVisitanteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VisitanteRepositoryImpl @Inject constructor(
    private val visitanteDatasourceRoom: VisitanteDatasourceRoom,
    private val visitanteDatasourceWebService: VisitanteDatasourceWebService,
): VisitanteRepository {

    override suspend fun addAllVisitante(list: List<Visitante>) {
        visitanteDatasourceRoom.addAllVisitante(*list.map { it.toVisitanteModel() }.toTypedArray())
    }

    override suspend fun deleteAllVisitante() {
        visitanteDatasourceRoom.deleteAllVisitante()
    }

    override suspend fun recoverAllVisitante(nroAparelho: Long): Flow<Result<List<Visitante>>> = flow {
        visitanteDatasourceWebService.getAllVisitante(nroAparelho)
            .catch { exception -> emit(Result.failure(exception)) }
            .collect { result ->
                result.fold (
                    onSuccess = { visitanteModelList ->
                        emit(Result.success(visitanteModelList.map { it.toVisitante() }))
                    },
                    onFailure = { exception -> emit(Result.failure(exception)) }
                )
            }
    }

}