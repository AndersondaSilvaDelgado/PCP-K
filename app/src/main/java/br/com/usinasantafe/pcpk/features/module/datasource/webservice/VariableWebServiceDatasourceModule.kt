package br.com.usinasantafe.pcpk.features.module.datasource.webservice

import br.com.usinasantafe.pcpk.features.external.webservices.datasource.variable.ConfigDatasourceWebServiceImpl
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.ConfigDatasourceWebService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VariableWebServiceDatasourceModule {

    @Singleton
    @Binds
    fun bindConfigDatasourceWebService(dataSource: ConfigDatasourceWebServiceImpl): ConfigDatasourceWebService

}