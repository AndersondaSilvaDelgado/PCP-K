package br.com.usinasantafe.pcp.module.datasource.webservice

import br.com.usinasantafe.pcp.external.webservices.datasource.stable.*
import br.com.usinasantafe.pcp.infra.datasource.webservice.stable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StableWebServiceDatasourceModule {

    @Binds
    @Singleton
    fun bindColabDatasource(dataSource: ColabDatasourceWebServiceImpl): ColabDatasourceWebService

    @Binds
    @Singleton
    fun bindEquipDatasource(dataSource: EquipDatasourceWebServiceImpl): EquipDatasourceWebService

    @Binds
    @Singleton
    fun bindLocalDatasource(dataSource: LocalDatasourceWebServiceImpl): LocalDatasourceWebService

    @Binds
    @Singleton
    fun bindTerceiroDatasource(dataSource: TerceiroDatasourceWebServiceImpl): TerceiroDatasourceWebService

    @Binds
    @Singleton
    fun bindVisitanteDatasource(dataSource: VisitanteDatasourceWebServiceImpl): VisitanteDatasourceWebService

}