package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface CheckNroEquip {

    suspend operator fun invoke(nro: String): Boolean

}