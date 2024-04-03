package br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences

import br.com.usinasantafe.pcpk.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.infra.models.sharedpreferences.MovEquipVisitTercSharedPreferencesModel

interface MovEquipVisitTercDatasourceSharedPreferences {

    suspend fun clearMovEquipVisitTerc(): Boolean

    suspend fun getMovEquipVisitTerc(): MovEquipVisitTercSharedPreferencesModel

    suspend fun setDestinoMovEquipVisitTerc(destino: String): Boolean

    suspend fun setIdVisitTercMovEquipVisitTerc(idVisitTerc: Long): Boolean

    suspend fun setObservMovEquipVisitTerc(observ: String?): Boolean

    suspend fun setPlacaMovEquipVisitTerc(placa: String): Boolean

    suspend fun setTipoVisitTercMovEquipVisitTerc(typeVisitTerc: TypeVisitTerc): Boolean

    suspend fun setVeiculoMovEquipVisitTerc(veiculo: String): Boolean

    suspend fun startMovEquipVisitTerc(): Boolean

}