package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

interface CloseSendMovResidencia {

    suspend operator fun invoke(pos: Int): Boolean

}