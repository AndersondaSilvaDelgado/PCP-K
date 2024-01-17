package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.RecoverDataVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.model.DisplayDataVisitTercModel
import javax.inject.Inject

class RecoverDataVisitTercImpl @Inject constructor (
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val terceiroRepository: TerceiroRepository,
    private val visitanteRepository: VisitanteRepository,
): RecoverDataVisitTerc {

    override suspend fun invoke(cpf: String): DisplayDataVisitTercModel {
        return if(movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc() == TypeVisitTerc.TERCEIRO){
            val terceiroList = terceiroRepository.getTerceiroListCPF(cpf)
            var empresas = ""
            for(terceiro in terceiroList){
                empresas += terceiro.empresaTerceiro + "\n"
            }
            DisplayDataVisitTercModel("NOME TERCEIRO", terceiroList.single().nomeTerceiro, empresas)
        } else {
            val visitante = visitanteRepository.getVisitanteCPF(cpf)
            DisplayDataVisitTercModel("NOME VISITANTE", visitante.nomeVisitante, visitante.empresaVisitante)
        }
    }

}