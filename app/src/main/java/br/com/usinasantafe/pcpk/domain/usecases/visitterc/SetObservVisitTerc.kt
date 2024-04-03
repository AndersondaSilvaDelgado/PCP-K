package br.com.usinasantafe.pcpk.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.utils.FlowApp
import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import java.util.Date
import javax.inject.Inject

interface SetObservVisitTerc {
    suspend operator fun invoke(observ: String?, typeMov: TypeMov, pos: Int, flowApp: FlowApp): Boolean
}

class SetObservVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val saveMovEquipVisitTerc: SaveMovEquipVisitTerc,
) : SetObservVisitTerc {
    override suspend fun invoke(
        observ: String?,
        typeMov: TypeMov,
        pos: Int,
        flowApp: FlowApp
    ): Boolean {
        when (flowApp) {
            FlowApp.ADD -> {
                when (typeMov) {
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
                    else -> {
                        return false
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