package br.com.usinasantafe.pcpk.features.presenter.view.initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.extension.replaceFragment
import br.com.usinasantafe.pcpk.databinding.ActivityInitialBinding
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.VisitTercActivity
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.ProprioActivity
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.ResidenciaActivity
import br.com.usinasantafe.pcpk.features.presenter.view.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialActivity : AppCompatActivity(), FragmentAttachListenerInitial {

    private lateinit var binding: ActivityInitialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goMenuInicial()
    }

    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

    override fun goSenhaFragment() {
        replaceFragment(SenhaFragment())
    }

    override fun goMovVisitTerc() {
        val intent = Intent(this, VisitTercActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun goMovProprio() {
        val intent = Intent(this, ProprioActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun goMovResidencia() {
        val intent = Intent(this, ResidenciaActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun goConfigFragment() {
        replaceFragment(ConfigFragment())
    }

    override fun goLocalInicial() {
        replaceFragment(LocalListFragment())
    }

    override fun goMenuInicial() {
        replaceFragment(MenuInicialFragment())
    }

    override fun goNomeVigia() {
        replaceFragment(NomeVigiaFragment())
    }

    override fun goMatricVigia() {
        replaceFragment(MatricVigiaFragment())
    }

    override fun goMenuApont() {
        replaceFragment(MenuApontListFragment())
    }

    override fun goSplash() {
        val intent = Intent(this, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.initial_manager_fragment, fragment)
    }
}