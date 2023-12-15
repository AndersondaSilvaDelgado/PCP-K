package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial

import br.com.usinasantafe.pcpk.features.presenter.model.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow

interface SaveConfig {

    suspend operator fun invoke(nroAparelho: String, senha: String): Flow<ResultUpdateDatabase>

}