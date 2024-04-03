package br.com.usinasantafe.pcpk.domain.entities.variable

import br.com.usinasantafe.pcpk.utils.StatusData
import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.utils.TypeMov
import java.util.Date

data class MovEquipProprio(
    var idMovEquipProprio: Long? = null,
    var tipoMovEquipProprio: TypeMov? = null,
    var idEquipMovEquipProprio: Long? = null,
    var idLocalMovEquipProprio: Long? = null,
    var dthrMovEquipProprio: Date = Date(),
    var nroMatricVigiaMovEquipProprio: Long? = null,
    var nroMatricColabMovEquipProprio: Long? = null,
    var destinoMovEquipProprio: String? = null,
    var nroNotaFiscalMovEquipProprio: Long? = null,
    var observMovEquipProprio: String? = null,
    var statusSendMovEquipProprio: StatusSend = StatusSend.STARTED,
    var movEquipProprioSegList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioSeg>? = emptyList(),
    var movEquipProprioPassagList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioPassag>? = emptyList(),
)
