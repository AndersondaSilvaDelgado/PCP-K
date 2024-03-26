package br.com.usinasantafe.pcpk.features.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.presenter.model.DisplayDataVisitTercModel
import javax.inject.Inject

interface RecoverDataVisitTerc {
    suspend operator fun invoke(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int): DisplayDataVisitTercModel
}

class RecoverDataVisitTercImpl @Inject constructor (
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val terceiroRepository: TerceiroRepository,
    private val visitanteRepository: VisitanteRepository,
): RecoverDataVisitTerc {

    override suspend fun invoke(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int): DisplayDataVisitTercModel {
        return when(typeAddOcupante) {
            TypeAddOcupante.ADDMOTORISTA,
            TypeAddOcupante.ADDPASSAGEIRO -> {
                getNomeVisitTerc(cpf, movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc())
            }

            TypeAddOcupante.CHANGEMOTORISTA,
            TypeAddOcupante.CHANGEPASSAGEIRO -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                getNomeVisitTerc(cpf, movEquip.tipoVisitTercMovEquipVisitTerc!!)
            }
        }
    }

    private suspend fun getNomeVisitTerc(cpf: String, typeVisitTerc: TypeVisitTerc): DisplayDataVisitTercModel {
        return when(typeVisitTerc) {
            TypeVisitTerc.VISITANTE -> {
                val visitante = visitanteRepository.getVisitanteCPF(cpf)
                DisplayDataVisitTercModel("NOME VISITANTE", visitante.nomeVisitante, visitante.empresaVisitante)
            }
            TypeVisitTerc.TERCEIRO -> {
                val terceiroList = terceiroRepository.getTerceiroListCPF(cpf)
                var empresas = ""
                for(terceiro in terceiroList){
                    empresas += terceiro.empresaTerceiro + "\n"
                }
                DisplayDataVisitTercModel("NOME TERCEIRO", terceiroList.single().nomeTerceiro, empresas)
            }
        }
    }

}