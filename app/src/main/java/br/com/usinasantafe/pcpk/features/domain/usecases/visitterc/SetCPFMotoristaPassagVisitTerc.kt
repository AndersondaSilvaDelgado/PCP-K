package br.com.usinasantafe.pcpk.features.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface SetCPFMotoristaPassagVisitTerc {

    suspend operator fun invoke(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean

}

class SetCPFMotoristaPassagVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
    private val visitanteRepository: VisitanteRepository,
    private val terceiroRepository: TerceiroRepository,
): SetCPFMotoristaPassagVisitTerc {

    override suspend fun invoke(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int): Boolean {
        return when(typeAddOcupante) {
            TypeAddOcupante.ADDMOTORISTA -> {
                val idVisitTerc = getIdVisitTerc(cpf, movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc())
                movEquipVisitTercRepository.setIdVisitTercMovEquipVisitTerc(idVisitTerc)
            }
            TypeAddOcupante.ADDPASSAGEIRO -> {
                val idVisitTerc = getIdVisitTerc(cpf, movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc())
                movEquipVisitTercPassagRepository.addPassag(idVisitTerc)
            }
            TypeAddOcupante.CHANGEMOTORISTA -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                val idVisitTerc = getIdVisitTerc(cpf, movEquip.tipoVisitTercMovEquipVisitTerc!!)
                movEquipVisitTercRepository.updateMotoristaMovEquipVisitTerc(idVisitTerc, movEquip)
            }
            TypeAddOcupante.CHANGEPASSAGEIRO -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                val idVisitTerc = getIdVisitTerc(cpf, movEquip.tipoVisitTercMovEquipVisitTerc!!)
                movEquipVisitTercPassagRepository.addPassag(idVisitTerc, movEquip.idMovEquipVisitTerc!!)
            }
        }
    }

    private suspend fun getIdVisitTerc(cpf: String, typeVisitTerc: TypeVisitTerc): Long{
        return when(typeVisitTerc){
            TypeVisitTerc.VISITANTE -> visitanteRepository.getVisitanteCPF(cpf).idVisitante
            TypeVisitTerc.TERCEIRO -> terceiroRepository.getTerceiroCPF(cpf).idBDTerceiro
        }
    }

}