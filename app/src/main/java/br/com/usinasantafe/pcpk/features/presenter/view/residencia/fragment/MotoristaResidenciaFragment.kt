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
import br.com.usinasantafe.pcpk.databinding.FragmentMotoristaResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.MotoristaResidenciaFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.MotoristaResidenciaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MotoristaResidenciaFragment : BaseFragment<FragmentMotoristaResidenciaBinding>(
    R.layout.fragment_motorista_residencia,
    FragmentMotoristaResidenciaBinding::bind
) {

    private val viewModel: MotoristaResidenciaViewModel by viewModels()
    private var fragmentAttachListenerResidencia: FragmentAttachListenerResidencia? = null
    private lateinit var flowApp: FlowApp
    private var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.flowApp = fragmentAttachListenerResidencia?.getFlowApp()!!
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
            buttonOkMotoristaResidencia.setOnClickListener {
                if (editTextMotoristaResidencia.text.isEmpty()) {
                    showGenericAlertDialog(
                        getString(
                            R.string.texto_campo_vazio,
                            "VEÍCULO"
                        ), requireContext()
                    )
                    return@setOnClickListener
                }
                viewModel.setMotorista(editTextMotoristaResidencia.text.toString(), flowApp, pos)
            }
            buttonCancMotoristaResidencia.setOnClickListener {
                when(flowApp) {
                    FlowApp.ADD -> fragmentAttachListenerResidencia?.goPlaca(flowApp)
                    FlowApp.CHANGE -> fragmentAttachListenerResidencia?.goDetalhe(pos)
                }
            }
        }
    }

    private fun handleStateChange(state: MotoristaResidenciaFragmentState) {
        when (state) {
            is MotoristaResidenciaFragmentState.CheckSetMotorista -> handleCheckSetVeiculo(state.check)
        }
    }

    private fun handleCheckSetVeiculo(check: Boolean) {
        if (check) {
            when(flowApp) {
                FlowApp.ADD -> fragmentAttachListenerResidencia?.goObserv(TypeMov.INPUT, flowApp)
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