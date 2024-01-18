package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListColabPassag
import javax.inject.Inject

class RecoverListColabPassagImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val colabRepository: ColabRepository,
): RecoverListColabPassag {

    override suspend fun invoke(typeAddOcupante: TypeAddOcupante, pos: Int): List<String> {
        return when(typeAddOcupante){
            TypeAddOcupante.ADDMOTORISTA,
            TypeAddOcupante.ADDPASSAGEIRO -> {
                movEquipProprioPassagRepository.listPassag().map {
                    val colab = colabRepository.getColabMatric(it.nroMatricMovEquipProprioPassag!!)
                    return@map "${colab.matricColab} - ${colab.nomeColab}"
                }
            }
            TypeAddOcupante.CHANGEMOTORISTA,
            TypeAddOcupante.CHANGEPASSAGEIRO -> {
                val movEquip = movEquipProprioRepository.listMovEquipProprioEmpty()[pos]
                movEquipProprioPassagRepository.listPassag(movEquip.idMovEquipProprio!!).map {
                    val colab = colabRepository.getColabMatric(it.nroMatricMovEquipProprioPassag!!)
                    return@map "${colab.matricColab} - ${colab.nomeColab}"
                }
            }
        }
    }

}