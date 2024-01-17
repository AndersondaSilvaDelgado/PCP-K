package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.CheckCPFVisitTerc
import javax.inject.Inject

class CheckCPFVisitTercImpl @Inject constructor (
    private val visitanteRepository: VisitanteRepository,
    private val terceiroRepository: TerceiroRepository,
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): CheckCPFVisitTerc {

    override suspend fun invoke(cpf: String): Boolean {
        if(movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc() == TypeVisitTerc.TERCEIRO){
            return terceiroRepository.checkCPFTerceiro(cpf)
        }
        return visitanteRepository.checkCPFVisitante(cpf)
    }

}