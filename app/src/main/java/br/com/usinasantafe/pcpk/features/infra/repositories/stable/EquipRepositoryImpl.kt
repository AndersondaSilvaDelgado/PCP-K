package br.com.usinasantafe.pcpk.features.infra.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Colab
import br.com.usinasantafe.pcpk.features.domain.entities.stable.Equip
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.stable.EquipDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable.EquipDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toColab
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toEquip
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toEquipModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EquipRepositoryImpl @Inject constructor(
    private val equipDatasourceRoom: EquipDatasourceRoom,
    private val equipDatasourceWebService: EquipDatasourceWebService,
): EquipRepository {

    override suspend fun addAllEquip(list: List<Equip>) {
        equipDatasourceRoom.addAllEquip(*list.map { it.toEquipModel() }.toTypedArray())
    }

    override suspend fun deleteAllEquip() {
        equipDatasourceRoom.deleteAllEquip()
    }

    override suspend fun recoverAllEquip(nroAparelho: Long): Flow<Result<List<Equip>>> = flow {
        equipDatasourceWebService.getAllEquip(nroAparelho)
            .catch { exception -> emit(Result.failure(exception)) }
            .collect { result ->
                result.fold(
                    onSuccess = { equipModelList ->
                        emit(Result.success(equipModelList.map { it.toEquip() }))
                    },
                    onFailure = { exception -> emit(Result.failure(exception)) }
                )
            }
    }

    override suspend fun checkEquipNro(nro: Long): Boolean {
        return equipDatasourceRoom.checkEquipNro(nro)
    }

    override suspend fun getEquipNro(nro: Long): Equip {
        return equipDatasourceRoom.getEquipNro(nro).toEquip()
    }

    override suspend fun getEquipId(id: Long): Equip {
        return equipDatasourceRoom.getEquipId(id).toEquip()
    }

}