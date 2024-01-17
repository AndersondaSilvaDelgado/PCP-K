package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetTipoVisitTerc
import javax.inject.Inject

class SetTipoVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): SetTipoVisitTerc {

    override suspend fun invoke(typeVisitTerc: TypeVisitTerc): Boolean {
        return try {
            movEquipVisitTercRepository.setTipoVisitTercMovEquipVisitTerc(typeVisitTerc)
        } catch (exception: Exception) {
            false
        }
    }

}