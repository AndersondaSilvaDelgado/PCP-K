package br.com.usinasantafe.pcpk.features.external.webservices.api.stable

import br.com.usinasantafe.pcpk.common.utils.WEB_ALL_TERCEIRO
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.TerceiroRoomModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TerceiroApi {

    @GET(WEB_ALL_TERCEIRO)
    suspend fun all(@Header("Authorization") auth: String): Response<List<TerceiroRoomModel>>

}