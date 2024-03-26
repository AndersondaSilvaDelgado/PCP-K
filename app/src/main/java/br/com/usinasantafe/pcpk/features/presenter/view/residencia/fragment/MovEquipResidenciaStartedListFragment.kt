package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialogCheck
import br.com.usinasantafe.pcpk.databinding.FragmentMovEquipResidenciaStartedListBinding
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipResidenciaModel
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.adapter.MovEquipResidenciaStartedAdapter
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.DetalheMovEquipResidenciaFragment.Companion.POS_DETALHE_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.DetalheMovEquipResidenciaFragment.Companion.REQUEST_KEY_DETALHE_RESIDENCIA
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
                showMessage()
            }
            buttonRetMov.setOnClickListener {
                fragmentAttachListenerResidencia?.goMovResidenciaList()
            }
        }
    }

    private fun startEvents() {
        viewModel.recoverListMov()
    }

    private fun showMessage(){
        showGenericAlertDialogCheck("DESEJA REALMENTE FECHAR TODOS OS MOVIMENTOS?", requireContext()) {
            viewModel.closeAllMov()
        }
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
                setBundleDetalheResidencia(pos)
                fragmentAttachListenerResidencia?.goDetalhe()
            }
        }
        binding.listViewMov.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun setBundleDetalheResidencia(pos: Int){
        val bundle = Bundle()
        bundle.putInt(POS_DETALHE_RESIDENCIA, pos)
        setFragmentResult(REQUEST_KEY_DETALHE_RESIDENCIA, bundle)
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