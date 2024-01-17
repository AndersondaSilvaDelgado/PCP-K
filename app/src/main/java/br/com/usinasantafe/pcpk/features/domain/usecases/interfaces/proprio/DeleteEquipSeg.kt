package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface DeleteEquipSeg {

    suspend operator fun invoke(pos: Int): Boolean

}