package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.UpdateAllDataBaseImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.update.*
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.UpdateAllDataBase
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UpdateDatabaseModule {

    @Singleton
    @Binds
    fun bindUpdateDataBase(usecase: UpdateAllDataBaseImpl): UpdateAllDataBase

    @Singleton
    @Binds
    fun bindUpdateColab(usecase: UpdateColabImpl): UpdateColab

    @Singleton
    @Binds
    fun bindUpdateEquip(usecase: UpdateEquipImpl): UpdateEquip

    @Singleton
    @Binds
    fun bindUpdateLocal(usecase: UpdateLocalImpl): UpdateLocal

    @Singleton
    @Binds
    fun bindUpdateTerceiro(usecase: UpdateTerceiroImpl): UpdateTerceiro

    @Singleton
    @Binds
    fun bindUpdateVisitante(usecase: UpdateVisitanteImpl): UpdateVisitante

    @Singleton
    @Binds
    fun bindUpdateVisitTerc(usecase: UpdateVisitTercImpl): UpdateVisitTerc

}