package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.extension.replaceFragment
import br.com.usinasantafe.pcpk.databinding.ActivityProprioBinding
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity
import br.com.usinasantafe.pcpk.features.presenter.view.splash.SplashActivity
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
        startActivity(intent)
    }

    override fun goMatricColab() {
        replaceFragment(MatricColabFragment())
    }

    override fun goListPassag() {
        replaceFragment(PassagColabListFragment())
    }

    override fun goNomeColab() {
        replaceFragment(NomeColabFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.proprio_manager_fragment, fragment)
    }
}