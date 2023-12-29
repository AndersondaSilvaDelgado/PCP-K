package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.MovEquipVisitTercPassagDatasourceSharedPreferences
import javax.inject.Inject

class MovEquipVisitTercPassagDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MovEquipVisitTercPassagDatasourceSharedPreferences {

}