package br.com.usinasantafe.pcpk.features.infra.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Terceiro
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.stable.TerceiroDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable.TerceiroDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toTerceiro
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.toTerceiroModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TerceiroRepositoryImpl @Inject constructor(
    private val terceiroDatasourceRoom: TerceiroDatasourceRoom,
    private val terceiroDatasourceWebService: TerceiroDatasourceWebService,
): TerceiroRepository {

    override suspend fun addAllTerceiro(list: List<Terceiro>) {
        terceiroDatasourceRoom.addAllTerceiro(*list.map { it.toTerceiroModel() }.toTypedArray())
    }

    override suspend fun checkCPFTerceiro(cpf: String): Boolean {
        return terceiroDatasourceRoom.checkCPFTerceiro(cpf)
    }

    override suspend fun deleteAllTerceiro() {
        terceiroDatasourceRoom.deleteAllTerceiro()
    }

    override suspend fun getTerceiroCPF(cpf: String): Terceiro {
        return terceiroDatasourceRoom.getTerceiroCPF(cpf).toTerceiro()
    }

    override suspend fun getTerceiroListCPF(cpf: String): List<Terceiro> {
        return terceiroDatasourceRoom.getTerceiroListCPF(cpf).map { it.toTerceiro() }
    }

    override suspend fun recoverAllTerceiro(nroAparelho: Long): Flow<Result<List<Terceiro>>> = flow {
        terceiroDatasourceWebService.getAllTerceiro(nroAparelho)
            .catch { exception -> emit(Result.failure(exception)) }
            .collect { result ->
                result.fold(
                    onSuccess = { terceiroModelList ->
                        emit(Result.success(terceiroModelList.map { it.toTerceiro() }))
                    },
                    onFailure = { exception -> emit(Result.failure(exception)) }
                )
            }
    }

    override suspend fun getTerceiroId(id: Long): Terceiro {
        return terceiroDatasourceRoom.getTerceiroId(id).toTerceiro()
    }

}