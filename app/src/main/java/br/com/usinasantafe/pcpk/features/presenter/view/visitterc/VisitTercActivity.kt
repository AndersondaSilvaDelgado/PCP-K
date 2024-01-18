package br.com.usinasantafe.pcpk.features.presenter.view.visitterc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.extension.replaceFragment
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.ActivityVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity.Companion.KEY_FLOW_INITIAL
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity.Companion.FlowInitial
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.CPFVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.CPFVisitTercFragment.Companion.KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DestinoVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DestinoVisitTercFragment.Companion.KEY_FLOW_DESTINO_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DestinoVisitTercFragment.Companion.KEY_POS_DESTINO_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DetalheMovEquipVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DetalheMovEquipVisitTercFragment.Companion.KEY_POS_DETALHE_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.MovEquipVisitTercStartedListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.MovEquipVisitTercListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.NomeVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.NomeVisitTercFragment.Companion.KEY_CPF_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.KEY_FLOW_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.KEY_POS_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.KEY_TYPE_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PassagVisitTercListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PassagVisitTercListFragment.Companion.KEY_POS_PASSAG_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PassagVisitTercListFragment.Companion.KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC_PASSAG_LIST
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment.Companion.KEY_FLOW_PLACA_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment.Companion.KEY_POS_PLACA_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.TipoVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment.Companion.KEY_FLOW_VEICULO_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment.Companion.KEY_POS_VEICULO_VISIT_TERC

class VisitTercActivity : AppCompatActivity(), FragmentAttachListenerVisitTerc {

    private lateinit var binding: ActivityVisitTercBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisitTercBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goMovVisitTercList()
    }

    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

    override fun goInicial() {
        val intent = Intent(this, InitialActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        val bundle = intent.extras
        bundle?.putInt(KEY_FLOW_INITIAL, FlowInitial.RETURN.ordinal)
        startActivity(intent)
    }

    override fun goMovVisitTercList() {
        replaceFragment(MovEquipVisitTercListFragment())
    }

    override fun goMovVisitTercListStarted() {
        replaceFragment(MovEquipVisitTercStartedListFragment())
    }

    override fun goVeiculo(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_VEICULO_VISIT_TERC, flowApp.ordinal)
        args.putInt(KEY_POS_VEICULO_VISIT_TERC, pos)
        val fragment = VeiculoVisitTercFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goPlaca(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_PLACA_VISIT_TERC, flowApp.ordinal)
        args.putInt(KEY_POS_PLACA_VISIT_TERC, pos)
        val fragment = PlacaVisitTercFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goTipoVisitTerc() {
        replaceFragment(TipoVisitTercFragment())
    }

    override fun goCPFVisitTerc(typeAddOcupante: TypeAddOcupante, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC, typeAddOcupante.ordinal)
        val fragment = CPFVisitTercFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goNomeVisitTerc(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int) {
        val args = Bundle()
        args.putString(KEY_CPF_VISIT_TERC, cpf)
        args.putInt(KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC, typeAddOcupante.ordinal)
        val fragment = NomeVisitTercFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goPassagList(typeAddOcupante: TypeAddOcupante, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC_PASSAG_LIST, typeAddOcupante.ordinal)
        args.putInt(KEY_POS_PASSAG_VISIT_TERC, pos)
        val fragment = PassagVisitTercListFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goDestino(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_DESTINO_VISIT_TERC, flowApp.ordinal)
        args.putInt(KEY_POS_DESTINO_VISIT_TERC, pos)
        val fragment = DestinoVisitTercFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goObserv(typeMov: TypeMov?, flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_TYPE_OBSERV_VISIT_TERC, typeMov?.ordinal ?: -1)
        args.putInt(KEY_FLOW_OBSERV_VISIT_TERC, flowApp.ordinal)
        args.putInt(KEY_POS_OBSERV_VISIT_TERC, pos)
        val fragment = ObservVisitTercFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goDetalhe(pos: Int) {
        val args = Bundle()
        args.putInt(KEY_POS_DETALHE_VISIT_TERC, pos)
        val fragment = DetalheMovEquipVisitTercFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.visit_terc_manager_fragment, fragment)
    }
}