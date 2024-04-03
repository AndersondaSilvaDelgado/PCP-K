package br.com.usinasantafe.pcpk.module.datasource.sharedpreferences

import br.com.usinasantafe.pcpk.external.sharedpreferences.*
import br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SharedPreferencesDatasourceModule {

    @Binds
    @Singleton
    fun bindConfigDatasourceSharedPreferences(dataSource: ConfigDatasourceSharedPreferencesImpl): ConfigDatasourceSharedPreferences

    @Binds
    @Singleton
    fun bindMovEquipProprioDatasourceSharedPreferences(dataSource: MovEquipProprioDatasourceSharedPreferencesImpl): MovEquipProprioDatasourceSharedPreferences

    @Binds
    @Singleton
    fun bindMovEquipProprioPassagDatasourceSharedPreferences(dataSource: MovEquipProprioPassagDatasourceSharedPreferencesImpl): MovEquipProprioPassagDatasourceSharedPreferences

    @Binds
    @Singleton
    fun bindMovEquipProprioSegDatasourceSharedPreferences(dataSource: MovEquipProprioSegDatasourceSharedPreferencesImpl): MovEquipProprioSegDatasourceSharedPreferences

    @Binds
    @Singleton
    fun bindMovEquipResidenciaDatasourceSharedPreferences(dataSource: MovEquipResidenciaDatasourceSharedPreferencesImpl): MovEquipResidenciaDatasourceSharedPreferences

    @Binds
    @Singleton
    fun bindMovEquipVisitTercDatasourceSharedPreferences(dataSource: MovEquipVisitTercDatasourceSharedPreferencesImpl): MovEquipVisitTercDatasourceSharedPreferences

    @Binds
    @Singleton
    fun bindMovEquipVisitTercPassagDatasourceSharedPreferences(dataSource: MovEquipVisitTercPassagDatasourceSharedPreferencesImpl): MovEquipVisitTercPassagDatasourceSharedPreferences

}