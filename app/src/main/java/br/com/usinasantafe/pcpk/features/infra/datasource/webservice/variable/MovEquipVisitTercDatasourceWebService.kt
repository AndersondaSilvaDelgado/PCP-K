package br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable

import br.com.usinasantafe.pcpk.features.infra.models.webservice.MovEquipVisitTercWebServiceModelInput
import br.com.usinasantafe.pcpk.features.infra.models.webservice.MovEquipVisitTercWebServiceModelOutput

interface MovEquipVisitTercDatasourceWebService {

    suspend fun sendMovEquipVisitTerc(
        movEquipVisitTercList: List<MovEquipVisitTercWebServiceModelOutput>
        , nroAparelho: Long)
            : Result<List<MovEquipVisitTercWebServiceModelInput>>

}