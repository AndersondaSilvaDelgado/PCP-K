package br.com.usinasantafe.pcpk.features.presenter.view.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.usinasantafe.cmm.common.adapter.CustomAdapter
import br.com.usinasantafe.cmm.common.base.BaseFragment
import br.com.usinasantafe.pcpk.BuildConfig
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.databinding.FragmentMenuInicialBinding
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.common.MenuInicialFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.common.MenuInicialViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuInicialFragment : BaseFragment<FragmentMenuInicialBinding>(
    R.layout.fragment_menu_inicial,
    FragmentMenuInicialBinding::bind,
) {

    private lateinit var viewModel: MenuInicialViewModel
    private var fragmentAttachListenerCommon: FragmentAttachListenerCommon? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        viewList()
        version()

    }

    private fun observeState(){
//        viewModel.uiLiveData.observe(viewLifecycleOwner) {
//            state -> handleStateChange(state)
//        }
    }

    private fun viewList() {

        val opcaoMenu = listOf(
            "BOLETIM",
            "CONFIGURAÇÃO",
            "SAIR",
        )

        val listAdapter = CustomAdapter(opcaoMenu).apply {
            onItemClick = { text, _ ->
                when(text){
                    "BOLETIM" -> eventBoletim()
                    "CONFIGURAÇÃO" -> eventConfig()
                    "SAIR" -> eventSair()
                }
            }
        }
        binding.listMenuInicial.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun version() {
        binding.textViewPrincipal.text = "PRINCIPAL - V " + BuildConfig.VERSION_NAME
    }

    private fun eventBoletim(){
//        viewModel.checkAccessBoletim()
    }

    private fun eventConfig(){
        fragmentAttachListenerCommon?.goSenhaFragment()
    }

    private fun eventSair(){
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun handleStateChange(state: MenuInicialFragmentState){
        when(state){
            is MenuInicialFragmentState.HasConfig -> {}
            is MenuInicialFragmentState.GetStatusSend -> {}
            is MenuInicialFragmentState.SetResultUpdate -> {}
            is MenuInicialFragmentState.FeedbackLoadingDataBase -> {}
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListenerCommon){
            fragmentAttachListenerCommon = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerCommon = null
    }

}