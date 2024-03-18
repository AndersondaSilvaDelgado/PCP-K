package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip

interface FragmentAttachListenerProprio {
    fun popBackStack()
    fun goInicial()
    fun goMovProprioList()
    fun goVeiculoProprio(typeAddEquip: TypeAddEquip, pos: Int = 0)
    fun goMatricColab(typeAddOcupante: TypeAddOcupante, pos: Int = 0)
    fun goNomeColab(matricColab: String, typeAddOcupante: TypeAddOcupante, pos: Int = 0)
    fun goPassagList(typeAddOcupante: TypeAddOcupante, pos: Int = 0)
    fun goVeicSegList(typeAddEquip: TypeAddEquip, pos: Int = 0)
    fun goDestino(flowApp: FlowApp, pos: Int = 0)
    fun goNotaFiscal(flowApp: FlowApp, pos: Int = 0)
    fun goObserv(flowApp: FlowApp, pos: Int = 0)
    fun goDetalhe(pos: Int = 0)
    fun getTypeAddEquip(): TypeAddEquip
    fun getTypeAddOcupante(): TypeAddOcupante
    fun getFlowApp(): FlowApp
    fun getPos(): Int
    fun getMatricColab(): String
}