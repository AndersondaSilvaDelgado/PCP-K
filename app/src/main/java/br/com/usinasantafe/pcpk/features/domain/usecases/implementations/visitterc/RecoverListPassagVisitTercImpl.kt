package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

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
): RecoverListPassagVisitTerc {

    override suspend fun invoke(): List<String> {
        return movEquipVisitTercPassagRepository.listPassag().map {
            val passag = if(movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc() == TypeVisitTerc.TERCEIRO){
                var terceiro = terceiroRepository.getTerceiroId(it.idVisitTercMovEquipVisitTercPassag!!)
                "${terceiro.cpfTerceiro} - ${terceiro.nomeTerceiro}"
            } else {
                var visitante = visitanteRepository.getVisitanteId(it.idVisitTercMovEquipVisitTercPassag!!)
                "${visitante.cpfVisitante} - ${visitante.nomeVisitante}"
            }
            return@map passag
        }
    }

}