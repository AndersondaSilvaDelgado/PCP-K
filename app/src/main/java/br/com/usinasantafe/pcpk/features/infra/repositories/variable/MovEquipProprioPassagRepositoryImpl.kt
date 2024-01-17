package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioPassag
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioPassagDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioPassagDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioPassagRoomModel
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.modelRoomToMovEquipProprioPassag
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.entityToMovEquipProprioPassagRoomModel
import javax.inject.Inject

class MovEquipProprioPassagRepositoryImpl @Inject constructor (
    private val movEquipProprioPassagDatasourceSharedPreferences: MovEquipProprioPassagDatasourceSharedPreferences,
    private val movEquipProprioPassagDatasourceRoom: MovEquipProprioPassagDatasourceRoom,
) : MovEquipProprioPassagRepository {

    override suspend fun addPassag(nroMatric: Long): Boolean {
        return try {
            movEquipProprioPassagDatasourceSharedPreferences.addPassag(nroMatric)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun addPassag(nroMatric: Long, idMov: Long): Boolean {
        return try {
            movEquipProprioPassagDatasourceRoom.addMovEquipProprioPassag(
                MovEquipProprioPassagRoomModel(idMovEquipProprio = idMov, nroMatricMovEquipProprioPassag = nroMatric)
            )
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun deletePassag(pos: Int): Boolean {
        return try {
            movEquipProprioPassagDatasourceSharedPreferences.deletePassag(pos)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun listPassag(): List<MovEquipProprioPassag> {
        return movEquipProprioPassagDatasourceSharedPreferences.listPassag().map { MovEquipProprioPassag(nroMatricMovEquipProprioPassag = it) }
    }

    override suspend fun listPassagIdMov(idMov: Long): List<MovEquipProprioPassag> {
        return movEquipProprioPassagDatasourceRoom.listMovEquipProprioPassagIdMov(idMov).map { it.modelRoomToMovEquipProprioPassag() }
    }

    override suspend fun savePassag(idMov: Int): Boolean {
        if(!movEquipProprioPassagDatasourceRoom.addAllMovEquipProprioPassag(*listPassag().map {
                it.entityToMovEquipProprioPassagRoomModel(idMov)
        }.toTypedArray())) return false
        return movEquipProprioPassagDatasourceSharedPreferences.clearPassag()
    }

}