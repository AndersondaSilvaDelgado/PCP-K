package br.com.usinasantafe.pcpk.features.external.webservices.api.variable

import br.com.usinasantafe.pcpk.common.utils.WEB_SAVE_TOKEN
import br.com.usinasantafe.pcpk.features.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcpk.features.infra.models.webservice.ConfigWebServiceModelOutput
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ConfigApi {

    @POST(WEB_SAVE_TOKEN)
    suspend fun send(@Body config: ConfigWebServiceModelOutput): Response<ConfigWebServiceModelInput>

}