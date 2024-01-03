package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.common.utils.BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO
import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioDatasourceSharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class MovEquipProprioDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MovEquipProprioDatasourceSharedPreferences {
    override suspend fun getMovEquipProprio(): MovEquipProprio {
        val movEquipProprio = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO, null)!!
        return Gson().fromJson(movEquipProprio, MovEquipProprio::class.java)
    }

    override suspend fun setDestinoMovEquipProprio(destino: String): Boolean {
        try {
            val movEquipProprio = getMovEquipProprio()
            movEquipProprio.destinoMovEquipProprio = destino
            saveMovEquipProprio(movEquipProprio)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean {
        try {
            val movEquipProprio = getMovEquipProprio()
            movEquipProprio.nroMatricColabMovEquipProprio = nroMatric
            saveMovEquipProprio(movEquipProprio)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setNotaFiscalMovEquipProprio(notaFiscal: Long): Boolean {
        try {
            val movEquipProprio = getMovEquipProprio()
            movEquipProprio.nroNotaFiscalMovEquipProprio = notaFiscal
            saveMovEquipProprio(movEquipProprio)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setObservMovEquipProprio(observ: String): Boolean {
        try {
            val movEquipProprio = getMovEquipProprio()
            movEquipProprio.observMovEquipProprio = observ
            saveMovEquipProprio(movEquipProprio)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setVeiculoMovEquipProprio(idEquip: Long): Boolean {
        try {
            val movEquipProprio = getMovEquipProprio()
            movEquipProprio.idEquipMovEquipProprio = idEquip
            saveMovEquipProprio(movEquipProprio)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean {
        try {
            val movEquipProprio = MovEquipProprio()
            movEquipProprio.statusMovEquipProprio = StatusData.INITIATE
            movEquipProprio.tipoMovEquipProprio = typeMov
            saveMovEquipProprio(movEquipProprio)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    private fun saveMovEquipProprio(movEquipProprio: MovEquipProprio) {
        val editor = sharedPreferences.edit()
        editor.putString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO, Gson().toJson(movEquipProprio))
        editor.commit()
    }
}