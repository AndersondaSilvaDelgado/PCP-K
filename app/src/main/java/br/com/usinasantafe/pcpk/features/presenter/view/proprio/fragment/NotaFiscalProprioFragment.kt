package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.setListenerButtonsGenericSUpdate
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.databinding.FragmentNotaFiscalProprioBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.NotaFiscalProprioFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.NotaFiscalProprioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotaFiscalProprioFragment : BaseFragment<FragmentNotaFiscalProprioBinding>(
    R.layout.fragment_nota_fiscal_proprio,
    FragmentNotaFiscalProprioBinding::bind
) {

    private val viewModel: NotaFiscalProprioViewModel by viewModels()
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null

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
                    fragmentAttachListenerProprio?.goObserv()
                    return@setOnClickListener
                }
                viewModel.setNotaFiscal(editTextPadrao.text.toString())
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
            fragmentAttachListenerProprio?.goObserv()
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
            fragmentAttachListenerProprio?.goDestino()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}