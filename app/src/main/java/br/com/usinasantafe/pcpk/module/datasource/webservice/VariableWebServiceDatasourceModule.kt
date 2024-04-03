package br.com.usinasantafe.pcpk.module.datasource.webservice

import br.com.usinasantafe.pcpk.external.webservices.datasource.variable.*
import br.com.usinasantafe.pcpk.infra.datasource.webservice.variable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VariableWebServiceDatasourceModule {

    @Binds
    @Singleton
    fun bindConfigDatasourceWebService(dataSource: ConfigDatasourceWebServiceImpl): ConfigDatasourceWebService

    @Binds
    @Singleton
    fun bindMovEquipProprioDatasourceWebService(dataSource: MovEquipProprioDatasourceWebServiceImpl): MovEquipProprioDatasourceWebService

    @Binds
    @Singleton
    fun bindMovEquipVisitTercDatasourceWebService(dataSource: MovEquipVisitTercDatasourceWebServiceImpl): MovEquipVisitTercDatasourceWebService

    @Binds
    @Singleton
    fun bindMovEquipResidenciaDatasourceWebService(dataSource: MovEquipResidenciaDatasourceWebServiceImpl): MovEquipResidenciaDatasourceWebService

}