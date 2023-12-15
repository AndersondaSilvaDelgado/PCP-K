package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.recover

import br.com.usinasantafe.pcpk.features.presenter.model.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow

interface RecoverToken {

    suspend operator fun invoke(nroAparelho: String, contador: Int = 0, qtde: Int = 13): Flow<ResultUpdateDatabase>

}