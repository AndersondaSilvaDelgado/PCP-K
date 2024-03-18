package br.com.usinasantafe.pcpk.features.presenter.view.residencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.extension.replaceFragment
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.ActivityResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity.Companion.KEY_FLOW_INITIAL
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity.Companion.FlowInitial
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.DetalheMovEquipResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MotoristaResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MovEquipResidenciaListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MovEquipResidenciaStartedListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.ObservResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.PlacaResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.VeiculoResidenciaFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResidenciaActivity : AppCompatActivity(), FragmentAttachListenerResidencia {

    private lateinit var binding: ActivityResidenciaBinding
    private var flowApp = FlowApp.ADD
    private var pos = 0;
    private var typeMov : TypeMov? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResidenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goMovResidenciaList()
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

    override fun goMovResidenciaList() {
        replaceFragment(MovEquipResidenciaListFragment())
    }

    override fun goMovResidenciaStartedList() {
        replaceFragment(MovEquipResidenciaStartedListFragment())
    }

    override fun goVeiculo(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(VeiculoResidenciaFragment())
    }

    override fun goPlaca(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(PlacaResidenciaFragment())
    }

    override fun goMotorista(flowApp: FlowApp, pos: Int) {
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(MotoristaResidenciaFragment())
    }

    override fun goObserv(typeMov: TypeMov?, flowApp: FlowApp, pos: Int) {
        this.typeMov = typeMov
        this.flowApp = flowApp
        this.pos = pos
        replaceFragment(ObservResidenciaFragment())
    }

    override fun goDetalhe(pos: Int) {
        this.pos = pos
        replaceFragment(DetalheMovEquipResidenciaFragment())
    }

    override fun getFlowApp(): FlowApp {
        return flowApp
    }

    override fun getPos(): Int {
        return pos
    }

    override fun getTypeMov(): TypeMov? {
        return typeMov
    }

    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.residencia_manager_fragment, fragment)
    }
}