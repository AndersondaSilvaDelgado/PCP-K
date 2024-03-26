package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.setListenerButtonsGenericSUpdate
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.databinding.FragmentNotaFiscalProprioBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DestinoProprioFragment.Companion.FLOW_APP_DESTINO_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DestinoProprioFragment.Companion.POS_DESTINO_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DestinoProprioFragment.Companion.REQUEST_KEY_DESTINO_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DetalheMovEquipProprioFragment.Companion.POS_DETALHE_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.DetalheMovEquipProprioFragment.Companion.REQUEST_KEY_DETALHE_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.ObservProprioFragment.Companion.FLOW_APP_OBSERV_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.ObservProprioFragment.Companion.POS_OBSERV_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment.ObservProprioFragment.Companion.REQUEST_KEY_OBSERV_PROPRIO
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.NotaFiscalProprioFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.NotaFiscalProprioViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Flow

@AndroidEntryPoint
class NotaFiscalProprioFragment : BaseFragment<FragmentNotaFiscalProprioBinding>(
    R.layout.fragment_nota_fiscal_proprio,
    FragmentNotaFiscalProprioBinding::bind
) {

    private val viewModel: NotaFiscalProprioViewModel by viewModels()
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null
    private lateinit var flowApp: FlowApp
    private var pos: Int = 0

    companion object {
        const val REQUEST_KEY_NOTA_FISCAL_PROPRIO = "requestKeyNotaFiscalProprio"
        const val FLOW_APP_NOTA_FISCAL_PROPRIO = "flowAppNotaFiscalProprio"
        const val POS_NOTA_FISCAL_PROPRIO = "posNotaFiscalProprio"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REQUEST_KEY_NOTA_FISCAL_PROPRIO) { _, bundle ->
            this.flowApp = FlowApp.values()[bundle.getInt(FLOW_APP_NOTA_FISCAL_PROPRIO)]
            this.pos = bundle.getInt(POS_NOTA_FISCAL_PROPRIO)
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
            setListenerButtonsGenericSUpdate(layoutBotoes, editTextPadrao)
            layoutBotoes.buttonOkPadrao.setOnClickListener {
                if (editTextPadrao.text.isEmpty()) {
                    setBundleDestino(flowApp, 0)
                    fragmentAttachListenerProprio?.goObserv()
                    return@setOnClickListener
                }
                viewModel.setNotaFiscal(editTextPadrao.text.toString(), flowApp, pos)
            }
        }
    }

    private fun handleStateChange(state: NotaFiscalProprioFragmentState) {
        when (state) {
            is NotaFiscalProprioFragmentState.CheckSetNotaFiscal -> handleCheckSetNotaFiscal(state.check)
        }
    }

    private fun handleCheckSetNotaFiscal(check: Boolean) {
        if (check) {
            when(flowApp) {
                FlowApp.ADD -> {
                    setBundleObserv(flowApp, 0)
                    fragmentAttachListenerProprio?.goObserv()
                }
                FlowApp.CHANGE -> {
                    setBundleDetalhe(pos)
                    fragmentAttachListenerProprio?.goDetalhe()
                }
            }
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_falha_insercao_campo,
                "DESTINO"
            ), requireContext()
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListenerProprio){
            fragmentAttachListenerProprio = context
        }
        onBackPressed {
            when(flowApp) {
                FlowApp.ADD -> {
                    setBundleDestino(flowApp, 0)
                    fragmentAttachListenerProprio?.goDestino()
                }
                FlowApp.CHANGE -> {
                    setBundleDetalhe(pos)
                    fragmentAttachListenerProprio?.goDetalhe()
                }
            }
        }
    }

    private fun setBundleDetalhe(pos: Int){
        val bundle = Bundle()
        bundle.putInt(POS_DETALHE_PROPRIO, pos)
        setFragmentResult(REQUEST_KEY_DETALHE_PROPRIO, bundle)
    }

    private fun setBundleObserv(flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_OBSERV_PROPRIO, flowApp.ordinal)
        bundle.putInt(POS_OBSERV_PROPRIO, pos)
        setFragmentResult(REQUEST_KEY_OBSERV_PROPRIO, bundle)
    }

    private fun setBundleDestino(flowApp: FlowApp, pos: Int){
        val bundle = Bundle()
        bundle.putInt(FLOW_APP_DESTINO_PROPRIO, flowApp.ordinal)
        bundle.putInt(POS_DESTINO_PROPRIO, pos)
        setFragmentResult(REQUEST_KEY_DESTINO_PROPRIO, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}