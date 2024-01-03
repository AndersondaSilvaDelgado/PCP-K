package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioSeg
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioSegDatasourceSharedPreferences
import javax.inject.Inject

class MovEquipProprioSegRepositoryImpl @Inject constructor (
    private val movEquipProprioSegDatasourceSharedPreferences: MovEquipProprioSegDatasourceSharedPreferences,
) : MovEquipProprioSegRepository {

    override suspend fun addEquipSeg(movEquipProprioSeg: MovEquipProprioSeg): Boolean {
        return try {
            movEquipProprioSegDatasourceSharedPreferences.addEquipSeg(movEquipProprioSeg)
        } catch (exception: Exception) {
            false
        }
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

    override suspend fun listEquipSeg(): List<MovEquipProprioSeg> {
        return movEquipProprioSegDatasourceSharedPreferences.listEquipSeg()
    }

}