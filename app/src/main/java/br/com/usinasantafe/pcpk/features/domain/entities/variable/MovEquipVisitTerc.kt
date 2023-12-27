package br.com.usinasantafe.pcpk.features.domain.entities.variable

import br.com.usinasantafe.pcpk.common.utils.StatusData
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import java.util.Date

data class MovEquipVisitTerc(
    var idMovEquipVisitTerc: Long? = null,
    var tipoMovEquipVisitTerc: TypeMov? = null,
    var idVisitTercMovEquipVisitTerc: Long? = null,
    var dthrMovEquipVisitTerc: Date = Date(),
    var nroMatricVigiaMovEquipVisitTerc: Long? = null,
    var idLocalMovEquipVisitTerc: Long? = null,
    var veiculoMovEquipVisitTerc: String? = null,
    var placaMovEquipVisitTerc: String? = null,
    var destinoMovEquipVisitTerc: String? = null,
    var observMovEquipVisitTerc: String? = null,
    var nroAparelhoMovEquipVisitTerc: Long? = null,
    var statusMovEquipVisitTerc: StatusData? = null,
    var statusSendMovEquipVisitTerc: StatusSend? = null,
    var movEquipVisitTercPassagList: List<MovEquipVisitTercPassag>? = emptyList(),
)
