package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTercPassag
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipVisitTercPassagDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipVisitTercPassagDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipVisitTercPassagRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.entityToMovEquipVisitTercPassagRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.modelRoomToMovEquipVisitTercPassag
import javax.inject.Inject

class MovEquipVisitTercPassagRepositoryImpl @Inject constructor (
    private val movEquipVisitTercPassagDatasourceSharedPreferences: MovEquipVisitTercPassagDatasourceSharedPreferences,
    private val movEquipVisitTercPassagDatasourceRoom: MovEquipVisitTercPassagDatasourceRoom,
) : MovEquipVisitTercPassagRepository {

    override suspend fun addPassag(idVisitTerc: Long): Boolean {
        return try {
            movEquipVisitTercPassagDatasourceSharedPreferences.addPassag(idVisitTerc)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun addPassag(idVisitTerc: Long, idMov: Long): Boolean {
        return try {
            movEquipVisitTercPassagDatasourceRoom.addMovEquipVisitTercPassag(
                MovEquipVisitTercPassagRoomModel(idMovEquipVisitTerc = idMov, idVisitTercMovEquipVisitTercPassag = idVisitTerc)
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun deletePassag(pos: Int): Boolean {
        return try {
            movEquipVisitTercPassagDatasourceSharedPreferences.deletePassag(pos)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun deletePassag(pos: Int, idMov: Long): Boolean {
        return try {
            val movEquip = movEquipVisitTercPassagDatasourceRoom.listMovEquipVisitTercPassagIdMov(idMov)[pos]
            movEquipVisitTercPassagDatasourceRoom.deleteMovEquipVisitTercPassag(movEquip)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun listPassag(): List<MovEquipVisitTercPassag> {
        return movEquipVisitTercPassagDatasourceSharedPreferences.listPassag().map { MovEquipVisitTercPassag(idVisitTercMovEquipVisitTercPassag = it) }
    }

    override suspend fun listPassag(idMov: Long): List<MovEquipVisitTercPassag> {
        return movEquipVisitTercPassagDatasourceRoom.listMovEquipVisitTercPassagIdMov(idMov).map { it.modelRoomToMovEquipVisitTercPassag() }
    }

    override suspend fun savePassag(idMov: Int): Boolean {
        if(!movEquipVisitTercPassagDatasourceRoom.addAllMovEquipVisitTercPassag(*listPassag().map {
                it.entityToMovEquipVisitTercPassagRoomModel(idMov)
            }.toTypedArray())) return false
        return movEquipVisitTercPassagDatasourceSharedPreferences.clearPassag()
    }

    override suspend fun savePassag(
        idMov: Int,
        passagList: List<MovEquipVisitTercPassag>
    ): Boolean {
        return movEquipVisitTercPassagDatasourceRoom.addAllMovEquipVisitTercPassag(*passagList.map {
                it.entityToMovEquipVisitTercPassagRoomModel(idMov)
            }.toTypedArray())
    }

}