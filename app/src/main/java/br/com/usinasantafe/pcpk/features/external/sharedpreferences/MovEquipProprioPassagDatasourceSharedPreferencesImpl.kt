package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.common.utils.BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_PASSAG
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioPassag
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioSeg
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioPassagDatasourceSharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class MovEquipProprioPassagDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MovEquipProprioPassagDatasourceSharedPreferences {

    override suspend fun addPassag(movEquipProprioPassag: MovEquipProprioPassag): Boolean {
        try {
            var data = listPassag() as MutableList<MovEquipProprioPassag>
            data.add(movEquipProprioPassag)
            val editor = sharedPreferences.edit()
            val typeToken = object : TypeToken<List<MovEquipProprioSeg>>() {}.type
            editor.putString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_PASSAG, Gson().toJson(data, typeToken))
            editor.commit()
            data.clear()
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override suspend fun countPassag(): Int {
        val result = sharedPreferences.getString(
            BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_PASSAG, null)
        if(result.isNullOrEmpty()){
            return 0
        }
        val typeToken = object : TypeToken<List<MovEquipProprioPassag>>() {}.type
        var data: MutableList<MovEquipProprioPassag> = Gson().fromJson(result, typeToken)
        var count = data.size
        data.clear()
        return count
    }

    override suspend fun deletePassag(pos: Int): Boolean {
        try {
            var data = listPassag() as MutableList<MovEquipProprioPassag>
            data.removeAt(pos)
            val editor = sharedPreferences.edit()
            val typeToken = object : TypeToken<List<MovEquipProprioPassag>>() {}.type
            editor.putString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_PASSAG, Gson().toJson(data, typeToken))
            editor.commit()
            data.clear()
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override suspend fun listPassag(): List<MovEquipProprioPassag> {
        var data = mutableListOf<MovEquipProprioPassag>()
        val typeToken = object : TypeToken<List<MovEquipProprioPassag>>() {}.type
        val result = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_MOV_EQUIP_PROPRIO_PASSAG, null)
        if(!result.isNullOrEmpty()){
            data = Gson().fromJson(result, typeToken)
        }
        return data
    }

}