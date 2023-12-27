package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial


interface SetMatricVigia {

    suspend operator fun invoke(matricVigia: String): Boolean

}