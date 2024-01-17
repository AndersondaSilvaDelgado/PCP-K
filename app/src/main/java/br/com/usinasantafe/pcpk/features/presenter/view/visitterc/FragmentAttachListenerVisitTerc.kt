package br.com.usinasantafe.pcpk.features.presenter.view.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface FragmentAttachListenerVisitTerc {
    fun popBackStack()
    fun goInicial()
    fun goMovVisitTercList()
    fun goMovVisitTercListEmpty()
    fun goVeiculo(flowApp: FlowApp, pos: Int = 0)
    fun goPlaca(flowApp: FlowApp, pos: Int = 0)
    fun goTipoVisitTerc()
    fun goCPFVisitTerc(typeAddOcupante: TypeAddOcupante)
    fun goNomeVisitTerc(cpf: String, typeAddOcupante: TypeAddOcupante)
    fun goPassagList()
    fun goDestino()
    fun goObserv(typeMov: TypeMov, pos: Int? = null)
    fun goDetalhe(pos: Int = 0)

}