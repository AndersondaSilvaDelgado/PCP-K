package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface DeleteEquipProprioSeg {

    suspend operator fun invoke(pos: Int): Boolean

}