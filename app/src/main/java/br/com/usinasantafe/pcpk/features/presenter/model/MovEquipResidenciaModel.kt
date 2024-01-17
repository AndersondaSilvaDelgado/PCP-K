package br.com.usinasantafe.pcpk.features.presenter.model

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia

data class MovEquipResidenciaModel(
    val dthr: String,
    val motorista: String,
    val veiculo: String,
    val placa: String,
)

fun MovEquipResidencia.toMovEquipResidenciaModel(): MovEquipResidenciaModel {
    return with(this){
        MovEquipResidenciaModel(
            dthr = this.dthrMovEquipResidencia.toString(),
            motorista = this.motoristaMovEquipResidencia!!,
            veiculo = this.veiculoMovEquipResidencia!!,
            placa = this.placaMovEquipResidencia!!,
        )
    }
}

