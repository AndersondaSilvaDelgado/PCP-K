package br.com.usinasantafe.pcpk.features.external.webservices.datasource.stable

import br.com.usinasantafe.pcpk.common.utils.token
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable.ColabDatasourceWebService
import br.com.usinasantafe.pcpk.features.external.webservices.api.stable.ColabApi
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.ColabRoomModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ColabDatasourceWebServiceImpl @Inject constructor (
    private val colabApi: ColabApi,
): ColabDatasourceWebService {

    override suspend fun getAllColab(nroAparelho: Long): Flow<Result<List<ColabRoomModel>>> = flow {
        val response = colabApi.all(token(nroAparelho))
        if (!response.isSuccessful)
            emit(Result.failure(Throwable("Erro recebimento de dados")))
        emit(Result.success(response.body()!!))
    }

}