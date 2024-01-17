package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.RecoverListMovEquipVisitTercEmpty
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipVisitTercModel
import javax.inject.Inject

class RecoverListMovEquipVisitTercEmptyImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val visitanteRepository: VisitanteRepository,
    private val terceiroRepository: TerceiroRepository,
): RecoverListMovEquipVisitTercEmpty {

    override suspend fun invoke(): List<MovEquipVisitTercModel> {
        return movEquipVisitTercRepository.listMovEquipVisitTercEmpty().map { movEquipVisitTerc ->
            movEquipVisitTerc.let {
                val dthr = it.dthrMovEquipVisitTerc.toString()
                val motorista = if(movEquipVisitTerc.tipoVisitTercMovEquipVisitTerc == TypeVisitTerc.TERCEIRO){
                    val terceiro = terceiroRepository.getTerceiroId(movEquipVisitTerc.idVisitTercMovEquipVisitTerc!!)
                    "TERCEIRO: ${terceiro.cpfTerceiro} - ${terceiro.nomeTerceiro}"
                } else {
                    val visitante = visitanteRepository.getVisitanteId(movEquipVisitTerc.idVisitTercMovEquipVisitTerc!!)
                    "VISITANTE: ${visitante.cpfVisitante} - ${visitante.nomeVisitante}"
                }
                val tipo = if (it.tipoMovEquipVisitTerc == TypeMov.INPUT) "ENTRADA" else "SAIDA"
                return@map MovEquipVisitTercModel(dthr, motorista, it.veiculoMovEquipVisitTerc!!, it.placaMovEquipVisitTerc!!, tipo)
            }
        }
    }

}