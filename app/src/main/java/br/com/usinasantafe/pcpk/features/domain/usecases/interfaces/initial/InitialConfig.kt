package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial

import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow

interface InitialConfig {

    suspend operator fun invoke(nroAparelho: String, password: String, contador: Int = 0, qtde: Int = 3): Flow<Result<ResultUpdateDatabase>>

}