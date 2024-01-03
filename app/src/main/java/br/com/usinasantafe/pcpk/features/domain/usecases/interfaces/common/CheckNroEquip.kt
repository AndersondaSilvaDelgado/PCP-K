package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

interface CheckNroEquip {

    suspend operator fun invoke(nro: String): Boolean

}