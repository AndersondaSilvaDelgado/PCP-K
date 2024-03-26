package br.com.usinasantafe.pcpk.features.external.webservices.api.stable

import br.com.usinasantafe.pcpk.common.utils.WEB_ALL_COLAB
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.ColabRoomModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ColabApi {

    @GET(WEB_ALL_COLAB)
    suspend fun all(@Header("Authorization") auth: String): Response<List<ColabRoomModel>>

    @GET("colab-test.php")
    suspend fun allTest(): Response<List<ColabRoomModel>>

}