package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface SetDestinoProprio {

    suspend operator fun invoke(destino: String): Boolean

}