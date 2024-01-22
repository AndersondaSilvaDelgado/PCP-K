package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentMovEquipProprioListBinding
import br.com.usinasantafe.pcpk.features.presenter.model.HeaderModel
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.adapter.MovEquipProprioAdapter
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.MovEquipProprioListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.MovEquipProprioListViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notifyAll

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
        viewModel.recoverDataHeader()
        viewModel.recoverListMov()
    }

    private fun setListener() {
        with(binding) {
            buttonEntradaMovEquipProprio.setOnClickListener {
                viewModel.checkSetInitialMov(TypeMov.INPUT)
            }
            buttonSaidaMovEquipProprio.setOnClickListener {
                viewModel.checkSetInitialMov(TypeMov.OUTPUT)
            }
            buttonRetornarMovEquipProprio.setOnClickListener {
                fragmentAttachListenerProprio?.goInicial()
            }
        }
    }

    private fun handleStateChange(state: MovEquipProprioListFragmentState){
        when(state){
            is MovEquipProprioListFragmentState.RecoverHeader -> handleHeader(state.header)
            is MovEquipProprioListFragmentState.ListMovEquip -> handleListMov(state.movEquipProprioList)
            is MovEquipProprioListFragmentState.CheckInitialMovEquip -> handleStartMov(state.check)
        }
    }

    private fun handleHeader(header: HeaderModel){
        with(binding) {
            textViewVigia.text = header.nomeVigia
            textViewLocal.text = header.local
        }
    }

    private fun handleStartMov(check: Boolean) {
        if (check) {
            fragmentAttachListenerProprio?.goVeiculoProprio(TypeAddEquip.ADDVEICULO)
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_failure_app,
            ), requireContext()
        )
    }

    private fun handleListMov(movEquipProprioList: List<MovEquipProprioModel>){
        val listAdapter = MovEquipProprioAdapter(movEquipProprioList).apply {
            onItemClick = { pos ->
                fragmentAttachListenerProprio?.goDetalhe(pos)
            }
        }
        binding.listViewMovProprio.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerProprio) {
            fragmentAttachListenerProprio = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}