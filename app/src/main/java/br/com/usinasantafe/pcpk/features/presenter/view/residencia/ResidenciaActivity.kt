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
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.DetalheMovEquipResidenciaFragment.Companion.KEY_POS_DETALHE_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MotoristaResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MovEquipResidenciaListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MovEquipResidenciaStartedListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.ObservResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.ObservResidenciaFragment.Companion.KEY_FLOW_OBSERV_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.ObservResidenciaFragment.Companion.KEY_POS_OBSERV_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.ObservResidenciaFragment.Companion.KEY_TYPE_OBSERV_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.PlacaResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.PlacaResidenciaFragment.Companion.KEY_FLOW_PLACA_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.PlacaResidenciaFragment.Companion.KEY_POS_PLACA_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.VeiculoResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.VeiculoResidenciaFragment.Companion.KEY_FLOW_VEICULO_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.VeiculoResidenciaFragment.Companion.KEY_POS_VEICULO_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment

class ResidenciaActivity : AppCompatActivity(), FragmentAttachListenerResidencia {


    private lateinit var binding: ActivityResidenciaBinding

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
        val bundle = intent.extras
        bundle?.putInt(KEY_FLOW_INITIAL, FlowInitial.RETURN.ordinal)
        startActivity(intent)
    }

    override fun goMovResidenciaList() {
        replaceFragment(MovEquipResidenciaListFragment())
    }

    override fun goMovResidenciaStartedList() {
        replaceFragment(MovEquipResidenciaStartedListFragment())
    }

    override fun goVeiculo(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_VEICULO_RESIDENCIA, flowApp.ordinal)
        args.putInt(KEY_POS_VEICULO_RESIDENCIA, pos)
        val fragment = VeiculoResidenciaFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goPlaca(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_PLACA_RESIDENCIA, flowApp.ordinal)
        args.putInt(KEY_POS_PLACA_RESIDENCIA, pos)
        val fragment = PlacaResidenciaFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goMotorista(flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_FLOW_PLACA_RESIDENCIA, flowApp.ordinal)
        args.putInt(KEY_POS_PLACA_RESIDENCIA, pos)
        val fragment = MotoristaResidenciaFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goObserv(typeMov: TypeMov?, flowApp: FlowApp, pos: Int) {
        val args = Bundle()
        args.putInt(KEY_TYPE_OBSERV_RESIDENCIA, typeMov?.ordinal ?: -1)
        args.putInt(KEY_FLOW_OBSERV_RESIDENCIA, flowApp.ordinal)
        args.putInt(KEY_POS_OBSERV_RESIDENCIA, pos)
        val fragment = ObservResidenciaFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    override fun goDetalhe(pos: Int) {
        val args = Bundle()
        args.putInt(KEY_POS_DETALHE_RESIDENCIA, pos)
        val fragment = DetalheMovEquipResidenciaFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.residencia_manager_fragment, fragment)
    }
}