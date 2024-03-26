package br.com.usinasantafe.pcpk.features.presenter.view.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface FragmentAttachListenerResidencia {
    fun popBackStack()
    fun goInicial()
    fun goMovResidenciaList()
    fun goMovResidenciaStartedList()
    fun goVeiculo()
    fun goPlaca()
    fun goMotorista()
    fun goObserv()
    fun goDetalhe()
}