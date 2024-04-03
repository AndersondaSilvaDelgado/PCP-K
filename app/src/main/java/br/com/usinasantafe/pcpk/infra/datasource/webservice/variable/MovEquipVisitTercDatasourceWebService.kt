package br.com.usinasantafe.pcpk.infra.datasource.webservice.variable

import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipVisitTercWebServiceModelInput
import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipVisitTercWebServiceModelOutput

interface MovEquipVisitTercDatasourceWebService {

    suspend fun sendMovEquipVisitTerc(
        movEquipVisitTercList: List<MovEquipVisitTercWebServiceModelOutput>
        , nroAparelho: Long)
            : Result<List<MovEquipVisitTercWebServiceModelInput>>

}