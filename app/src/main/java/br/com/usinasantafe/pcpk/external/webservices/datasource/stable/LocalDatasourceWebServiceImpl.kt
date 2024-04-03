package br.com.usinasantafe.pcpk.external.webservices.datasource.stable

import br.com.usinasantafe.pcpk.utils.token
import br.com.usinasantafe.pcpk.external.webservices.api.stable.LocalApi
import br.com.usinasantafe.pcpk.infra.datasource.webservice.stable.LocalDatasourceWebService
import br.com.usinasantafe.pcpk.infra.models.room.stable.LocalRoomModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDatasourceWebServiceImpl @Inject constructor (
    private val localApi: LocalApi,
): LocalDatasourceWebService {

    override suspend fun getAllLocal(nroAparelho: Long): Flow<Result<List<LocalRoomModel>>> = flow {
        val response = localApi.all(token(nroAparelho))
        if (!response.isSuccessful)
            emit(Result.failure(Throwable("Erro recebimento de dados")))
        emit(Result.success(response.body()!!))
    }

}