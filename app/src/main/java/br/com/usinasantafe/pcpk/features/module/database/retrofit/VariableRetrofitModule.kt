package br.com.usinasantafe.pcpk.features.module.database.retrofit

import br.com.usinasantafe.pcpk.features.external.webservices.AppRetrofit
import br.com.usinasantafe.pcpk.features.external.webservices.api.variable.ConfigApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VariableRetrofitModule {

    @Singleton
    @Provides
    fun configApiRetrofit(): ConfigApi {
        return AppRetrofit.getInstance().create(ConfigApi::class.java)
    }

}