package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialogCheck
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentDetalheMovEquipVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipVisitTercModel
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DetalheMovEquipProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.CPFVisitTercFragment.Companion.POS_CPF_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.CPFVisitTercFragment.Companion.REQUEST_KEY_CPF_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.CPFVisitTercFragment.Companion.TYPE_ADD_OCUPANTE_CPF_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DestinoVisitTercFragment.Companion.FLOW_APP_DESTINO_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DestinoVisitTercFragment.Companion.POS_DESTINO_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DestinoVisitTercFragment.Companion.REQUEST_KEY_DESTINO_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.FLOW_APP_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.POS_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.REQUEST_KEY_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment.Companion.TYPE_MOV_OBSERV_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PassagVisitTercListFragment.Companion.POS_PASSAG_VISIT_TERC_LIST
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PassagVisitTercListFragment.Companion.REQUEST_KEY_PASSAG_VISIT_TERC_LIST
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PassagVisitTercListFragment.Companion.TYPE_ADD_OCUPANTE_PASSAG_VISIT_TERC_LIST
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment.Companion.FLOW_APP_PLACA_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment.Companion.POS_PLACA_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment.Companion.REQUEST_KEY_PLACA_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment.Companion.FLOW_APP_VEIC_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment.Companion.POS_VEIC_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment.Companion.REQUEST_KEY_VEIC_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.DetalheMovEquipVisitTercFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.DetalheMovEquipVisitTercViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalheMovEquipVisitTercFragment : BaseFragment<FragmentDetalheMovEquipVisitTercBinding>(
    R.layout.fragment_detalhe_mov_equip_visit_terc,
    FragmentDetalheMovEquipVisitTercBinding::bind
) {

    private val viewModel: DetalheMovEquipVisitTercViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null
    private var pos: Int = 0

    companion object {
        const val REQUEST_KEY_DETALHE_VISIT_TERC = "requestKeyDetalheVisitTerc"
        const val POS_DETALHE_VISIT_TERC = "posDetalheVisitTerc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REQUEST_KEY_DETALHE_VISIT_TERC) { _, bundle ->
            this.pos = bundle.getInt(POS_DETALHE_VISIT_TERC)
        }

    }

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
        viewModel.recoverDataDetalhe(pos)
    }

    private fun setListener() {
        with(binding) {
            buttonFecharMov.setOnClickListener {
                showMessage()
            }
            buttonRetDetalheMov.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goMovVisitTercListStarted()
            }
        }

    }

    private fun showMessage(){
        showGenericAlertDialogCheck("DESEJA REALMENTE FECHAR O MOVIMENTO?", requireContext()) {
            viewModel.closeMov(pos)
        }
    }

    private fun handleStateChange(state: DetalheMovEquipVisitTercFragmentState){
        when(state){
            is DetalheMovEquipVisitTercFragmentState.CheckMov -> handleCloseMov(state.check)
            is DetalheMovEquipVisitTercFragmentState.RecoverDetalhe -> handleDetalhe(state.detalhe)
        }
    }

    private fun handleCloseMov(check: Boolean) {
        if (check) {
            fragmentAttachListenerVisitTerc?.goMovVisitTercListStarted()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_failure_app,
            ), requireContext()
        )
    }

    private fun handleDetalhe(detalhe: DetalheMovEquipVisitTercModel) {

        val detalhes = listOf(
            detalhe.dthr,
            detalhe.tipoMov,
            detalhe.veiculo,
            detalhe.placa,
            detalhe.tipoVisitTerc,
            detalhe.motorista,
            detalhe.passageiro,
            detalhe.destino,
            detalhe.observ
        )

        val listAdapter = CustomAdapter(detalhes).apply {
            onItemClick = { _, position ->
                when(position){
                    2 -> {
                        setBundleVeicVisitTerc(FlowApp.CHANGE, pos)
                        fragmentAttachListenerVisitTerc?.goVeiculo()
                    }
                    3 -> {
                        setBundlePlacaVisitTerc(FlowApp.CHANGE, pos)
                        fragmentAttachListenerVisitTerc?.goPlaca()
                    }
                    5 -> {
                        setBundleCPFVisitTerc(TypeAddOcupante.CHANGEMOTORISTA, pos)
                        fragmentAttachListenerVisitTerc?.goCPFVisitTerc()
                    }
                    6 -> {
                        setBundlePassagVisitTerc(TypeAddOcupante.CHANGEPASSAGEIRO, pos)
                        fragmentAttachListenerVisitTerc?.goPassagList()
                    }
                    7 -> {
                        setBundleDestinoVisitTerc(FlowApp.CHANGE, pos)
                        fragmentAttachListenerVisitTerc?.goDestino()
                    }
                    8 -> {
                        setBundleObservVisitTerc(TypeMov.EMPTY, FlowApp.CHANGE, pos)
                        fragmentAttachListenerVisitTerc?.goObserv()
                    }
                }
            }
        }
        binding.listViewDetalheMov.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun setBundleVeicVisitTerc(flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_VEIC_VISIT_TERC, flowApp.ordinal)
        bundle.putInt(POS_VEIC_VISIT_TERC, pos)
        setFragmentResult(REQUEST_KEY_VEIC_VISIT_TERC, bundle)
    }

    private fun setBundlePlacaVisitTerc(flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_PLACA_VISIT_TERC, flowApp.ordinal)
        bundle.putInt(POS_PLACA_VISIT_TERC, pos)
        setFragmentResult(REQUEST_KEY_PLACA_VISIT_TERC, bundle)
    }

    private fun setBundleCPFVisitTerc(typeAddOcupante: TypeAddOcupante, pos: Int){
        val bundle = Bundle()
        bundle.putInt(TYPE_ADD_OCUPANTE_CPF_VISIT_TERC, typeAddOcupante.ordinal)
        bundle.putInt(POS_CPF_VISIT_TERC, pos)
        setFragmentResult(REQUEST_KEY_CPF_VISIT_TERC, bundle)
    }

    private fun setBundlePassagVisitTerc(typeAddOcupante: TypeAddOcupante, pos: Int){
        val bundle = Bundle()
        bundle.putInt(TYPE_ADD_OCUPANTE_PASSAG_VISIT_TERC_LIST, typeAddOcupante.ordinal)
        bundle.putInt(POS_PASSAG_VISIT_TERC_LIST, pos)
        setFragmentResult(REQUEST_KEY_PASSAG_VISIT_TERC_LIST, bundle)
    }

    private fun setBundleDestinoVisitTerc(flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_DESTINO_VISIT_TERC, flowApp.ordinal)
        bundle.putInt(POS_DESTINO_VISIT_TERC, pos)
        setFragmentResult(REQUEST_KEY_DESTINO_VISIT_TERC, bundle)
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