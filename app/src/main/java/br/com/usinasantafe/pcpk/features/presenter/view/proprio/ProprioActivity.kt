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
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DestinoProprioFragment.Companion.KEY_FLOW_DESTINO_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DestinoProprioFragment.Companion.KEY_POS_DESTINO_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DetalheMovEquipProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DetalheMovEquipProprioFragment.Companion.KEY_POS_DETALHE_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.MatricColabFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.MatricColabFragment.Companion.KEY_POS_MATRIC_COLAB
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.MatricColabFragment.Companion.KEY_TYPE_OCUPANTE_VEIC_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.MovEquipProprioListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.NomeColabFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.NomeColabFragment.Companion.KEY_MATRIC_COLAB
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.NotaFiscalProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.NotaFiscalProprioFragment.Companion.KEY_FLOW_NOTA_FISCAL
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.NotaFiscalProprioFragment.Companion.KEY_POS_NOTA_FISCAL
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.ObservProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.ObservProprioFragment.Companion.KEY_FLOW_OBSERV_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.ObservProprioFragment.Companion.KEY_POS_OBSERV_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.PassagColabListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.PassagColabListFragment.Companion.KEY_POS_PASSAG_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.PassagColabListFragment.Companion.KEY_TYPE_OCUPANTE_VEIC_PROPRIO_PASSAG_LIST
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.VeiculoProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.VeiculoProprioFragment.Companion.KEY_POS_EQUIP_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.VeiculoProprioFragment.Companion.KEY_TYPE_EQUIP_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.VeiculoSegProprioListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.VeiculoSegProprioListFragment.Companion.KEY_POS_EQUIP_SEG_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.VeiculoSegProprioListFragment.Companion.KEY_TYPE_VEIC_PROPRIO_SEG_LIST
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
        val bundle = intent.extras
        bundle?.putInt(KEY_FLOW_INITIAL, FlowInitial.RETURN.ordinal)
        startActivity(intent)
    }

    override fun goVeiculoProprio(typeAddEquip: TypeAddEquip, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_TYPE_EQUIP_PROPRIO, typeAddEquip.ordinal)
        args.putInt(KEY_POS_EQUIP_PROPRIO, pos)
        val fragment = VeiculoProprioFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goMatricColab(typeAddOcupante: TypeAddOcupante, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_TYPE_OCUPANTE_VEIC_PROPRIO, typeAddOcupante.ordinal)
        args.putInt(KEY_POS_MATRIC_COLAB, pos)
        val fragment = MatricColabFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goPassagList(typeAddOcupante: TypeAddOcupante, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_TYPE_OCUPANTE_VEIC_PROPRIO_PASSAG_LIST, typeAddOcupante.ordinal)
        args.putInt(KEY_POS_PASSAG_PROPRIO, pos)
        val fragment = PassagColabListFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goVeicSegList(typeAddEquip: TypeAddEquip, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_TYPE_VEIC_PROPRIO_SEG_LIST, typeAddEquip.ordinal)
        args.putInt(KEY_POS_EQUIP_SEG_PROPRIO, pos)
        val fragment = VeiculoSegProprioListFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goDestino(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_DESTINO_PROPRIO, flowApp.ordinal)
        args.putInt(KEY_POS_DESTINO_PROPRIO, pos)
        val fragment = DestinoProprioFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goNotaFiscal(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_NOTA_FISCAL, flowApp.ordinal)
        args.putInt(KEY_POS_NOTA_FISCAL, pos)
        val fragment = NotaFiscalProprioFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goObserv(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_OBSERV_PROPRIO, flowApp.ordinal)
        args.putInt(KEY_POS_OBSERV_PROPRIO, pos)
        val fragment = ObservProprioFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goDetalhe(pos: Int) {
        val args = Bundle()
        args.putInt(KEY_POS_DETALHE_PROPRIO, pos)
        val fragment = DetalheMovEquipProprioFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goNomeColab(matricColab: String, typeAddOcupante: TypeAddOcupante, pos: Int) {
        val args = Bundle()
        args.putString(KEY_MATRIC_COLAB, matricColab)
        args.putInt(KEY_TYPE_OCUPANTE_VEIC_PROPRIO, typeAddOcupante.ordinal)
        val fragment = NomeColabFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.proprio_manager_fragment, fragment)
    }
}