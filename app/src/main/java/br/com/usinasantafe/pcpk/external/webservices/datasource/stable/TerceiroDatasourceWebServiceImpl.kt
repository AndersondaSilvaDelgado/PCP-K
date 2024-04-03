package br.com.usinasantafe.pcpk.external.webservices.datasource.stable

import br.com.usinasantafe.pcpk.utils.token
import br.com.usinasantafe.pcpk.external.webservices.api.stable.TerceiroApi
import br.com.usinasantafe.pcpk.infra.datasource.webservice.stable.TerceiroDatasourceWebService
import br.com.usinasantafe.pcpk.infra.models.room.stable.TerceiroRoomModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TerceiroDatasourceWebServiceImpl @Inject constructor (
    private val terceiroApi: TerceiroApi,
): TerceiroDatasourceWebService {

    override suspend fun getAllTerceiro(nroAparelho: Long): Flow<Result<List<TerceiroRoomModel>>> = flow {
        val response = terceiroApi.all(token(nroAparelho))
        if (!response.isSuccessful)
            emit(Result.failure(Throwable("Erro recebimento de dados")))
        emit(Result.success(response.body()!!))
    }

}