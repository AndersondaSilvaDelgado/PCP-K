package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.RecoverListMovEquipVisitTercOpen
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipVisitTercModel
import javax.inject.Inject

class RecoverListMovEquipVisitTercOpenImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val visitanteRepository: VisitanteRepository,
    private val terceiroRepository: TerceiroRepository,
): RecoverListMovEquipVisitTercOpen {

    override suspend fun invoke(): List<MovEquipVisitTercModel> {
        return movEquipVisitTercRepository.listMovEquipVisitTercOpen().map { movEquipVisitTerc ->
            movEquipVisitTerc.let {
                val dthr = it.dthrMovEquipVisitTerc.toString()
                val motorista = if(movEquipVisitTerc.tipoVisitTercMovEquipVisitTerc == TypeVisitTerc.TERCEIRO){
                    val terceiro = terceiroRepository.getTerceiroId(movEquipVisitTerc.idVisitTercMovEquipVisitTerc!!)
                    "TERCEIRO: ${terceiro.cpfTerceiro} - ${terceiro.nomeTerceiro}"
                } else {
                    val visitante = visitanteRepository.getVisitanteId(movEquipVisitTerc.idVisitTercMovEquipVisitTerc!!)
                    "VISITANTE: ${visitante.cpfVisitante} - ${visitante.nomeVisitante}"
                }
                return@map MovEquipVisitTercModel(dthr, motorista, it.veiculoMovEquipVisitTerc!!, it.placaMovEquipVisitTerc!!)
            }
        }
    }

}