package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.room.variable.MovEquipProprioDatasourceRoom
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.MovEquipProprioDatasourceWebService
import javax.inject.Inject

class MovEquipProprioRepositoryImpl @Inject constructor (
    private val movEquipProprioDatasourceRoom: MovEquipProprioDatasourceRoom,
    private val movEquipProprioDatasourceSharedPreferences: MovEquipProprioDatasourceSharedPreferences,
    private val movEquipProprioDatasourceWebService: MovEquipProprioDatasourceWebService,
) : MovEquipProprioRepository {

    override suspend fun checkAddMotoristaMovEquipProprio(): Boolean {
        val movEquipProprio = movEquipProprioDatasourceSharedPreferences.getMovEquipProprio()
        return movEquipProprio.nroMatricColabMovEquipProprio == null
    }

    override suspend fun getMatricMotoristaMovEquipProprio(): Long {
        return movEquipProprioDatasourceSharedPreferences.getMovEquipProprio().nroMatricColabMovEquipProprio!!
    }

    override suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.setMotoristaMovEquipProprio(nroMatric)
        } catch (exception: Exception){
            false
        }
        return true
    }

    override suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean {
        return try {
            movEquipProprioDatasourceSharedPreferences.startMovEquipProprio(typeMov)
        } catch (exception: Exception){
            false
        }
        return true
    }

}