package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.databinding.FragmentMovEquipResidenciaStartedListBinding
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipResidenciaModel
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.adapter.MovEquipResidenciaStartedAdapter
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.MovEquipResidenciaStartedListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.MovEquipResidenciaStartedListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovEquipResidenciaStartedListFragment : BaseFragment<FragmentMovEquipResidenciaStartedListBinding>(
    R.layout.fragment_mov_equip_residencia_started_list,
    FragmentMovEquipResidenciaStartedListBinding::bind,
) {

    private val viewModel: MovEquipResidenciaStartedListViewModel by viewModels()
    private var fragmentAttachListenerResidencia: FragmentAttachListenerResidencia? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        setListener()
        startEvents()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            state -> handleStateChange(state)
        }
    }

    private fun setListener() {
        with(binding) {
            buttonFecharMov.setOnClickListener {
                viewModel.closeAllMov()
            }
            buttonRetMov.setOnClickListener {
                fragmentAttachListenerResidencia?.goMovResidenciaList()
            }
        }
    }

    private fun startEvents() {
        viewModel.recoverListMov()
    }

    private fun handleStateChange(state: MovEquipResidenciaStartedListFragmentState) {
        when(state){
            is MovEquipResidenciaStartedListFragmentState.ListMovEquip -> handleListMov(state.movEquipResidenciaList)
            is MovEquipResidenciaStartedListFragmentState.CheckCloseAllMov -> handleCloseAllMov(state.check)
        }
    }

    private fun handleCloseAllMov(check: Boolean){
        if (check) {
            fragmentAttachListenerResidencia?.goMovResidenciaList()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_failure_app,
            ), requireContext()
        )
    }

    private fun handleListMov(movEquipResidenciaList: List<MovEquipResidenciaModel>) {
        val listAdapter = MovEquipResidenciaStartedAdapter(movEquipResidenciaList).apply {
            onItemClick = { pos ->
                fragmentAttachListenerResidencia?.goDetalhe(pos)
            }
        }
        binding.listViewMov.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerResidencia) {
            fragmentAttachListenerResidencia = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerResidencia = null
    }

}