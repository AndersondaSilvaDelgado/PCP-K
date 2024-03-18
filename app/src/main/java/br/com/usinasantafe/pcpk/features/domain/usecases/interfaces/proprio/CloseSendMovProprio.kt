package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface CloseSendMovProprio {

    suspend operator fun invoke(pos: Int): Boolean

}