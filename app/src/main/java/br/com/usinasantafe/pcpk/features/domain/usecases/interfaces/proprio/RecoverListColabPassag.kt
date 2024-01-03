package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface RecoverListColabPassag {

    suspend operator fun invoke(): List<String>

}