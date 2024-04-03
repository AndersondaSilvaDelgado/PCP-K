package br.com.usinasantafe.pcpk.infra.datasource.webservice.variable

import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipResidenciaWebServiceModelInput
import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipResidenciaWebServiceModelOutput


interface MovEquipResidenciaDatasourceWebService {

    suspend fun sendMovEquipResidencia(
        movEquipResidenciaList: List<MovEquipResidenciaWebServiceModelOutput>
        , nroAparelho: Long)
            : Result<List<MovEquipResidenciaWebServiceModelInput>>

}