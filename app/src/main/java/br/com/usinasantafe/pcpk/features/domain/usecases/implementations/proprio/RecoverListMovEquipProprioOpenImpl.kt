package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import android.util.Log
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListMovEquipProprioOpen
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class RecoverListMovEquipProprioOpenImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val equipRepository: EquipRepository,
    private val colabRepository: ColabRepository,
): RecoverListMovEquipProprioOpen {

    override suspend fun invoke(): List<MovEquipProprioModel> {
        return movEquipProprioRepository.listMovEquipProprioStarted().map { movEquip ->
            val dthr = "DATA/HORA: ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR")).format(movEquip.dthrMovEquipProprio)}"
            val tipo = if (movEquip.tipoMovEquipProprio == TypeMov.INPUT) "ENTRADA" else "SAIDA"
            val equip = "VEICULO: ${equipRepository.getEquipId(movEquip.idEquipMovEquipProprio!!).nroEquip}"
            val colab = colabRepository.getColabMatric(movEquip.nroMatricColabMovEquipProprio!!)
            val descrColab = "MOTORISTA: ${colab.matricColab} - ${colab.nomeColab}"
            return@map MovEquipProprioModel(dthr, tipo, descrColab, equip)
        }
    }

}