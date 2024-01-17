package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

interface SetPlacaResidencia {

    suspend operator fun invoke(placa: String): Boolean

}