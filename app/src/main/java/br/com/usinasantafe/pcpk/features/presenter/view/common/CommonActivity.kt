package br.com.usinasantafe.pcpk.features.presenter.view.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.extension.replaceFragment
import br.com.usinasantafe.pcpk.databinding.ActivityCommonBinding
import br.com.usinasantafe.pcpk.features.presenter.view.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommonActivity : AppCompatActivity(), FragmentAttachListenerCommon {

    private lateinit var binding: ActivityCommonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goMenuInicial()
    }

    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

    override fun goSenhaFragment() {
        replaceFragment(SenhaFragment())
    }

    override fun goConfigFragment() {
        replaceFragment(ConfigFragment())
    }

    override fun goMenuInicial() {
        replaceFragment(MenuInicialFragment())
    }

    override fun goMatricVigia() {
        replaceFragment(MatricVigiaFragment())
    }

    override fun goSplash() {
        val intent = Intent(this, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.config_manager_fragment, fragment)
    }
}