package br.com.usinasantafe.pcpk.features.presenter.view.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface FragmentAttachListenerVisitTerc {
    fun popBackStack()
    fun goInicial()
    fun goMovVisitTercList()
    fun goMovVisitTercListStarted()
    fun goVeiculo()
    fun goPlaca()
    fun goTipoVisitTerc()
    fun goCPFVisitTerc()
    fun goNomeVisitTerc()
    fun goPassagList()
    fun goDestino()
    fun goObserv()
    fun goDetalhe()
}