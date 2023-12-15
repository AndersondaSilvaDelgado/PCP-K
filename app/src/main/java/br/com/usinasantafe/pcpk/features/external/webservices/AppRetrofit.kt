package br.com.usinasantafe.pcpk.features.external.webservices

import android.content.res.Resources
import br.com.usinasantafe.pcpk.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object AppRetrofit {

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

    fun getInstance(): Retrofit = Retrofit.Builder()
            .baseUrl(Resources.getSystem().getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(httpClient())
            .build()

}