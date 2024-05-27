package br.com.usinasantafe.pcp.external.webservices.datasource.stable

import br.com.usinasantafe.pcp.infra.datasource.webservice.stable.ColabDatasourceWebService
import br.com.usinasantafe.pcp.external.webservices.api.stable.ColabApi
import br.com.usinasantafe.pcp.infra.models.room.stable.ColabRoomModel
import br.com.usinasantafe.pcp.utils.token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ColabDatasourceWebServiceImpl @Inject constructor (
    private val colabApi: ColabApi,
): ColabDatasourceWebService {

    override suspend fun getAllColab(token: String): Flow<Result<List<ColabRoomModel>>> = flow {
        val response = colabApi.all(token)
        if (!response.isSuccessful)
            emit(Result.failure(Throwable("Erro recebimento de dados")))
        emit(Result.success(response.body()!!))
    }

}