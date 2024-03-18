package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.databinding.FragmentVeiculoVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.flowApp = fragmentAttachListenerVisitTerc?.getFlowApp()!!
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
                    FlowApp.CHANGE -> fragmentAttachListenerVisitTerc?.goDetalhe(pos)
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
                FlowApp.ADD -> fragmentAttachListenerVisitTerc?.goPlaca(flowApp)
                FlowApp.CHANGE -> fragmentAttachListenerVisitTerc?.goDetalhe(pos)
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