package br.com.usinasantafe.pcpk.presenter.residencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.utils.replaceFragment
import br.com.usinasantafe.pcpk.databinding.ActivityResidenciaBinding
import br.com.usinasantafe.pcpk.presenter.initial.InitialActivity
import br.com.usinasantafe.pcpk.presenter.initial.InitialActivity.Companion.KEY_FLOW_INITIAL
import br.com.usinasantafe.pcpk.presenter.initial.InitialActivity.Companion.FlowInitial
import br.com.usinasantafe.pcpk.presenter.residencia.detalhemov.DetalheMovEquipResidenciaFragment
import br.com.usinasantafe.pcpk.presenter.residencia.motorista.MotoristaResidenciaFragment
import br.com.usinasantafe.pcpk.presenter.residencia.movequip.MovEquipResidenciaListFragment
import br.com.usinasantafe.pcpk.presenter.residencia.movequipedit.MovEquipResidenciaStartedListFragment
import br.com.usinasantafe.pcpk.presenter.residencia.observ.ObservResidenciaFragment
import br.com.usinasantafe.pcpk.presenter.residencia.placa.PlacaResidenciaFragment
import br.com.usinasantafe.pcpk.presenter.residencia.veiculo.VeiculoResidenciaFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    override fun goVeiculo() {
        replaceFragment(VeiculoResidenciaFragment())
    }

    override fun goPlaca() {
        replaceFragment(PlacaResidenciaFragment())
    }

    override fun goMotorista() {
        replaceFragment(MotoristaResidenciaFragment())
    }

    override fun goObserv() {
        replaceFragment(ObservResidenciaFragment())
    }

    override fun goDetalhe() {
        replaceFragment(DetalheMovEquipResidenciaFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.residencia_manager_fragment, fragment)
    }
}