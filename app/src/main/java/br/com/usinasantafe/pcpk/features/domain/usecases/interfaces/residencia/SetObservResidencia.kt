package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface SetObservResidencia {

    suspend operator fun invoke(observ: String?, typeMov: TypeMov, pos: Int? = 0): Boolean

}