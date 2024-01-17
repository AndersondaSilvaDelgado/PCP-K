package br.com.usinasantafe.pcpk.features.external.webservices.datasource.variable

import br.com.usinasantafe.pcpk.common.utils.token
import br.com.usinasantafe.pcpk.features.external.webservices.api.variable.MovEquipProprioApi

import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.MovEquipProprioDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.webservice.MovEquipProprioWebServiceModelInput
import br.com.usinasantafe.pcpk.features.infra.models.webservice.MovEquipProprioWebServiceModelOutput
import javax.inject.Inject

class MovEquipProprioDatasourceWebServiceImpl @Inject constructor(
    private val movEquipProprioApi: MovEquipProprioApi
): MovEquipProprioDatasourceWebService {

    override suspend fun sendMovEquipProprio(
        movEquipProprioList: List<MovEquipProprioWebServiceModelOutput>,
        nroAparelho: Long
    ): Result<List<MovEquipProprioWebServiceModelInput>> {
        val response = movEquipProprioApi.send(token(nroAparelho), movEquipProprioList)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Throwable("Erro envio de dados"))
        }
    }

}