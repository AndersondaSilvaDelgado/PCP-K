package br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable

import br.com.usinasantafe.pcpk.features.infra.models.webservice.MovEquipProprioWebServiceModelInput
import br.com.usinasantafe.pcpk.features.infra.models.webservice.MovEquipProprioWebServiceModelOutput

interface MovEquipProprioDatasourceWebService {

    suspend fun sendMovEquipProprio(
        movEquipProprioList: List<MovEquipProprioWebServiceModelOutput>
        , nroAparelho: Long)
    : Result<List<MovEquipProprioWebServiceModelInput>>

}