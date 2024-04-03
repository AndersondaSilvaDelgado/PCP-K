package br.com.usinasantafe.pcpk.external.webservices.datasource.variable

import android.util.Log
import br.com.usinasantafe.pcpk.utils.token
import br.com.usinasantafe.pcpk.external.webservices.api.variable.MovEquipProprioApi

import br.com.usinasantafe.pcpk.infra.datasource.webservice.variable.MovEquipProprioDatasourceWebService
import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipProprioWebServiceModelInput
import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipProprioWebServiceModelOutput
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
            Log.i("PCP","Erro envio de dados" )
            Result.failure(Throwable("Erro envio de dados"))
        }
    }

}