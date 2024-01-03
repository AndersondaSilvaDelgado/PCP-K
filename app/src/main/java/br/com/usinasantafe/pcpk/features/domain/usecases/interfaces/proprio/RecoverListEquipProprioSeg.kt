package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface RecoverListEquipProprioSeg {

    suspend operator fun invoke(): List<String>

}