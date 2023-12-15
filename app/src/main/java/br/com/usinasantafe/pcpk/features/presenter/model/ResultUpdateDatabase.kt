package br.com.usinasantafe.pcpk.features.presenter.model

data class ResultUpdateDatabase(
    val count: Int,
    val describe: String,
    val size: Int,
    var percentage: Int = ((count * 100) / size)
)
