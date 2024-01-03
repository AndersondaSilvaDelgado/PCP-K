package br.com.usinasantafe.pcpk.features.presenter.view.initial.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.BuildConfig
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.databinding.FragmentMenuInicialBinding
import br.com.usinasantafe.pcpk.features.presenter.view.initial.FragmentAttachListenerInitial
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial.MenuInicialFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial.MenuInicialViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuInicialFragment : BaseFragment<FragmentMenuInicialBinding>(
    R.layout.fragment_menu_inicial,
    FragmentMenuInicialBinding::bind,
) {

    private val viewModel: MenuInicialViewModel by viewModels()
    private var fragmentAttachListenerInitial: FragmentAttachListenerInitial? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        viewList()
        version()

    }

    private fun observeState(){
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            state -> handleStateChange(state)
        }
    }

    private fun viewList() {

        val opcaoMenu = listOf(
            "APONTAMENTO",
            "CONFIGURAÇÃO",
            "SAIR",
        )

        val listAdapter = CustomAdapter(opcaoMenu).apply {
            onItemClick = { text, _ ->
                when(text){
                    "APONTAMENTO" -> eventApont()
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

    private fun eventApont(){
        viewModel.checkAccessApont()
    }

    private fun eventConfig(){
        fragmentAttachListenerInitial?.goSenhaFragment()
    }

    private fun eventSair(){
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun handleStateChange(state: MenuInicialFragmentState){
        when(state){
            is MenuInicialFragmentState.HasAcessApont -> handleAcessApont(state.hasAcessApont)
        }
    }

    private fun handleAcessApont(hasAcessApont: Boolean){
        if(!hasAcessApont){
            showGenericAlertDialog(getString(R.string.texto_falha_acesso_apont), requireContext())
            return
        }
        fragmentAttachListenerInitial?.goMatricVigia()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListenerInitial){
            fragmentAttachListenerInitial = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerInitial = null
    }

}