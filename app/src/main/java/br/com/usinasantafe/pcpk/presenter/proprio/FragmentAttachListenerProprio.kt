package br.com.usinasantafe.pcpk.presenter.proprio

import br.com.usinasantafe.pcpk.utils.FlowApp
import br.com.usinasantafe.pcpk.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.utils.TypeAddEquip

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