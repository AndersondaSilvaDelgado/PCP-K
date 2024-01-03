package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio

interface SetNotaFiscalProprio {

    suspend operator fun invoke(notaFiscal: String): Boolean

}