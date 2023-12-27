package br.com.usinasantafe.pcpk.features.external.webservices.datasource.stable

import br.com.usinasantafe.pcpk.common.utils.token
import br.com.usinasantafe.pcpk.features.external.webservices.api.stable.VisitanteApi
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable.VisitanteDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.VisitanteRoomModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VisitanteDatasourceWebServiceImpl @Inject constructor (
    private val visitanteApi: VisitanteApi,
): VisitanteDatasourceWebService {

    override suspend fun getAllVisitante(nroAparelho: Long): Flow<Result<List<VisitanteRoomModel>>> = flow {
        val response = visitanteApi.all(token(nroAparelho))
        if (!response.isSuccessful)
            emit(Result.failure(Throwable("Erro recebimento de dados")))
        emit(Result.success(response.body()!!))
    }

}