package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.common.utils.BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_SEG
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioSeg
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioSegDatasourceSharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class MovEquipProprioSegDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MovEquipProprioSegDatasourceSharedPreferences {

    override suspend fun addEquipSeg(movEquipProprioSeg: MovEquipProprioSeg): Boolean {
        try {
            var data = listEquipSeg() as MutableList<MovEquipProprioSeg>
            data.add(movEquipProprioSeg)
            val editor = sharedPreferences.edit()
            val typeToken = object : TypeToken<List<MovEquipProprioSeg>>() {}.type
            editor.putString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_SEG, Gson().toJson(data, typeToken))
            editor.commit()
            data.clear()
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override suspend fun clearEquipSeg(): Boolean {
        try {
            val result = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_SEG, null)
            if(!result.isNullOrEmpty()){
                val editor = sharedPreferences.edit()
                editor.putString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_SEG, null)
                editor.commit()
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override suspend fun deleteEquipSeg(pos: Int): Boolean {
        try {
            var data = listEquipSeg() as MutableList<MovEquipProprioSeg>
            data.removeAt(pos)
            val editor = sharedPreferences.edit()
            val typeToken = object : TypeToken<List<MovEquipProprioSeg>>() {}.type
            editor.putString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_SEG, Gson().toJson(data, typeToken))
            editor.commit()
            data.clear()
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override suspend fun listEquipSeg(): List<MovEquipProprioSeg> {
        var data = mutableListOf<MovEquipProprioSeg>()
        val typeToken = object : TypeToken<List<MovEquipProprioSeg>>() {}.type
        val result = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_SEG, null)
        if(!result.isNullOrEmpty()){
            data = Gson().fromJson(result, typeToken)
        }
        return data
    }

}