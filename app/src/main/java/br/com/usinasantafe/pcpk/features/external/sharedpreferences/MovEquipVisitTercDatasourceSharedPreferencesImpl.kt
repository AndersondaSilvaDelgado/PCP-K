package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipVisitTercDatasourceSharedPreferences
import javax.inject.Inject

class MovEquipVisitTercDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MovEquipVisitTercDatasourceSharedPreferences {

}