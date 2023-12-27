package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial

interface SetLocal {

    suspend operator fun invoke(pos: Int): Boolean

}