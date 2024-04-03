package br.com.usinasantafe.pcpk.external.webservices.datasource.variable

import br.com.usinasantafe.pcpk.utils.token
import br.com.usinasantafe.pcpk.external.webservices.api.variable.MovEquipResidenciaApi
import br.com.usinasantafe.pcpk.infra.datasource.webservice.variable.MovEquipResidenciaDatasourceWebService
import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipResidenciaWebServiceModelInput
import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipResidenciaWebServiceModelOutput
import javax.inject.Inject

class MovEquipResidenciaDatasourceWebServiceImpl @Inject constructor(
    private val movEquipResidenciaApi: MovEquipResidenciaApi,
): MovEquipResidenciaDatasourceWebService {

    override suspend fun sendMovEquipResidencia(
        movEquipResidenciaList: List<MovEquipResidenciaWebServiceModelOutput>,
        nroAparelho: Long
    ): Result<List<MovEquipResidenciaWebServiceModelInput>> {
        val response = movEquipResidenciaApi.send(token(nroAparelho), movEquipResidenciaList)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Throwable("Erro envio de dados"))
        }
    }

}