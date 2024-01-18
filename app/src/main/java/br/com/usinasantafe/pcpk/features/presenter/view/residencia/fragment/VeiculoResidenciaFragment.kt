package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.databinding.FragmentVeiculoResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.VeiculoVisitTercFragment
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.VeiculoResidenciaFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.VeiculoResidenciaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VeiculoResidenciaFragment : BaseFragment<FragmentVeiculoResidenciaBinding>(
    R.layout.fragment_veiculo_residencia,
    FragmentVeiculoResidenciaBinding::bind
) {

    private val viewModel: VeiculoResidenciaViewModel by viewModels()
    private var fragmentAttachListenerResidencia: FragmentAttachListenerResidencia? = null
    private lateinit var flowApp: FlowApp
    private var pos: Int = 0

    companion object {
        const val KEY_FLOW_VEICULO_RESIDENCIA = "key_flow_veiculo_residencia";
        const val KEY_POS_VEICULO_RESIDENCIA = "key_pos_veiculo_residencia";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowApp = FlowApp.values()[arguments?.getInt(KEY_FLOW_VEICULO_RESIDENCIA)!!]
        pos = arguments?.getInt(KEY_POS_VEICULO_RESIDENCIA)!!
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
            buttonOkVeicResidencia.setOnClickListener {
                if (editTextVeicResidencia.text.isEmpty()) {
                    showGenericAlertDialog(
                        getString(
                            R.string.texto_campo_vazio,
                            "VEÍCULO"
                        ), requireContext()
                    )
                    return@setOnClickListener
                }
                viewModel.setVeiculo(editTextVeicResidencia.text.toString(), flowApp, pos)
            }
            buttonCancVeicResidencia.setOnClickListener {
                when(flowApp){
                    FlowApp.ADD -> fragmentAttachListenerResidencia?.goMovResidenciaList()
                    FlowApp.CHANGE -> fragmentAttachListenerResidencia?.goDetalhe(pos)
                }
            }
        }
    }

    private fun handleStateChange(state: VeiculoResidenciaFragmentState) {
        when (state) {
            is VeiculoResidenciaFragmentState.CheckSetVeiculo -> handleCheckSetVeiculo(state.check)
        }
    }

    private fun handleCheckSetVeiculo(check: Boolean) {
        if (check) {
            when(flowApp) {
                FlowApp.ADD -> fragmentAttachListenerResidencia?.goPlaca(flowApp)
                FlowApp.CHANGE -> fragmentAttachListenerResidencia?.goDetalhe(pos)
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