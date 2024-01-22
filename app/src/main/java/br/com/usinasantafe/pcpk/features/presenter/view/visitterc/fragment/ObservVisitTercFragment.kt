package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentObservVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.ObservVisitTercFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.ObservVisitTercViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ObservVisitTercFragment : BaseFragment<FragmentObservVisitTercBinding>(
    R.layout.fragment_observ_visit_terc,
    FragmentObservVisitTercBinding::bind
) {

    private val viewModel: ObservVisitTercViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null
    private lateinit var flowApp: FlowApp
    private var typeMov: TypeMov? = null
    private var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.flowApp = fragmentAttachListenerVisitTerc?.getFlowApp()!!
        this.typeMov = fragmentAttachListenerVisitTerc?.getTypeMov()
        this.pos = fragmentAttachListenerVisitTerc?.getPos()!!
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
            buttonOkObserv.setOnClickListener {
                if (editTextObserv.text.isEmpty()) {
                    viewModel.setObserv(null, pos, typeMov, flowApp)
                    return@setOnClickListener
                }
                viewModel.setObserv(editTextObserv.text.toString(), pos, typeMov, flowApp)
            }
            buttonCancObserv.setOnClickListener {
                when (flowApp) {
                    FlowApp.ADD -> {
                        when (typeMov!!) {
                            TypeMov.INPUT -> fragmentAttachListenerVisitTerc?.goDestino(FlowApp.ADD)
                            TypeMov.OUTPUT -> fragmentAttachListenerVisitTerc?.goMovVisitTercList()
                        }
                    }
                    FlowApp.CHANGE -> fragmentAttachListenerVisitTerc?.goDetalhe(pos)
                }
            }
        }
    }

    private fun handleStateChange(state: ObservVisitTercFragmentState) {
        when (state) {
            is ObservVisitTercFragmentState.CheckSetObserv -> handleCheckSetObserv(state.check)
        }
    }

    private fun handleCheckSetObserv(check: Boolean) {
        if (check) {
            when(flowApp){
                FlowApp.ADD -> fragmentAttachListenerVisitTerc?.goMovVisitTercList()
                FlowApp.CHANGE -> fragmentAttachListenerVisitTerc?.goDetalhe(pos)
            }
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_falha_insercao_campo,
                "OBSERVAÇÃO"
            ), requireContext()
        )
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