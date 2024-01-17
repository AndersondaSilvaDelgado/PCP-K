package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc

interface SaveMovEquipVisitTerc {

    suspend operator fun invoke(): Boolean

    suspend operator fun invoke(movEquipVisitTerc: MovEquipVisitTerc): Boolean

}