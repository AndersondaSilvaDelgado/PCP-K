package br.com.usinasantafe.pcpk.external.webservices.api.stable

import br.com.usinasantafe.pcpk.utils.WEB_ALL_VISITANTE
import br.com.usinasantafe.pcpk.infra.models.room.stable.VisitanteRoomModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface VisitanteApi {

    @GET(WEB_ALL_VISITANTE)
    suspend fun all(@Header("Authorization") auth: String): Response<List<VisitanteRoomModel>>

}