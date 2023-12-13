package br.com.usinasantafe.pcpk.features.presenter.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import br.com.usinasantafe.cmm.common.utils.PointerStart
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.databinding.ActivitySplashBinding
import br.com.usinasantafe.pcpk.features.presenter.view.common.CommonActivity
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.splash.SplashState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler.post(runnable)
        observeState()
        startEvents()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(this) {
                state -> handleStateChange(state)
        }
    }

    private fun startEvents() {
        viewModel.startSent()
    }

    private val runnable: Runnable
        get() = Runnable {
            binding.splashImg.animate()
                .alpha(0f)
                .setDuration(2000)
                .withEndAction{
                    binding.splashImg.animate()
                        .alpha(1f).duration = 2000
                }
            handler.postDelayed(runnable, 4000)
        }

    private fun handleStateChange(state: SplashState){
        when(state){
            is SplashState.CheckStartAPP -> handleCheckStartAPP(state.pointerStart)
        }
    }

    private fun handleCheckStartAPP(checkStartAPP: PointerStart){
        when(checkStartAPP){
            PointerStart.MENUINICIAL -> goMenuInicial()
            else -> {}
        }
    }

    private fun goMenuInicial(){
        val intent = Intent(this, CommonActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


}