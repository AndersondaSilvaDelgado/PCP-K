package br.com.usinasantafe.pcpk.domain.entities.variable

import br.com.usinasantafe.pcpk.utils.StatusData
import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.utils.TypeMov
import java.util.Date

data class MovEquipResidencia(
    var idMovEquipResidencia: Long? = null,
    var nroAparelhoMovEquipResidencia: Long? = null,
    var nroMatricVigiaMovEquipResidencia: Long? = null,
    var idLocalMovEquipResidencia: Long? = null,
    var dthrMovEquipResidencia: Date = Date(),
    var tipoMovEquipResidencia: TypeMov? = null,
    var veiculoMovEquipResidencia: String? = null,
    var placaMovEquipResidencia: String? = null,
    var motoristaMovEquipResidencia: String? = null,
    var observMovEquipResidencia: String? = null,
    var statusMovEquipResidencia: StatusData = StatusData.OPEN,
    var statusSendMovEquipResidencia: StatusSend = StatusSend.STARTED,
)
