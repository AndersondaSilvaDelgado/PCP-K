package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.extension.replaceFragment
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.databinding.ActivityProprioBinding
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity.Companion.KEY_FLOW_INITIAL
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity.Companion.FlowInitial
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DestinoProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DetalheMovEquipProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.MatricColabFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.MovEquipProprioListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.NomeColabFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.NotaFiscalProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.ObservProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.PassagColabListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.VeiculoProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.VeiculoSegProprioListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProprioActivity : AppCompatActivity(), FragmentAttachListenerProprio {

    private lateinit var binding: ActivityProprioBinding
    private var matricColab = ""
    private var typeAddEquip = TypeAddEquip.ADDVEICULO
    private var typeAddOcupante = TypeAddOcupante.ADDMOTORISTA
    private var flowApp = FlowApp.ADD
    private var pos = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProprioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goMovProprioList()
    }

    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

    override fun goMovProprioList() {
        replaceFragment(MovEquipProprioListFragment())
    }

    override fun goInicial() {
        val intent = Intent(this, InitialActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.apply {
            putExtra(KEY_FLOW_INITIAL, FlowInitial.RETURN.ordinal)
        }
        startActivity(intent)
    }

    override fun goVeiculoProprio(typeAddEquip: TypeAddEquip, pos: Int) {
        this.typeAddEquip = typeAddEquip
        this.pos = pos
        replaceFragment(VeiculoProprioFragment())
    }

    override fun goMatricColab(typeAddOcupante: TypeAddOcupante, pos: Int) {
        this.typeAddOcupante = typeAddOcupante
        this.pos = pos
        replaceFragment(MatricColabFragment())
    }

    override fun goPassagList(typeAddOcupante: TypeAddOcupante, pos: Int) {
        this.typeAddOcupante = typeAddOcupante
        this.pos = pos
        replaceFragment(PassagColabListFragment())
    }

    override fun goVeicSegList(typeAddEquip: TypeAddEquip, pos: Int) {
        this.typeAddEquip = typeAddEquip
        this.pos = pos
        replaceFragment(VeiculoSegProprioListFragment())
    }

    override fun goDestino(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(DestinoProprioFragment())
    }

    override fun goNotaFiscal(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(NotaFiscalProprioFragment())
    }

    override fun goObserv(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(ObservProprioFragment())
    }

    override fun goDetalhe(pos: Int) {
        this.pos = pos
        replaceFragment(DetalheMovEquipProprioFragment())
    }

    override fun goNomeColab(matricColab: String, typeAddOcupante: TypeAddOcupante, pos: Int) {
        this.typeAddOcupante = typeAddOcupante
        this.pos = pos
        this.matricColab = matricColab
        replaceFragment(NomeColabFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.proprio_manager_fragment, fragment)
    }

    override fun getTypeAddEquip(): TypeAddEquip {
        return typeAddEquip
    }

    override fun getTypeAddOcupante(): TypeAddOcupante {
        return typeAddOcupante
    }

    override fun getFlowApp(): FlowApp {
        return flowApp
    }

    override fun getPos(): Int {
        return pos
    }

    override fun getMatricColab(): String {
        return matricColab;
    }

}