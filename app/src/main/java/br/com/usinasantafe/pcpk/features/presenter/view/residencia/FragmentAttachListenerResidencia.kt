package br.com.usinasantafe.pcpk.features.presenter.view.residencia

import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface FragmentAttachListenerResidencia {
    fun popBackStack()
    fun goInicial()
    fun goMovResidenciaList()
    fun goVeiculo()
    fun goPlaca()
    fun goMotorista()
    fun goObserv(typeMov: TypeMov, pos: Int? = null)
}