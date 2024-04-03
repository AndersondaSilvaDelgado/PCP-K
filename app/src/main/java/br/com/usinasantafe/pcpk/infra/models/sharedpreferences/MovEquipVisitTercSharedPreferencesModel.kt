package br.com.usinasantafe.pcpk.infra.models.sharedpreferences

import br.com.usinasantafe.pcpk.utils.StatusData
import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
import java.util.Date

data class MovEquipVisitTercSharedPreferencesModel(
    var dthrMovEquipVisitTerc: Date = Date(),
    var tipoMovEquipVisitTerc: TypeMov = TypeMov.INPUT,
    var idVisitTercMovEquipVisitTerc: Long? = null,
    var tipoVisitTercMovEquipVisitTerc: TypeVisitTerc? = null,
    var veiculoMovEquipVisitTerc: String? = null,
    var placaMovEquipVisitTerc: String? = null,
    var destinoMovEquipVisitTerc: String? = null,
    var observMovEquipVisitTerc: String? = null,
)

fun MovEquipVisitTercSharedPreferencesModel.modelSharedPreferencesToMovEquipVisitTerc(): br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc {
    return with(this){
        br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc(
            dthrMovEquipVisitTerc = this.dthrMovEquipVisitTerc,
            tipoMovEquipVisitTerc = this.tipoMovEquipVisitTerc,
            idVisitTercMovEquipVisitTerc = this.idVisitTercMovEquipVisitTerc,
            tipoVisitTercMovEquipVisitTerc = this.tipoVisitTercMovEquipVisitTerc,
            veiculoMovEquipVisitTerc = this.veiculoMovEquipVisitTerc,
            placaMovEquipVisitTerc = this.placaMovEquipVisitTerc,
            destinoMovEquipVisitTerc = this.destinoMovEquipVisitTerc,
            observMovEquipVisitTerc = this.observMovEquipVisitTerc,
        )
    }
}
