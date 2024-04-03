package br.com.usinasantafe.pcpk.infra.models.sharedpreferences

import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
import java.util.Date

data class MovEquipProprioSharedPreferencesModel(
    var dthrMovEquipProprio: Date = Date(),
    var tipoMovEquipProprio: TypeMov,
    var idEquipMovEquipProprio: Long? = null,
    var nroMatricColabMovEquipProprio: Long? = null,
    var destinoMovEquipProprio: String? = null,
    var nroNotaFiscalMovEquipProprio: Long? = null,
    var observMovEquipProprio: String? = null,
)

fun MovEquipProprioSharedPreferencesModel.modelSharedPreferencesToMovEquipProprio(): br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio(
            dthrMovEquipProprio = this.dthrMovEquipProprio,
            tipoMovEquipProprio = this.tipoMovEquipProprio,
            idEquipMovEquipProprio = this.idEquipMovEquipProprio,
            nroMatricColabMovEquipProprio = this.nroMatricColabMovEquipProprio,
            destinoMovEquipProprio = this.destinoMovEquipProprio,
            nroNotaFiscalMovEquipProprio = this.nroNotaFiscalMovEquipProprio,
            observMovEquipProprio = this.observMovEquipProprio,
        )
    }
}
