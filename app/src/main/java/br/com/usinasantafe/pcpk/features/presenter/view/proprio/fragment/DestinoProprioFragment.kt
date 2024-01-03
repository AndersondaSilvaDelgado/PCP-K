package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
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
                viewModel.setDestino(editTextDestino.text.toString())
            }
            buttonCancDestino.setOnClickListener {
                fragmentAttachListenerProprio?.goPassagList()
            }
        }
    }

    private fun handleStateChange(state: DestinoProprioFragmentState) {
        when (state) {
            is DestinoProprioFragmentState.CheckSetDestino -> handleCheckSetDestino(state.check)
            is DestinoProprioFragmentState.GoFragmentNotaFiscal -> fragmentAttachListenerProprio?.goNotaFiscal()
            is DestinoProprioFragmentState.GoFragmentObserv -> fragmentAttachListenerProprio?.goObserv()
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListenerProprio){
            fragmentAttachListenerProprio = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}