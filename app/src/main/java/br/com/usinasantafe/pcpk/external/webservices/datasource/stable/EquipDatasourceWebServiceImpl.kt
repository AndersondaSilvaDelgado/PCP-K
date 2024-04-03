package br.com.usinasantafe.pcpk.external.webservices.datasource.stable

import br.com.usinasantafe.pcpk.utils.token
import br.com.usinasantafe.pcpk.external.webservices.api.stable.EquipApi
import br.com.usinasantafe.pcpk.infra.datasource.webservice.stable.EquipDatasourceWebService
import br.com.usinasantafe.pcpk.infra.models.room.stable.EquipRoomModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EquipDatasourceWebServiceImpl @Inject constructor (
    private val equipApi: EquipApi,
): EquipDatasourceWebService {

    override suspend fun getAllEquip(nroAparelho: Long): Flow<Result<List<EquipRoomModel>>> = flow {
        val response = equipApi.all(token(nroAparelho))
        if (!response.isSuccessful)
            emit(Result.failure(Throwable("Erro recebimento de dados")))
        emit(Result.success(response.body()!!))
    }

}