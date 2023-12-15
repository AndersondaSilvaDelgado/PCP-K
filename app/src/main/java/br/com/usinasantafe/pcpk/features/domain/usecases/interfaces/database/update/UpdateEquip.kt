package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update

import br.com.usinasantafe.pcpk.features.presenter.model.ResultUpdateDatabase
import kotlinx.coroutines.flow.Flow

interface UpdateEquip {

    suspend operator fun invoke(contador: Int = 0, qtde: Int = 3): Flow<ResultUpdateDatabase>

}