package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioPassag
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.TerceiroRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.VisitanteRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetMotoristaPassagVisitTerc
import javax.inject.Inject

class SetMotoristaPassagVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
    private val visitanteRepository: VisitanteRepository,
    private val terceiroRepository: TerceiroRepository,
): SetMotoristaPassagVisitTerc {

    override suspend fun invoke(cpf: String, typeAddOcupante: TypeAddOcupante): Boolean {
        return try {
            val idVisitTerc = if(movEquipVisitTercRepository.getTipoVisitTercMovEquipVisitTerc() == TypeVisitTerc.TERCEIRO){
                visitanteRepository.getVisitanteCPF(cpf).idVisitante
            } else {
                terceiroRepository.getTerceiroCPF(cpf).idBDTerceiro
            }
            if(typeAddOcupante == TypeAddOcupante.ADDMOTORISTA){
                movEquipVisitTercRepository.setIdVisitTercMovEquipVisitTerc(idVisitTerc)
            } else {
                movEquipVisitTercPassagRepository.addPassag(idVisitTerc)
            }
        } catch (exception: Exception){
            false
        }
    }

}