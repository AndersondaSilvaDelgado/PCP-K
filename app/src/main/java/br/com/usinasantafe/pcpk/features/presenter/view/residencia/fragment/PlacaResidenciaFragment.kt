package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.databinding.FragmentPlacaResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.PlacaResidenciaFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.PlacaResidenciaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlacaResidenciaFragment : BaseFragment<FragmentPlacaResidenciaBinding>(
    R.layout.fragment_placa_residencia,
    FragmentPlacaResidenciaBinding::bind
) {

    private val viewModel: PlacaResidenciaViewModel by viewModels()
    private var fragmentAttachListenerResidencia: FragmentAttachListenerResidencia? = null
    private lateinit var flowApp: FlowApp
    private var pos: Int = 0

    companion object {
        const val KEY_FLOW_PLACA_RESIDENCIA = "key_flow_placa_residencia";
        const val KEY_POS_PLACA_RESIDENCIA = "key_pos_placa_residencia";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowApp = FlowApp.values()[arguments?.getInt(KEY_FLOW_PLACA_RESIDENCIA)!!]
        pos = arguments?.getInt(KEY_POS_PLACA_RESIDENCIA)!!
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
            buttonOkPlacaResidencia.setOnClickListener {
                if (editTextPlacaResidencia.text.isEmpty()) {
                    showGenericAlertDialog(
                        getString(
                            R.string.texto_campo_vazio,
                            "PLACA"
                        ), requireContext()
                    )
                    return@setOnClickListener
                }
                viewModel.setPlaca(editTextPlacaResidencia.text.toString(), flowApp, pos)
            }
            buttonCancPlacaResidencia.setOnClickListener {
                when(flowApp) {
                    FlowApp.ADD -> fragmentAttachListenerResidencia?.goVeiculo(flowApp)
                    FlowApp.CHANGE -> fragmentAttachListenerResidencia?.goDetalhe(pos)
                }
            }
        }
    }

    private fun handleStateChange(state: PlacaResidenciaFragmentState) {
        when (state) {
            is PlacaResidenciaFragmentState.CheckSetPlaca -> handleCheckSetPlaca(state.check)
        }
    }

    private fun handleCheckSetPlaca(check: Boolean) {
        if (check) {
            when (flowApp) {
                FlowApp.ADD -> fragmentAttachListenerResidencia?.goMotorista(flowApp)
                FlowApp.CHANGE -> fragmentAttachListenerResidencia?.goDetalhe(pos)
            }
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_falha_insercao_campo,
                "PLACA"
            ), requireContext()
        )
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