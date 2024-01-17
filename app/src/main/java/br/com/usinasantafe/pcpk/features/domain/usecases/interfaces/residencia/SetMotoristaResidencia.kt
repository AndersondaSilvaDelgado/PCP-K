package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

interface SetMotoristaResidencia {

    suspend operator fun invoke(motorista: String): Boolean

}