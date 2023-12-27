package br.com.usinasantafe.pcpk.features.external.webservices.api.stable

import br.com.usinasantafe.pcpk.common.utils.WEB_ALL_EQUIP
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.EquipRoomModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface EquipApi {

    @GET(WEB_ALL_EQUIP)
    suspend fun all(@Header("Authorization") auth: String): Response<List<EquipRoomModel>>

}