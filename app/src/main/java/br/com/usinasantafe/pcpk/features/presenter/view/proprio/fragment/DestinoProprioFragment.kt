package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.databinding.FragmentDestinoProprioBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.DestinoProprioFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.DestinoProprioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DestinoProprioFragment : BaseFragment<FragmentDestinoProprioBinding>(
    R.layout.fragment_destino_proprio,
    FragmentDestinoProprioBinding::bind
) {

    private val viewModel: DestinoProprioViewModel by viewModels()
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null
    private lateinit var flowApp: FlowApp
    private var pos: Int = 0

    companion object {
        const val KEY_FLOW_DESTINO_PROPRIO = "key_flow_destino_proprio";
        const val KEY_POS_DESTINO_PROPRIO = "key_pos_destino_proprio";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowApp = FlowApp.values()[arguments?.getInt(KEY_FLOW_DESTINO_PROPRIO)!!]
        pos = arguments?.getInt(KEY_POS_DESTINO_PROPRIO)!!
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
            buttonOkDestino.setOnClickListener {
                if (editTextDestino.text.isEmpty()) {
                    showGenericAlertDialog(
                        getString(
                            R.string.texto_campo_vazio,
                            "DESTINO"
                        ), requireContext()
                    )
                    return@setOnClickListener
                }
                viewModel.setDestino(editTextDestino.text.toString(), flowApp, pos)
            }
            buttonCancDestino.setOnClickListener {
                if (flowApp == FlowApp.ADD) {
                    fragmentAttachListenerProprio?.goPassagList(TypeAddOcupante.ADDPASSAGEIRO)
                    return@setOnClickListener
                }
                fragmentAttachListenerProprio?.goDetalhe(pos)
            }
        }
    }

    private fun handleStateChange(state: DestinoProprioFragmentState) {
        when (state) {
            is DestinoProprioFragmentState.CheckSetDestino -> handleCheckSetDestino(state.check)
            is DestinoProprioFragmentState.GoFragmentNotaFiscal -> handleGoFragmentNotaFiscal()
            is DestinoProprioFragmentState.GoFragmentObserv -> handleGoFragmentObserv()
        }
    }

    private fun handleCheckSetDestino(checkSetMatricColab: Boolean) {
        if (checkSetMatricColab) {
            viewModel.checkNextFragment()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_falha_insercao_campo,
                "DESTINO"
            ), requireContext()
        )
    }

    private fun handleGoFragmentNotaFiscal(){
        when(flowApp) {
            FlowApp.ADD -> fragmentAttachListenerProprio?.goNotaFiscal(flowApp)
            FlowApp.CHANGE -> fragmentAttachListenerProprio?.goDetalhe(pos)
        }
    }

    private fun handleGoFragmentObserv(){
        when(flowApp) {
            FlowApp.ADD -> fragmentAttachListenerProprio?.goObserv(flowApp)
            FlowApp.CHANGE -> fragmentAttachListenerProprio?.goDetalhe(pos)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerProprio) {
            fragmentAttachListenerProprio = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}