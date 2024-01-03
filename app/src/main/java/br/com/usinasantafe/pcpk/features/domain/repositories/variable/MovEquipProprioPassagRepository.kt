package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioPassag

interface MovEquipProprioPassagRepository {

    suspend fun addPassag(movEquipProprioPassag: MovEquipProprioPassag): Boolean

    suspend fun countPassag(): Int

    suspend fun deletePassag(pos: Int): Boolean

    suspend fun listPassag(): List<MovEquipProprioPassag>
}