package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.RecoverListPassagVisitTerc
import javax.inject.Inject

class RecoverListPassagVisitTercImpl @Inject constructor(
    private val terceiroRepository: TerceiroRepository,
    private val visitanteRepository: VisitanteRepository,
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
) : RecoverListPassagVisitTerc {

    override suspend fun invoke(typeAddOcupante: TypeAddOcupante, pos: Int): List<String> {
        return when (typeAddOcupante) {
            TypeAddOcupante.ADDMOTORISTA,
            TypeAddOcupante.ADDPASSAGEIRO -> {
                movEquipVisitTercPassagRepository.listPassag().map {
                    return@map getNomeVisitTerc(it.idVisitTercMovEquipVisitTercPassag!!, movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc())
                }
            }

            TypeAddOcupante.CHANGEMOTORISTA,
            TypeAddOcupante.CHANGEPASSAGEIRO -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                movEquipVisitTercPassagRepository.listPassag(movEquip.idMovEquipVisitTerc!!).map {
                    return@map getNomeVisitTerc(it.idVisitTercMovEquipVisitTercPassag!!, movEquip.tipoVisitTercMovEquipVisitTerc!!)
                }
            }
        }

    }


    private suspend fun getNomeVisitTerc(id: Long, typeVisitTerc: TypeVisitTerc): String {
        return when(typeVisitTerc) {
            TypeVisitTerc.VISITANTE -> {
                val visitante =
                    visitanteRepository.getVisitanteId(id)
                "${visitante.cpfVisitante} - ${visitante.nomeVisitante}"
            }

            TypeVisitTerc.TERCEIRO -> {
                val terceiro =
                    terceiroRepository.getTerceiroId(id)
                "${terceiro.cpfTerceiro} - ${terceiro.nomeTerceiro}"
            }
        }
    }


}
