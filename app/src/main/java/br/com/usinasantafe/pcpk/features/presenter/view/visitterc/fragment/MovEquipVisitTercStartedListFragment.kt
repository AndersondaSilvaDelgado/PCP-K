package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.databinding.FragmentMovEquipVisitTercStartedListBinding
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipVisitTercModel
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.adapter.MovEquipVisitTercStartedAdapter
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.MovEquipVisitTercStartedListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.MovEquipVisitTercEmptyListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovEquipVisitTercStartedListFragment : BaseFragment<FragmentMovEquipVisitTercStartedListBinding>(
    R.layout.fragment_mov_equip_visit_terc_started_list,
    FragmentMovEquipVisitTercStartedListBinding::bind,
) {

    private val viewModel: MovEquipVisitTercEmptyListViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null

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
                fragmentAttachListenerVisitTerc?.goMovVisitTercList()
            }
        }
    }

    private fun startEvents() {
        viewModel.recoverListMov()
    }

    private fun handleStateChange(state: MovEquipVisitTercStartedListFragmentState) {
        when(state){
            is MovEquipVisitTercStartedListFragmentState.ListMovEquip -> handleListMov(state.movEquipVisitTercList)
            is MovEquipVisitTercStartedListFragmentState.CheckCloseAllMov -> handleCloseAllMov(state.check)
        }
    }

    private fun handleCloseAllMov(check: Boolean){
        if (check) {
            fragmentAttachListenerVisitTerc?.goMovVisitTercList()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_failure_app,
            ), requireContext()
        )
    }

    private fun handleListMov(movEquipVisitTercList: List<MovEquipVisitTercModel>) {
        val listAdapter = MovEquipVisitTercStartedAdapter(movEquipVisitTercList).apply {
            onItemClick = { pos ->
                fragmentAttachListenerVisitTerc?.goDetalhe(pos)
            }
        }
        binding.listViewMov.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerVisitTerc) {
            fragmentAttachListenerVisitTerc = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerVisitTerc = null
    }

}