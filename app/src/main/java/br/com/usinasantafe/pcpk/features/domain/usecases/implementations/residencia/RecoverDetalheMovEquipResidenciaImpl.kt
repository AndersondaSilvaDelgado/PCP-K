package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.RecoverDetalheMovEquipResidencia
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipResidenciaModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class RecoverDetalheMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): RecoverDetalheMovEquipResidencia {

    override suspend fun invoke(pos: Int): DetalheMovEquipResidenciaModel {
        val mov = movEquipResidenciaRepository.listMovEquipResidenciaStarted()[pos]
        val dthr = "DATA/HORA: ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR")).format(mov.dthrMovEquipResidencia)}"
        val tipoMov = if (mov.tipoMovEquipResidencia!!.ordinal == 0) "ENTRADA" else "SAÍDA"
        val veiculo = "VEÍCULO: ${mov.veiculoMovEquipResidencia}"
        val placa = "PLACA: ${mov.placaMovEquipResidencia}"
        val motorista = "MOTORISTA: ${mov.motoristaMovEquipResidencia}"
        val observ = "OBSERV.: ${mov.observMovEquipResidencia}"
        return DetalheMovEquipResidenciaModel(dthr, tipoMov, veiculo, placa, motorista, observ)
    }

}