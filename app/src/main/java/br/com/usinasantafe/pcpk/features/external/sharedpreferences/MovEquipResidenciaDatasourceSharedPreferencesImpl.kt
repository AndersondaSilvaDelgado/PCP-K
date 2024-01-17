package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.common.utils.BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_RESIDENCIA
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipResidenciaDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.models.sharedpreferences.MovEquipResidenciaSharedPreferencesModel
import com.google.gson.Gson
import java.util.Date
import javax.inject.Inject

class MovEquipResidenciaDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MovEquipResidenciaDatasourceSharedPreferences {

    override suspend fun clearMovEquipResidencia(): Boolean {
        try {
            val editor = sharedPreferences.edit()
            editor.putString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_RESIDENCIA, null)
            editor.commit()
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun getMovEquipResidencia(): MovEquipResidenciaSharedPreferencesModel {
        val movEquipResidencia = sharedPreferences.getString(
            BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_RESIDENCIA, null)!!
        return Gson().fromJson(movEquipResidencia, MovEquipResidenciaSharedPreferencesModel::class.java)
    }

    override suspend fun setMotoristaMovEquipResidencia(motorista: String): Boolean {
        try {
            val movEquipResidencia = getMovEquipResidencia()
            movEquipResidencia.motoristaMovEquipResidencia = motorista
            saveMovEquipResidencia(movEquipResidencia)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setObservMovEquipResidencia(observ: String?): Boolean {
        try {
            val movEquipResidencia = getMovEquipResidencia()
            movEquipResidencia.observMovEquipResidencia = observ
            saveMovEquipResidencia(movEquipResidencia)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setPlacaMovEquipResidencia(placa: String): Boolean {
        try {
            val movEquipResidencia = getMovEquipResidencia()
            movEquipResidencia.placaMovEquipResidencia = placa
            saveMovEquipResidencia(movEquipResidencia)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun setVeiculoMovEquipResidencia(veiculo: String): Boolean {
        try {
            val movEquipResidencia = getMovEquipResidencia()
            movEquipResidencia.veiculoMovEquipResidencia = veiculo
            saveMovEquipResidencia(movEquipResidencia)
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    override suspend fun startMovEquipResidencia(): Boolean {
        try {
            saveMovEquipResidencia(MovEquipResidenciaSharedPreferencesModel())
        } catch(exception: Exception) {
            return false
        }
        return true
    }

    private fun saveMovEquipResidencia(movEquipResidenciaSharedPreferencesModel: MovEquipResidenciaSharedPreferencesModel) {
        val editor = sharedPreferences.edit()
        editor.putString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_RESIDENCIA, Gson().toJson(movEquipResidenciaSharedPreferencesModel))
        editor.commit()
    }
}