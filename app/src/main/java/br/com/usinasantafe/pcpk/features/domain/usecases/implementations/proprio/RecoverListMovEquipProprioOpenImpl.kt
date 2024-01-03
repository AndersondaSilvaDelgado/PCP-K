package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.stable.Colab
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListMovEquipProprioOpen
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel
import javax.inject.Inject

class RecoverListMovEquipProprioOpenImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val equipRepository: EquipRepository,
    private val colabRepository: ColabRepository,
): RecoverListMovEquipProprioOpen {

    override suspend fun invoke(): List<MovEquipProprioModel> {
        return movEquipProprioRepository.listMovEquipProprioOpen().map { movEquipProprio ->
            movEquipProprio.let {
                val dthr = it.dthrMovEquipProprio.toString()
                val tipo = if (it.tipoMovEquipProprio == TypeMov.INPUT) "ENTRADA" else "SAIDA"
                val equip = equipRepository.getEquipId(it.idEquipMovEquipProprio!!).nroEquip.toString()
                val colab = colabRepository.getColabMatric(it.nroMatricColabMovEquipProprio!!)
                return@map MovEquipProprioModel(dthr, tipo, "${colab.matricColab} - ${colab.nomeColab}", equip)
            }
        }
    }

}