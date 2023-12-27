package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial


interface RecoverListLocal {

    suspend operator fun invoke(): List<String>

}