package br.com.usinasantafe.pcpk.features.domain.entities.variable

import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.common.utils.TypeMov
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
    var nroAparelhoMovEquipProprio: Long? = null,
    var statusMovEquipProprio: StatusData? = null,
    var statusSendMovEquipProprio: StatusSend? = null,
    var movEquipProprioSegList: List<MovEquipProprioSeg>? = emptyList(),
    var movEquipProprioPassagList: List<MovEquipProprioPassag>? = emptyList(),
)
