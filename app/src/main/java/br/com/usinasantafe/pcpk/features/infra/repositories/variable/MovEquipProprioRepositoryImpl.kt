package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.MovEquipProprioDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.room.variable.toMovEquipProprio
import javax.inject.Inject

class MovEquipProprioRepositoryImpl @Inject constructor (
    private val movEquipProprioDatasourceRoom: MovEquipProprioDatasourceRoom,
    private val movEquipProprioDatasourceSharedPreferences: MovEquipProprioDatasourceSharedPreferences,
) : MovEquipProprioRepository {

    override suspend fun checkAddMotoristaMovEquipProprio(): Boolean {
        val movEquipProprio = movEquipProprioDatasourceSharedPreferences.getMovEquipProprio()
        return movEquipProprio.nroMatricColabMovEquipProprio == null
    }

    override suspend fun checkAddVeiculoMovEquipProprio(): Boolean {
        val movEquipProprio = movEquipProprioDatasourceSharedPreferences.getMovEquipProprio()
        return movEquipProprio.idEquipMovEquipProprio == null
    }

    override suspend fun getMatricMotoristaMovEquipProprio(): Long {
        return movEquipProprioDatasourceSharedPreferences.getMovEquipProprio().nroMatricColabMovEquipProprio!!
    }

    override suspend fun getTipoMovMovEquipProprio(): TypeMov {
        return movEquipProprioDatasourceSharedPreferences.getMovEquipProprio().tipoMovEquipProprio!!
    }

    override suspend fun listMovEquipProprioOpen(): List<MovEquipProprio> {
        return movEquipProprioDatasourceRoom.listMovEquipProprioOpen().map { it.toMovEquipProprio() }
    }

    override suspend fun setDestinoMovEquipProprio(destino: String): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setDestinoMovEquipProprio(destino)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setMotoristaMovEquipProprio(nroMatric)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setNotaFiscalMovEquipProprio(notaFiscal: Long): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setNotaFiscalMovEquipProprio(notaFiscal)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setObservMovEquipProprio(observ: String): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setObservMovEquipProprio(observ)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun setVeiculoMovEquipProprio(idEquip: Long): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setVeiculoMovEquipProprio(idEquip)
        } catch (exception: Exception){
            false
        }
    }

    override suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.startMovEquipProprio(typeMov)
        } catch (exception: Exception){
            false
        }
    }

}