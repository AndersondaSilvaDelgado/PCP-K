package br.com.usinasantafe.pcpk.features.presenter.model

data class MovEquipVisitTercModel(
    val dthr: String,
    val motorista: String,
    val veiculo: String,
    val placa: String,
    val tipo: String? = null,
)
