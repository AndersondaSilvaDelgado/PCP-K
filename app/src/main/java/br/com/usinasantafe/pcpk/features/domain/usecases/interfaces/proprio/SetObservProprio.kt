package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface SetObservProprio {

    suspend operator fun invoke(observ: String): Boolean

}