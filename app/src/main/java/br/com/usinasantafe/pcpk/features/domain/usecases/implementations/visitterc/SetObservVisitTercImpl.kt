package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

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
        return when (flowApp) {
            FlowApp.ADD -> {
                return when (typeMov!!) {
                    TypeMov.INPUT -> {
                        if (!movEquipVisitTercRepository.setObservMovEquipVisitTerc(observ)) return false
                        saveMovEquipVisitTerc()
                    }

                    TypeMov.OUTPUT -> {
                        val movEquipVisitTerc =
                            movEquipVisitTercRepository.listMovEquipVisitTercOpen()[pos]
                        movEquipVisitTercRepository.setStatusCloseMov(movEquipVisitTerc)
                        movEquipVisitTerc.observMovEquipVisitTerc = observ
                        movEquipVisitTerc.tipoMovEquipVisitTerc = TypeMov.OUTPUT
                        movEquipVisitTerc.dthrMovEquipVisitTerc = Date()
                        movEquipVisitTerc.destinoMovEquipVisitTerc = null
                        saveMovEquipVisitTerc(movEquipVisitTerc)
                    }
                }
            }

            FlowApp.CHANGE -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                movEquipVisitTercRepository.updateObservMovEquipVisitTerc(observ, movEquip)
            }
        }
    }
}