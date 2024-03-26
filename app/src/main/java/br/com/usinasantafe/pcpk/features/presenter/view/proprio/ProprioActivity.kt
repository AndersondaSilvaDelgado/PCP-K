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

    override fun goVeiculoProprio() {
        replaceFragment(VeiculoProprioFragment())
    }

    override fun goMatricColab() {
        replaceFragment(MatricColabFragment())
    }

    override fun goPassagList() {
        replaceFragment(PassagColabListFragment())
    }

    override fun goVeicSegList() {
        replaceFragment(VeiculoSegProprioListFragment())
    }

    override fun goDestino() {
        replaceFragment(DestinoProprioFragment())
    }

    override fun goNotaFiscal() {
        replaceFragment(NotaFiscalProprioFragment())
    }

    override fun goObserv() {
        replaceFragment(ObservProprioFragment())
    }

    override fun goDetalhe() {
        replaceFragment(DetalheMovEquipProprioFragment())
    }

    override fun goNomeColab() {
        replaceFragment(NomeColabFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.proprio_manager_fragment, fragment)
    }

}