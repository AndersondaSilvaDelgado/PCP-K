package br.com.usinasantafe.pcpk.domain.entities.variable

import br.com.usinasantafe.pcpk.utils.StatusData
import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.utils.TypeVisitTerc
import java.util.Date

data class MovEquipVisitTerc(
    var idMovEquipVisitTerc: Long? = null,
    var nroAparelhoMovEquipVisitTerc: Long? = null,
    var nroMatricVigiaMovEquipVisitTerc: Long? = null,
    var idLocalMovEquipVisitTerc: Long? = null,
    var dthrMovEquipVisitTerc: Date = Date(),
    var tipoMovEquipVisitTerc: TypeMov? = null,
    var idVisitTercMovEquipVisitTerc: Long? = null,
    var tipoVisitTercMovEquipVisitTerc: TypeVisitTerc? = null,
    var veiculoMovEquipVisitTerc: String? = null,
    var placaMovEquipVisitTerc: String? = null,
    var destinoMovEquipVisitTerc: String? = null,
    var observMovEquipVisitTerc: String? = null,
    var statusMovEquipVisitTerc: StatusData = StatusData.OPEN,
    var statusSendMovEquipVisitTerc: StatusSend = StatusSend.STARTED,
    var movEquipVisitTercPassagList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTercPassag>? = emptyList(),
)
