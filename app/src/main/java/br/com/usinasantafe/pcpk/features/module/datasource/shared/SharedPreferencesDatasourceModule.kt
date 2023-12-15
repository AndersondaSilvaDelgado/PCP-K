package br.com.usinasantafe.pcpk.features.module.datasource.shared

import br.com.usinasantafe.pcpk.features.external.sharedpreferences.ConfigDatasourceSharedPreferencesImpl
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.ConfigDatasourceSharedPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SharedPreferencesDatasourceModule {

    @Singleton
    @Binds
    fun bindConfigDatasourceSharedPreferences(dataSource: ConfigDatasourceSharedPreferencesImpl): ConfigDatasourceSharedPreferences

}