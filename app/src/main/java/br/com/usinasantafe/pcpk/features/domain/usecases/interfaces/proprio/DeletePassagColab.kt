package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface DeletePassagColab {

    suspend operator fun invoke(pos: Int): Boolean

}