package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SaveMovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetObservVisitTerc
import java.util.Date
import javax.inject.Inject

class SetObservVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val saveMovEquipVisitTerc: SaveMovEquipVisitTerc,
): SetObservVisitTerc {
    override suspend fun invoke(observ: String?, typeMov: TypeMov, pos: Int?): Boolean {
        try {
            if (typeMov == TypeMov.INPUT){
                if(!movEquipVisitTercRepository.setObservMovEquipVisitTerc(observ)) return false
                return saveMovEquipVisitTerc()
            } else {
                val movEquipVisitTerc = movEquipVisitTercRepository.listMovEquipVisitTercOpen()[pos!!]
                movEquipVisitTercRepository.setStatusClosedMov(movEquipVisitTerc)
                movEquipVisitTerc.observMovEquipVisitTerc = observ
                movEquipVisitTerc.tipoMovEquipVisitTerc = TypeMov.OUTPUT
                movEquipVisitTerc.dthrMovEquipVisitTerc = Date()
                movEquipVisitTerc.destinoMovEquipVisitTerc = null
                return saveMovEquipVisitTerc(movEquipVisitTerc)
            }
        } catch (exception: Exception){
            return false
        }
    }
}