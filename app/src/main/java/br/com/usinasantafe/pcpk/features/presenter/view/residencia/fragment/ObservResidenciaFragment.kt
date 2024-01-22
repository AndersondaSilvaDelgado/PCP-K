package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentObservResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.ObservVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.ObservResidenciaFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.ObservResidenciaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ObservResidenciaFragment : BaseFragment<FragmentObservResidenciaBinding>(
    R.layout.fragment_observ_residencia,
    FragmentObservResidenciaBinding::bind
) {

    private val viewModel: ObservResidenciaViewModel by viewModels()
    private var fragmentAttachListenerResidencia: FragmentAttachListenerResidencia? = null
    private lateinit var flowApp: FlowApp
    private var typeMov: TypeMov? = null
    private var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.flowApp = fragmentAttachListenerResidencia?.getFlowApp()!!
        this.typeMov = fragmentAttachListenerResidencia?.getTypeMov()
        this.pos = fragmentAttachListenerResidencia?.getPos()!!
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
                        when(typeMov!!) {
                            TypeMov.INPUT -> fragmentAttachListenerResidencia?.goMotorista(flowApp)
                            TypeMov.OUTPUT -> fragmentAttachListenerResidencia?.goMovResidenciaList()
                        }
                    }
                    FlowApp.CHANGE -> fragmentAttachListenerResidencia?.goDetalhe(pos)
                }
            }
        }
    }

    private fun handleStateChange(state: ObservResidenciaFragmentState) {
        when (state) {
            is ObservResidenciaFragmentState.CheckSetObserv -> handleCheckSetObserv(state.check)
        }
    }

    private fun handleCheckSetObserv(check: Boolean) {
        if (check) {
            when(flowApp) {
                FlowApp.ADD -> fragmentAttachListenerResidencia?.goMovResidenciaList()
                FlowApp.CHANGE -> fragmentAttachListenerResidencia?.goDetalhe(pos)
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
        if(context is FragmentAttachListenerResidencia){
            fragmentAttachListenerResidencia = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerResidencia = null
    }

}