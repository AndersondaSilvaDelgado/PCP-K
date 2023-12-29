package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipResidenciaDatasourceSharedPreferences
import javax.inject.Inject

class MovEquipResidenciaDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MovEquipResidenciaDatasourceSharedPreferences {

}