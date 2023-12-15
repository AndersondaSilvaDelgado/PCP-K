package br.com.usinasantafe.pcpk.features.module.usecases

import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.UpdateAllDataBaseImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.update.UpdateColabImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.update.UpdateEquipImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.update.UpdateLocalImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.update.UpdateTerceiroImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.implementations.database.update.UpdateVisitanteImpl
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.UpdateAllDataBase
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateColab
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateEquip
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateLocal
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateTerceiro
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateVisitante
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

}