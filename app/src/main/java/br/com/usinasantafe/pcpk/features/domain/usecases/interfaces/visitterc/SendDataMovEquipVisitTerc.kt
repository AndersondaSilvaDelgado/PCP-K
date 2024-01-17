package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc

interface SendDataMovEquipVisitTerc {

    suspend operator fun invoke(): Result<List<MovEquipVisitTerc>>

}