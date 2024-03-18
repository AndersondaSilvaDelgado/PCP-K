package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import android.util.Log
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SaveMovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetObservVisitTerc
import java.util.Date
import javax.inject.Inject

class SetObservVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val saveMovEquipVisitTerc: SaveMovEquipVisitTerc,
) : SetObservVisitTerc {
    override suspend fun invoke(
        observ: String?,
        typeMov: TypeMov?,
        pos: Int,
        flowApp: FlowApp
    ): Boolean {
        when (flowApp) {
            FlowApp.ADD -> {
                when (typeMov!!) {
                    TypeMov.INPUT -> {
                        if (!movEquipVisitTercRepository.setObservMovEquipVisitTerc(observ)) return false
                        return saveMovEquipVisitTerc()
                    }
                    TypeMov.OUTPUT -> {
                        val movEquipVisitTerc =
                            movEquipVisitTercRepository.listMovEquipVisitTercOpen()[pos]
                        if (!movEquipVisitTercRepository.setStatusCloseMov(movEquipVisitTerc)) return false
                        movEquipVisitTerc.observMovEquipVisitTerc = observ
                        movEquipVisitTerc.tipoMovEquipVisitTerc = TypeMov.OUTPUT
                        movEquipVisitTerc.dthrMovEquipVisitTerc = Date()
                        movEquipVisitTerc.destinoMovEquipVisitTerc = null
                        return saveMovEquipVisitTerc(movEquipVisitTerc)
                    }
                }
            }

            FlowApp.CHANGE -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                return movEquipVisitTercRepository.updateObservMovEquipVisitTerc(observ, movEquip)
            }
        }
    }
}