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
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DestinoVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DetalheMovEquipVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.MovEquipVisitTercStartedListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.MovEquipVisitTercListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.NomeVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PassagVisitTercListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.TipoVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisitTercActivity : AppCompatActivity(), FragmentAttachListenerVisitTerc {

    private lateinit var binding: ActivityVisitTercBinding
    private var cpf = ""
    private var typeAddOcupante = TypeAddOcupante.ADDMOTORISTA
    private var flowApp = FlowApp.ADD
    private var pos = 0;
    private var typeMov : TypeMov? = null

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
        intent.apply {
            putExtra(KEY_FLOW_INITIAL, FlowInitial.RETURN.ordinal)
        }
        startActivity(intent)
    }

    override fun goMovVisitTercList() {
        replaceFragment(MovEquipVisitTercListFragment())
    }

    override fun goMovVisitTercListStarted() {
        replaceFragment(MovEquipVisitTercStartedListFragment())
    }

    override fun goVeiculo(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(VeiculoVisitTercFragment())
    }

    override fun goPlaca(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(PlacaVisitTercFragment())
    }

    override fun goTipoVisitTerc() {
        replaceFragment(TipoVisitTercFragment())
    }

    override fun goCPFVisitTerc(typeAddOcupante: TypeAddOcupante, pos: Int) {
        this.typeAddOcupante = typeAddOcupante
        this.pos = pos
        replaceFragment(CPFVisitTercFragment())
    }

    override fun goNomeVisitTerc(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int) {
        this.cpf = cpf
        this.typeAddOcupante = typeAddOcupante
        this.pos = pos
        replaceFragment(NomeVisitTercFragment())
    }

    override fun goPassagList(typeAddOcupante: TypeAddOcupante, pos: Int) {
        this.typeAddOcupante = typeAddOcupante
        this.pos = pos
        replaceFragment(PassagVisitTercListFragment())
    }

    override fun goDestino(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(DestinoVisitTercFragment())
    }

    override fun goObserv(typeMov: TypeMov?, flowApp: FlowApp, pos: Int) {
        this.typeMov = typeMov
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(ObservVisitTercFragment())
    }

    override fun goDetalhe(pos: Int) {
        this.pos = pos
        replaceFragment(DetalheMovEquipVisitTercFragment())
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

    override fun getCPF(): String {
        return cpf
    }

    override fun getTypeMov(): TypeMov? {
        return typeMov
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.visit_terc_manager_fragment, fragment)
    }
}