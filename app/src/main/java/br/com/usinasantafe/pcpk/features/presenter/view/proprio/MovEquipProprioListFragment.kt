package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentMovEquipProprioListBinding
import br.com.usinasantafe.pcpk.features.presenter.model.ConfigModelOutput
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.MovEquipProprioListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.MovEquipProprioListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovEquipProprioListFragment : BaseFragment<FragmentMovEquipProprioListBinding>(
    R.layout.fragment_mov_equip_proprio_list,
    FragmentMovEquipProprioListBinding::bind,
) {

    private val viewModel: MovEquipProprioListViewModel by viewModels()
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        setListener()
        startEvents()

    }

    private fun observeState(){
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            state -> handleStateChange(state)
        }
    }

    private fun startEvents() {
        viewModel.recoverDataConfig()
        viewModel.recoverListMov()
    }

    private fun setListener() {
        with(binding) {
            buttonEntradaMovEquipProprio.setOnClickListener {
                viewModel.setInitialMov(TypeMov.INPUT)
            }
            buttonSaidaMovEquipProprio.setOnClickListener {
                viewModel.setInitialMov(TypeMov.OUTPUT)
            }
            buttonMovEquipProprioFinalizado.setOnClickListener {
                TODO()
            }
            buttonRetornarMovEquipProprio.setOnClickListener {
                TODO()
            }
        }
    }

    private fun handleStateChange(state: MovEquipProprioListFragmentState){
        when(state){
            is MovEquipProprioListFragmentState.RecoverConfig -> handleConfig(state.config)
            is MovEquipProprioListFragmentState.ListMovEquip -> TODO()
            is MovEquipProprioListFragmentState.CheckInitialMovEquip -> handleStartMov(state.check)
        }
    }

    private fun handleStartMov(check: Boolean) {
        if (check) {
            fragmentAttachListenerProprio?.goMatricColab()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_dado_invalido_com_atual,
                "MATRICULA DO VIGIA"
            ), requireContext()
        )
    }

    private fun handleConfig(config: ConfigModelOutput){
        with(binding) {
            textViewVigia.text = config.nomeVigia
            textViewLocal.text = config.local
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerProprio) {
            fragmentAttachListenerProprio = context
        }
        onBackPressed {
            fragmentAttachListenerProprio?.goInicial()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}