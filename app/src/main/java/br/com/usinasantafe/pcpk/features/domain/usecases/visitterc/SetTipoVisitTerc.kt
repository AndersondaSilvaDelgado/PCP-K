package br.com.usinasantafe.pcpk.features.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface SetTipoVisitTerc {
    suspend operator fun invoke(typeVisitTerc: TypeVisitTerc): Boolean
}

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