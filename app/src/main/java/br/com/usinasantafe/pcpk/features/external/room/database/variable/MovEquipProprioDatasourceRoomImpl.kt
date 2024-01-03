package br.com.usinasantafe.pcpk.features.external.room.database.variable

import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.features.external.room.dao.variable.MovEquipProprioDao
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioRoomModel
import javax.inject.Inject

class MovEquipProprioDatasourceRoomImpl @Inject constructor (
    private val movEquipProprioDao: MovEquipProprioDao
): MovEquipProprioDatasourceRoom {

    override suspend fun listMovEquipProprioOpen(): List<MovEquipProprioRoomModel> {
        return movEquipProprioDao.listBoletimStatus(StatusData.OPEN)
    }

}