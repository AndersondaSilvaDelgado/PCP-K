package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverDetalheMovEquipProprio
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipProprioModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class RecoverDetalheMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val equipRepository: EquipRepository,
    private val colabRepository: ColabRepository,
): RecoverDetalheMovEquipProprio {

    override suspend fun invoke(pos: Int): DetalheMovEquipProprioModel {
        val mov = movEquipProprioRepository.listMovEquipProprioOpen()[pos]
        val dthr = "DATA/HORA: ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR")).format(mov.dthrMovEquipProprio)}"
        val tipoMov = if (mov.tipoMovEquipProprio!!.ordinal == 0) "ENTRADA" else "SAÍDA"
        val veiculo = "VEÍCULO: ${equipRepository.getEquipId(mov.idEquipMovEquipProprio!!).nroEquip}"
        val motorista = "MOTORISTA: ${mov.nroMatricColabMovEquipProprio} - ${colabRepository.getColabMatric(mov.nroMatricColabMovEquipProprio!!).nomeColab}"
        var passageiros = "PASSAGEIRO(S): "
        val passagList = movEquipProprioPassagRepository.listPassagIdMov(mov.idMovEquipProprio!!)
        for (passag in passagList) {
            passageiros += "${passag.nroMatricMovEquipProprioPassag} - ${colabRepository.getColabMatric(passag.nroMatricMovEquipProprioPassag!!).nomeColab} - "
        }
        val destino = "DESTINO: ${mov.destinoMovEquipProprio}"
        val equipSegList = movEquipProprioSegRepository.listEquipSegIdMov(mov.idMovEquipProprio!!)
        var equipSeg = "VEÍCULO SECUNDÁRIO: "
        for (equip in equipSegList){
            equipSeg += "${equipRepository.getEquipId(equip.idEquipMovEquipProprioSeg!!).nroEquip} - "
        }
        val notaFiscal = "NOTAL FISCAL: ${mov.nroNotaFiscalMovEquipProprio}"
        val observ = "OBS.: ${mov.observMovEquipProprio}"
        return DetalheMovEquipProprioModel(dthr, tipoMov, veiculo, motorista, passageiros, destino, equipSeg, notaFiscal, observ)
    }

}