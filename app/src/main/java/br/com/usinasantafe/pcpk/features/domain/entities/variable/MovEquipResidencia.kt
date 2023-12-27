package br.com.usinasantafe.pcpk.features.domain.entities.variable

import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import java.util.Date

data class MovEquipResidencia(
    var idMovEquipResidencia: Long? = null,
    var tipoMovEquipResidencia: TypeMov? = null,
    var dthrMovEquipResidencia: Date = Date(),
    var nroMatricVigiaMovEquipResidencia: Long? = null,
    var idLocalMovEquipResidencia: Long? = null,
    var nomeVisitanteMovEquipResidencia: String? = null,
    var veiculoMovEquipResidencia: String? = null,
    var placaMovEquipResidencia: String? = null,
    var observMovEquipResidencia: String? = null,
    var nroAparelhoMovEquipResidencia: Long? = null,
    var statusMovEquipResidencia: StatusData? = null,
    var statusSendMovEquipResidencia: StatusSend? = null,
)
