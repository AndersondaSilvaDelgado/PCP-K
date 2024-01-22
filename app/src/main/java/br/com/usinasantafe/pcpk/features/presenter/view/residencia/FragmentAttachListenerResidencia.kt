package br.com.usinasantafe.pcpk.features.presenter.view.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface FragmentAttachListenerResidencia {
    fun popBackStack()
    fun goInicial()
    fun goMovResidenciaList()
    fun goMovResidenciaStartedList()
    fun goVeiculo(flowApp: FlowApp, pos: Int = 0)
    fun goPlaca(flowApp: FlowApp, pos: Int = 0)
    fun goMotorista(flowApp: FlowApp, pos: Int = 0)
    fun goObserv(typeMov: TypeMov?, flowApp: FlowApp, pos: Int = 0)
    fun goDetalhe(pos: Int)

    fun getFlowApp(): FlowApp
    fun getPos(): Int
    fun getTypeMov(): TypeMov?
}