package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioPassag
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioPassagDatasourceSharedPreferences
import javax.inject.Inject

class MovEquipProprioPassagRepositoryImpl @Inject constructor (
    private val movEquipProprioPassagDatasourceSharedPreferences: MovEquipProprioPassagDatasourceSharedPreferences,
) : MovEquipProprioPassagRepository {

    override suspend fun addPassag(movEquipProprioPassag: MovEquipProprioPassag): Boolean {
        return try {
            movEquipProprioPassagDatasourceSharedPreferences.addPassag(movEquipProprioPassag)
        } catch (exception: Exception) {
            false
        }
    }

    override suspend fun countPassag(): Int {
        return movEquipProprioPassagDatasourceSharedPreferences.countPassag()
    }

    override suspend fun deletePassag(pos: Int): Boolean {
        return try {
            movEquipProprioPassagDatasourceSharedPreferences.deletePassag(pos)
        } catch (exception: Exception) {
            false
        }
        return true
    }

    override suspend fun listPassag(): List<MovEquipProprioPassag> {
        return movEquipProprioPassagDatasourceSharedPreferences.listPassag()
    }

}