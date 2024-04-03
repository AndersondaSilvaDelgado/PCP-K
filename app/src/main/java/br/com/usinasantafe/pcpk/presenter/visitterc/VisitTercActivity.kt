package br.com.usinasantafe.pcpk.presenter.visitterc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.utils.replaceFragment
import br.com.usinasantafe.pcpk.databinding.ActivityVisitTercBinding
import br.com.usinasantafe.pcpk.presenter.initial.InitialActivity
import br.com.usinasantafe.pcpk.presenter.initial.InitialActivity.Companion.KEY_FLOW_INITIAL
import br.com.usinasantafe.pcpk.presenter.initial.InitialActivity.Companion.FlowInitial
import br.com.usinasantafe.pcpk.presenter.visitterc.cpf.CPFVisitTercFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.destino.DestinoVisitTercFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.detalhemov.DetalheMovEquipVisitTercFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.movequip.MovEquipVisitTercListFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.movequipedit.MovEquipVisitTercStartedListFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.nome.NomeVisitTercFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.observ.ObservVisitTercFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.passag.PassagVisitTercListFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.placa.PlacaVisitTercFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.tipo.TipoVisitTercFragment
import br.com.usinasantafe.pcpk.presenter.visitterc.veiculo.VeiculoVisitTercFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisitTercActivity : AppCompatActivity(),
    FragmentAttachListenerVisitTerc {

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

    override fun goVeiculo() {
        replaceFragment(VeiculoVisitTercFragment())
    }

    override fun goPlaca() {
        replaceFragment(PlacaVisitTercFragment())
    }

    override fun goTipoVisitTerc() {
        replaceFragment(TipoVisitTercFragment())
    }

    override fun goCPFVisitTerc() {
        replaceFragment(CPFVisitTercFragment())
    }

    override fun goNomeVisitTerc() {
        replaceFragment(NomeVisitTercFragment())
    }

    override fun goPassagList() {
        replaceFragment(PassagVisitTercListFragment())
    }

    override fun goDestino() {
        replaceFragment(DestinoVisitTercFragment())
    }

    override fun goObserv() {
        replaceFragment(ObservVisitTercFragment())
    }

    override fun goDetalhe() {
        replaceFragment(DetalheMovEquipVisitTercFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.visit_terc_manager_fragment, fragment)
    }
}