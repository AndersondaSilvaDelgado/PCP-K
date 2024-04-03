package br.com.usinasantafe.pcpk.external.webservices.datasource.stable

import br.com.usinasantafe.pcpk.external.webservices.api.stable.ColabApi
import br.com.usinasantafe.pcpk.infra.models.room.stable.ColabRoomModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.single
import org.junit.Assert.*

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun httpClient(): OkHttpClient {

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(logging)
        .build()
}

private fun gson(): Gson = GsonBuilder().create()
@RunWith(MockitoJUnitRunner::class)
class ColabDatasourceWebServiceImplTest{
    @Test
    fun test() = runBlocking {
        val server = MockWebServer()
        server.start()
        server.enqueue(MockResponse().setBody(result))
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(httpClient())
            .build()
        val service: ColabApi = retrofit.create(ColabApi::class.java)
        val datasource = ColabDatasourceWebServiceImpl(service)
        val item = datasource.getAllColab(11L).single()
        assertTrue(item.isSuccess)
        var list = listOf(ColabRoomModel(1L, "ANITA FERRAZ MALZONI"))
        assertEquals(item.getOrNull(), list)
    }
}

val result = """
    [{"matricColab":1,"nomeColab":"ANITA FERRAZ MALZONI"}]
""".trimIndent()