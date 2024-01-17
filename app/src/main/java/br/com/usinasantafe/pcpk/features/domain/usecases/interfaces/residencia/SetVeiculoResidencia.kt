package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

interface SetVeiculoResidencia {

    suspend operator fun invoke(veiculo: String): Boolean

}