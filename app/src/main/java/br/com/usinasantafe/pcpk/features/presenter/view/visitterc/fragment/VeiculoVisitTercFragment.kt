package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.databinding.FragmentVeiculoVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DestinoProprioFragment
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DetalheMovEquipVisitTercFragment.Companion.POS_DETALHE_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.DetalheMovEquipVisitTercFragment.Companion.REQUEST_KEY_DETALHE_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment.Companion.FLOW_APP_PLACA_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment.Companion.POS_PLACA_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.PlacaVisitTercFragment.Companion.REQUEST_KEY_PLACA_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.VeiculoVisitTercFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.VeiculoVisitTercViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VeiculoVisitTercFragment : BaseFragment<FragmentVeiculoVisitTercBinding>(
    R.layout.fragment_veiculo_visit_terc,
    FragmentVeiculoVisitTercBinding::bind
) {

    private val viewModel: VeiculoVisitTercViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null
    private lateinit var flowApp: FlowApp
    private var pos: Int = 0

    companion object {
        const val REQUEST_KEY_VEIC_VISIT_TERC = "requestKeyVeicVisitTerc"
        const val FLOW_APP_VEIC_VISIT_TERC = "flowAppVeicVisitTerc"
        const val POS_VEIC_VISIT_TERC = "posVeicVisitTerc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REQUEST_KEY_VEIC_VISIT_TERC) { _, bundle ->
            this.flowApp = FlowApp.values()[bundle.getInt(FLOW_APP_VEIC_VISIT_TERC)]
            this.pos = bundle.getInt(POS_VEIC_VISIT_TERC)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        setListener()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(viewLifecycleOwner) { state ->
            handleStateChange(state)
        }
    }

    private fun setListener() {
        with(binding) {
            buttonOkVeicVisitTerc.setOnClickListener {
                if (editTextVeicVisitTerc.text.isEmpty()) {
                    showGenericAlertDialog(
                        getString(
                            R.string.texto_campo_vazio,
                            "VEÍCULO"
                        ), requireContext()
                    )
                    return@setOnClickListener
                }
                viewModel.setVeiculo(editTextVeicVisitTerc.text.toString(), flowApp, pos)
            }
            buttonCancVeicVisitTerc.setOnClickListener {
                when(flowApp) {
                    FlowApp.ADD -> fragmentAttachListenerVisitTerc?.goMovVisitTercList()
                    FlowApp.CHANGE -> {
                        setBundleDetalheVisitTerc(pos)
                        fragmentAttachListenerVisitTerc?.goDetalhe()
                    }
                }
            }
        }
    }

    private fun handleStateChange(state: VeiculoVisitTercFragmentState) {
        when (state) {
            is VeiculoVisitTercFragmentState.CheckSetVeiculo -> handleCheckSetVeiculo(state.check)
        }
    }

    private fun handleCheckSetVeiculo(check: Boolean) {
        if (check) {
            when(flowApp) {
                FlowApp.ADD -> {
                    setBundlePlacaVisitTerc(flowApp, 0)
                    fragmentAttachListenerVisitTerc?.goPlaca()
                }
                FlowApp.CHANGE -> {
                    setBundleDetalheVisitTerc(pos)
                    fragmentAttachListenerVisitTerc?.goDetalhe()
                }
            }
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_falha_insercao_campo,
                "VEÍCULO"
            ), requireContext()
        )
    }

    private fun setBundleDetalheVisitTerc(pos: Int){
        val bundle = Bundle()
        bundle.putInt(POS_DETALHE_VISIT_TERC, pos)
        setFragmentResult(REQUEST_KEY_DETALHE_VISIT_TERC, bundle)
    }

    private fun setBundlePlacaVisitTerc(flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_PLACA_VISIT_TERC, flowApp.ordinal)
        bundle.putInt(POS_PLACA_VISIT_TERC, pos)
        setFragmentResult(REQUEST_KEY_PLACA_VISIT_TERC, bundle)
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