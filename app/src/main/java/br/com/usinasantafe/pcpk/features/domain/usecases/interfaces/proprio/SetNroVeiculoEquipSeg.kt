package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface SetNroVeiculoEquipSeg {

    suspend operator fun invoke(nroEquip: String): Boolean

}