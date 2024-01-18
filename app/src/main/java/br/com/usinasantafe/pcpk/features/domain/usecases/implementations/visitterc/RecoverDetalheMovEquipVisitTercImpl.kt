package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.RecoverDetalheMovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipVisitTercModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class RecoverDetalheMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
    private val terceiroRepository: TerceiroRepository,
    private val visitanteRepository: VisitanteRepository,
): RecoverDetalheMovEquipVisitTerc {

    override suspend fun invoke(pos: Int): DetalheMovEquipVisitTercModel {
        val mov = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
        val dthr = "DATA/HORA: ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR")).format(mov.dthrMovEquipVisitTerc)}"
        val tipoMov = if (mov.tipoMovEquipVisitTerc!!.ordinal == 0) "ENTRADA" else "SAÍDA"
        val veiculo = "VEÍCULO: ${mov.veiculoMovEquipVisitTerc}"
        val placa = "PLACA: ${mov.placaMovEquipVisitTerc}"
        val tipoVisitTerc = if (mov.tipoVisitTercMovEquipVisitTerc!!.ordinal == 0) "VISITANTE" else "TERCEIRO"
        val motorista = when(mov.tipoVisitTercMovEquipVisitTerc!!){
            TypeVisitTerc.VISITANTE -> {
                val visit = visitanteRepository.getVisitanteId(mov.idVisitTercMovEquipVisitTerc!!)
                "${visit.cpfVisitante} - ${visit.nomeVisitante}"
            }
            TypeVisitTerc.TERCEIRO -> {
                val terc = terceiroRepository.getTerceiroId(mov.idVisitTercMovEquipVisitTerc!!)
                "${terc.cpfTerceiro} - ${terc.nomeTerceiro}"
            }
        }
        var passageiros = "PASSAGEIRO(S): "
        val passagList = movEquipVisitTercPassagRepository.listPassag(mov.idMovEquipVisitTerc!!)
        for (passag in passagList) {
            passageiros += when(mov.tipoVisitTercMovEquipVisitTerc!!){
                TypeVisitTerc.VISITANTE -> {
                    val visit = visitanteRepository.getVisitanteId(passag.idVisitTercMovEquipVisitTercPassag!!)
                    "${visit.cpfVisitante} - ${visit.nomeVisitante}"
                }
                TypeVisitTerc.TERCEIRO -> {
                    val terc = terceiroRepository.getTerceiroId(passag.idVisitTercMovEquipVisitTercPassag!!)
                    "${terc.cpfTerceiro} - ${terc.nomeTerceiro}"
                }
            }
        }
        val destino = "DESTINO: ${mov.destinoMovEquipVisitTerc}"
        val observ = "OBSERV.: ${mov.observMovEquipVisitTerc}"
        return DetalheMovEquipVisitTercModel(dthr, tipoMov, veiculo, placa, tipoVisitTerc, motorista, passageiros, destino, observ)
    }

}