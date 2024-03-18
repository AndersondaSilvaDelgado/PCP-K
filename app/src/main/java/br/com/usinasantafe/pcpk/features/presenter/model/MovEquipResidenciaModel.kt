package br.com.usinasantafe.pcpk.features.presenter.model

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
import java.text.SimpleDateFormat
import java.util.Locale

data class MovEquipResidenciaModel(
    val dthr: String,
    val motorista: String,
    val veiculo: String,
    val placa: String,
    val tipo: String,
)

fun MovEquipResidencia.toMovEquipResidenciaModel(): MovEquipResidenciaModel {
    return with(this){
        MovEquipResidenciaModel(
            dthr = "DATA/HORA: ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR")).format(this.dthrMovEquipResidencia)}",
            motorista = "MOTORISTA: ${this.motoristaMovEquipResidencia!!}",
            veiculo = "VEICULO: ${this.veiculoMovEquipResidencia!!}",
            placa = "PLACA: ${this.placaMovEquipResidencia!!}",
            tipo = if(this.tipoMovEquipResidencia == TypeMov.INPUT) "ENTRADA" else "SAÍDA"
        )
    }
}

