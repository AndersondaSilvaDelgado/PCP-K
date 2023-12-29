package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipProprioSegDatasourceSharedPreferences
import javax.inject.Inject

class MovEquipProprioSegDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MovEquipProprioSegDatasourceSharedPreferences {

}