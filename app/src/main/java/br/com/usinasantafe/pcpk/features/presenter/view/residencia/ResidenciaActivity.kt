package br.com.usinasantafe.pcpk.features.presenter.view.residencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.extension.replaceFragment
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.ActivityResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity.Companion.KEY_FLOW_INITIAL
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity.Companion.FlowInitial
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MotoristaResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MovEquipResidenciaListFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.ObservResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.ObservResidenciaFragment.Companion.KEY_POS_MOV_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.ObservResidenciaFragment.Companion.KEY_TYPE_MOV_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.PlacaResidenciaFragment
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.VeiculoResidenciaFragment

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
        var bundle = intent.extras
        bundle?.putInt(KEY_FLOW_INITIAL, FlowInitial.RETURN.ordinal)
        startActivity(intent)
    }

    override fun goMovResidenciaList() {
        replaceFragment(MovEquipResidenciaListFragment())
    }

    override fun goVeiculo() {
        replaceFragment(VeiculoResidenciaFragment())
    }

    override fun goPlaca() {
        replaceFragment(PlacaResidenciaFragment())
    }

    override fun goMotorista() {
        replaceFragment(MotoristaResidenciaFragment())
    }

    override fun goObserv(typeMov: TypeMov, pos: Int?) {
        val args = Bundle()
        args.putInt(KEY_TYPE_MOV_RESIDENCIA, typeMov.ordinal)
        if(pos != null){
            args.putInt(KEY_POS_MOV_RESIDENCIA, pos)
        }
        val fragment = ObservResidenciaFragment()
        fragment.arguments = args
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.residencia_manager_fragment, fragment)
    }
}