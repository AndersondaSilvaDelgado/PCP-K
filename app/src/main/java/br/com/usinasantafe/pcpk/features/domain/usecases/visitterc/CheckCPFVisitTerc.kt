package br.com.usinasantafe.pcpk.features.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface CheckCPFVisitTerc {
    suspend operator fun invoke(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean
}

class CheckCPFVisitTercImpl @Inject constructor (
    private val visitanteRepository: VisitanteRepository,
    private val terceiroRepository: TerceiroRepository,
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): CheckCPFVisitTerc {

    override suspend fun invoke(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean {
        when(typeAddOcupante){
            TypeAddOcupante.ADDMOTORISTA,
            TypeAddOcupante.ADDPASSAGEIRO -> {
                return when(movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc()){
                    TypeVisitTerc.VISITANTE -> visitanteRepository.checkCPFVisitante(cpf)
                    TypeVisitTerc.TERCEIRO -> terceiroRepository.checkCPFTerceiro(cpf)
                }
            }
            TypeAddOcupante.CHANGEMOTORISTA,
            TypeAddOcupante.CHANGEPASSAGEIRO -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                return when(movEquip.tipoVisitTercMovEquipVisitTerc!!){
                    TypeVisitTerc.VISITANTE -> visitanteRepository.checkCPFVisitante(cpf)
                    TypeVisitTerc.TERCEIRO -> terceiroRepository.checkCPFTerceiro(cpf)
                }
            }
        }

    }

}