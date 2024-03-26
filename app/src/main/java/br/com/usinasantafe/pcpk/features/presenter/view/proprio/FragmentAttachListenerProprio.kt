package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip

interface FragmentAttachListenerProprio {
    fun popBackStack()
    fun goInicial()
    fun goMovProprioList()
    fun goVeiculoProprio()
    fun goMatricColab()
    fun goNomeColab()
    fun goPassagList()
    fun goVeicSegList()
    fun goDestino()
    fun goNotaFiscal()
    fun goObserv()
    fun goDetalhe()
}