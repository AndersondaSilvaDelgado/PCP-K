package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioSeg
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioSegDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioSegDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioSegRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.entityToMovEquipProprioSegRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.modelRoomToMovEquipSegPassag
import javax.inject.Inject

class MovEquipProprioSegRepositoryImpl @Inject constructor (
    private val movEquipProprioSegDatasourceSharedPreferences: MovEquipProprioSegDatasourceSharedPreferences,
    private val movEquipProprioSegDatasourceRoom: MovEquipProprioSegDatasourceRoom,
) : MovEquipProprioSegRepository {

    override suspend fun addEquipSeg(idEquip: Long): Boolean {
        return try {
            movEquipProprioSegDatasourceSharedPreferences.addEquipSeg(idEquip)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun addEquipSeg(idEquip: Long, idMov: Long): Boolean {
        return movEquipProprioSegDatasourceRoom.addMovEquipProprioSeg(MovEquipProprioSegRoomModel(idMovEquipProprio = idMov, idEquipMovEquipProprioSeg = idEquip))
    }

    override suspend fun clearEquipSeg(): Boolean {
        return try {
            movEquipProprioSegDatasourceSharedPreferences.clearEquipSeg()
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun deleteEquipSeg(pos: Int): Boolean {
        return try {
            movEquipProprioSegDatasourceSharedPreferences.deleteEquipSeg(pos)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun deleteEquipSeg(pos: Int, idMov: Long): Boolean {
        return try {
            val equipSeg = movEquipProprioSegDatasourceRoom.listMovEquipProprioSegIdMov(idMov)[pos]
            movEquipProprioSegDatasourceRoom.deleteMovEquipProprioSeg(equipSeg)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun deleteEquipSeg(idMov: Long): Boolean {
        try {
            val equipSegList = movEquipProprioSegDatasourceRoom.listMovEquipProprioSegIdMov(idMov)
            for(equipSeg in equipSegList){
                movEquipProprioSegDatasourceRoom.deleteMovEquipProprioSeg(equipSeg)
            }
        } catch (exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun listEquipSeg(): List<MovEquipProprioSeg> {
        return movEquipProprioSegDatasourceSharedPreferences.listEquipSeg().map { MovEquipProprioSeg(idEquipMovEquipProprioSeg = it) }
    }

    override suspend fun listEquipSeg(idMov: Long): List<MovEquipProprioSeg> {
        return movEquipProprioSegDatasourceRoom.listMovEquipProprioSegIdMov(idMov).map { it.modelRoomToMovEquipSegPassag() }
    }

    override suspend fun saveEquipSeg(idMov: Long): Boolean {
        if(!movEquipProprioSegDatasourceRoom.addAllMovEquipProprioSeg(*listEquipSeg().map {
            it.entityToMovEquipProprioSegRoomModel(idMov)
        }.toTypedArray())) return false
        return movEquipProprioSegDatasourceSharedPreferences.clearEquipSeg()
    }

}