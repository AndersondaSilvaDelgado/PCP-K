package br.com.usinasantafe.pcpk.features.external.webservices.api.stable

import br.com.usinasantafe.pcpk.common.utils.WEB_ALL_LOCAL
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.LocalRoomModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface LocalApi {

    @GET(WEB_ALL_LOCAL)
    suspend fun all(@Header("Authorization") auth: String): Response<List<LocalRoomModel>>

}