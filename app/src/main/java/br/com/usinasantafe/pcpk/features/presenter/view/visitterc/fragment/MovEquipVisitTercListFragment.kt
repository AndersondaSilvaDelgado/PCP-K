package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentMovEquipVisitTercListBinding
import br.com.usinasantafe.pcpk.features.presenter.model.HeaderModel
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipVisitTercModel
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.adapter.MovEquipVisitTercAdapter
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.FLOW_APP_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.POS_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.REQUEST_KEY_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.TYPE_MOV_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment.Companion.FLOW_APP_VEIC_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment.Companion.POS_VEIC_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment.Companion.REQUEST_KEY_VEIC_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.MovEquipVisitTercListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.MovEquipVisitTercListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovEquipVisitTercListFragment : BaseFragment<FragmentMovEquipVisitTercListBinding>(
    R.layout.fragment_mov_equip_visit_terc_list,
    FragmentMovEquipVisitTercListBinding::bind,
) {

    private val viewModel: MovEquipVisitTercListViewModel by viewModels()
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

    private fun startEvents() {
        viewModel.recoverDataHeader()
        viewModel.recoverListMov()
    }

    private fun setListener() {
        with(binding) {
            buttonEntradaMov.setOnClickListener {
                viewModel.checkSetInitialMov()
            }
            buttonEditarMov.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goMovVisitTercListStarted()
            }
            buttonRetornarMov.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goInicial()
            }
        }
    }

    private fun handleStateChange(state: MovEquipVisitTercListFragmentState) {
        when(state){
            is MovEquipVisitTercListFragmentState.RecoverHeader -> handleHeader(state.header)
            is MovEquipVisitTercListFragmentState.ListMovEquip -> handleListMov(state.movEquipVisitTercList)
            is MovEquipVisitTercListFragmentState.CheckInitialMovEquip -> handleStartMov(state.check)
        }
    }

    private fun handleHeader(header: HeaderModel){
        with(binding) {
            textViewVigia.text = header.nomeVigia
            textViewLocal.text = header.local
        }
    }

    private fun handleListMov(movEquipVisitTercList: List<MovEquipVisitTercModel>){
        val listAdapter = MovEquipVisitTercAdapter(movEquipVisitTercList).apply {
            onItemClick = { pos ->
                setBundleObservVisitTerc(TypeMov.OUTPUT, FlowApp.ADD,  pos)
                fragmentAttachListenerVisitTerc?.goObserv()
            }
        }
        binding.listViewMovVisitTerc.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun handleStartMov(check: Boolean) {
        if (check) {
            setBundleVeicVisitTerc(FlowApp.ADD, 0)
            fragmentAttachListenerVisitTerc?.goVeiculo()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_failure_app,
            ), requireContext()
        )
    }

    private fun setBundleVeicVisitTerc(flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_VEIC_VISIT_TERC, flowApp.ordinal)
        bundle.putInt(POS_VEIC_VISIT_TERC, pos)
        setFragmentResult(REQUEST_KEY_VEIC_VISIT_TERC, bundle)
    }

    private fun setBundleObservVisitTerc(typeMov: TypeMov, flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_OBSERV_VISIT_TERC, flowApp.ordinal)
        bundle.putInt(TYPE_MOV_OBSERV_VISIT_TERC, typeMov.ordinal)
        bundle.putInt(POS_OBSERV_VISIT_TERC, pos)
        setFragmentResult(REQUEST_KEY_OBSERV_VISIT_TERC, bundle)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerVisitTerc) {
            fragmentAttachListenerVisitTerc = context
        }
        onBackPressed {}
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerVisitTerc = null
    }

}