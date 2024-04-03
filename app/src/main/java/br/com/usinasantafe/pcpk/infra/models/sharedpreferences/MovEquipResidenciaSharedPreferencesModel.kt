package br.com.usinasantafe.pcpk.infra.models.sharedpreferences

import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
import java.util.Date

data class MovEquipResidenciaSharedPreferencesModel(
    var dthrMovEquipResidencia: Date = Date(),
    var tipoMovEquipResidencia: TypeMov = TypeMov.INPUT,
    var motoristaMovEquipResidencia: String? = null,
    var veiculoMovEquipResidencia: String? = null,
    var placaMovEquipResidencia: String? = null,
    var observMovEquipResidencia: String? = null,
)

fun MovEquipResidenciaSharedPreferencesModel.modelSharedPreferencesToMovEquipResidencia(): br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia(
            dthrMovEquipResidencia = this.dthrMovEquipResidencia,
            tipoMovEquipResidencia = this.tipoMovEquipResidencia,
            motoristaMovEquipResidencia = this.motoristaMovEquipResidencia,
            veiculoMovEquipResidencia = this.veiculoMovEquipResidencia,
            placaMovEquipResidencia = this.placaMovEquipResidencia,
            observMovEquipResidencia = this.observMovEquipResidencia,
        )
    }
}

