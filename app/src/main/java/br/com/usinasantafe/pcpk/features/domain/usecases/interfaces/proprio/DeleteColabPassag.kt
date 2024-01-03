package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface DeleteColabPassag {

    suspend operator fun invoke(pos: Int): Boolean

}