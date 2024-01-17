package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface SetStatusSendCloseMovProprio {

    suspend operator fun invoke(pos: Int): Boolean

}