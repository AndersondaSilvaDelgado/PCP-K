package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioPassag

interface MovEquipProprioPassagRepository {

    suspend fun countPassag(): Int

    suspend fun listPassag(): List<MovEquipProprioPassag>
}