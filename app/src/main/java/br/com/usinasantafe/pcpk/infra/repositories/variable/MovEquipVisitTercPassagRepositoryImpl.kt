package br.com.usinasantafe.pcpk.infra.repositories.variable

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTercPassag
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.infra.datasource.room.variable.MovEquipVisitTercPassagDatasourceRoom
import br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences.MovEquipVisitTercPassagDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.infra.models.room.variable.MovEquipVisitTercPassagRoomModel
import br.com.usinasantafe.pcpk.infra.models.room.variable.entityToMovEquipVisitTercPassagRoomModel
import br.com.usinasantafe.pcpk.infra.models.room.variable.modelRoomToMovEquipVisitTercPassag
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
            val passag = movEquipVisitTercPassagDatasourceRoom.listMovEquipVisitTercPassagIdMov(idMov)[pos]
            movEquipVisitTercPassagDatasourceRoom.deleteMovEquipVisitTercPassag(passag)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun deletePassag(idMov: Long): Boolean {
        try {
            val passagList = movEquipVisitTercPassagDatasourceRoom.listMovEquipVisitTercPassagIdMov(idMov)
            for (passag in passagList) {
                movEquipVisitTercPassagDatasourceRoom.deleteMovEquipVisitTercPassag(passag)
            }
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun listPassag(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTercPassag> {
        return movEquipVisitTercPassagDatasourceSharedPreferences.listPassag().map {
            br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTercPassag(
                idVisitTercMovEquipVisitTercPassag = it
            )
        }
    }

    override suspend fun listPassag(idMov: Long): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTercPassag> {
        return movEquipVisitTercPassagDatasourceRoom.listMovEquipVisitTercPassagIdMov(idMov).map { it.modelRoomToMovEquipVisitTercPassag() }
    }

    override suspend fun savePassag(idMov: Long): Boolean {
        if(!movEquipVisitTercPassagDatasourceRoom.addAllMovEquipVisitTercPassag(*listPassag().map {
                it.entityToMovEquipVisitTercPassagRoomModel(idMov)
            }.toTypedArray())) return false
        return movEquipVisitTercPassagDatasourceSharedPreferences.clearPassag()
    }

    override suspend fun savePassag(
        idMov: Long,
        passagList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTercPassag>
    ): Boolean {
        return movEquipVisitTercPassagDatasourceRoom.addAllMovEquipVisitTercPassag(*passagList.map {
                it.entityToMovEquipVisitTercPassagRoomModel(idMov)
            }.toTypedArray())
    }

}