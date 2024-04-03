package br.com.usinasantafe.pcpk.external.webservices.api.variable

import br.com.usinasantafe.pcpk.utils.WEB_SAVE_MOV_EQUIP_VISIT_TERC
import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipVisitTercWebServiceModelInput
import br.com.usinasantafe.pcpk.infra.models.webservice.MovEquipVisitTercWebServiceModelOutput
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MovEquipVisitTercApi {

    @POST(WEB_SAVE_MOV_EQUIP_VISIT_TERC)
    suspend fun send(@Header("Authorization") auth: String, @Body data: List<MovEquipVisitTercWebServiceModelOutput>): Response<List<MovEquipVisitTercWebServiceModelInput>>

}