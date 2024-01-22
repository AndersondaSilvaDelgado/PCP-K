package br.com.usinasantafe.pcpk.features.presenter.view.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface FragmentAttachListenerVisitTerc {
    fun popBackStack()
    fun goInicial()
    fun goMovVisitTercList()
    fun goMovVisitTercListStarted()
    fun goVeiculo(flowApp: FlowApp, pos: Int = 0)
    fun goPlaca(flowApp: FlowApp, pos: Int = 0)
    fun goTipoVisitTerc()
    fun goCPFVisitTerc(typeAddOcupante: TypeAddOcupante, pos: Int = 0)
    fun goNomeVisitTerc(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int = 0)
    fun goPassagList(typeAddOcupante: TypeAddOcupante, pos: Int = 0)
    fun goDestino(flowApp: FlowApp, pos: Int = 0)
    fun goObserv(typeMov: TypeMov?, flowApp: FlowApp, pos: Int = 0)
    fun goDetalhe(pos: Int = 0)
    fun getTypeAddOcupante(): TypeAddOcupante
    fun getFlowApp(): FlowApp
    fun getPos(): Int
    fun getCPF(): String
    fun getTypeMov(): TypeMov?

}