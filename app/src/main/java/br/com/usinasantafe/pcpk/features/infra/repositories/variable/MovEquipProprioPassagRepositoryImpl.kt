package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioPassagDatasourceSharedPreferences
import javax.inject.Inject

class MovEquipProprioPassagRepositoryImpl @Inject constructor (
    private val movEquipProprioPassagDatasourceSharedPreferences: MovEquipProprioPassagDatasourceSharedPreferences
) : MovEquipProprioPassagRepository {

    override suspend fun countPassag(): Int {
        return movEquipProprioPassagDatasourceSharedPreferences.countPassag()
    }

}