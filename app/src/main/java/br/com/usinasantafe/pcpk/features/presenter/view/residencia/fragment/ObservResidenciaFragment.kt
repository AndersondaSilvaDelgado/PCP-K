package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentObservResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.DetalheMovEquipResidenciaFragment.Companion.POS_DETALHE_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.DetalheMovEquipResidenciaFragment.Companion.REQUEST_KEY_DETALHE_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MotoristaResidenciaFragment.Companion.FLOW_APP_MOTORISTA_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MotoristaResidenciaFragment.Companion.POS_MOTORISTA_RESIDENCIA
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment.MotoristaResidenciaFragment.Companion.REQUEST_KEY_MOTORISTA_RESIDENCIA
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
    private lateinit var typeMov: TypeMov
    private var pos: Int = 0

    companion object {
        const val REQUEST_KEY_OBSERV_RESIDENCIA = "requestKeyObservResidencia"
        const val FLOW_APP_OBSERV_RESIDENCIA = "flowAppObservResidencia"
        const val TYPE_MOV_OBSERV_RESIDENCIA = "typeMovObservResidencia"
        const val POS_OBSERV_RESIDENCIA = "posObservResidencia"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REQUEST_KEY_OBSERV_RESIDENCIA) { _, bundle ->
            this.flowApp = FlowApp.values()[bundle.getInt(FLOW_APP_OBSERV_RESIDENCIA)]
            this.pos = bundle.getInt(POS_OBSERV_RESIDENCIA)
            this.typeMov = TypeMov.values()[bundle.getInt(TYPE_MOV_OBSERV_RESIDENCIA)]
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
                        when (typeMov) {
                            TypeMov.INPUT -> {
                                setBundleMotoristaResidencia(flowApp, 0)
                                fragmentAttachListenerResidencia?.goMotorista()
                            }
                            TypeMov.OUTPUT -> fragmentAttachListenerResidencia?.goMovResidenciaList()
                            else -> {}
                        }
                    }
                    FlowApp.CHANGE -> {
                        setBundleDetalheResidencia(pos)
                        fragmentAttachListenerResidencia?.goDetalhe()
                    }
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
                FlowApp.CHANGE -> {
                    setBundleDetalheResidencia(pos)
                    fragmentAttachListenerResidencia?.goDetalhe()
                }
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

    private fun setBundleDetalheResidencia(pos: Int){
        val bundle = Bundle()
        bundle.putInt(POS_DETALHE_RESIDENCIA, pos)
        setFragmentResult(REQUEST_KEY_DETALHE_RESIDENCIA, bundle)
    }

    private fun setBundleMotoristaResidencia(flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_MOTORISTA_RESIDENCIA, flowApp.ordinal)
        bundle.putInt(POS_MOTORISTA_RESIDENCIA, pos)
        setFragmentResult(REQUEST_KEY_MOTORISTA_RESIDENCIA, bundle)
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