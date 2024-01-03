package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.databinding.FragmentObservProprioBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.ObservProprioFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.ObservProprioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ObservProprioFragment : BaseFragment<FragmentObservProprioBinding>(
    R.layout.fragment_observ_proprio,
    FragmentObservProprioBinding::bind
) {

    private val viewModel: ObservProprioViewModel by viewModels()
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
            buttonOkObserv.setOnClickListener {
                if (editTextObserv.text.isEmpty()) {
                    fragmentAttachListenerProprio?.goMovProprioList()
                    return@setOnClickListener
                }
                viewModel.setObserv(editTextObserv.text.toString())
            }
        }
    }

    private fun handleStateChange(state: ObservProprioFragmentState) {
        when (state) {
            is ObservProprioFragmentState.CheckSetObserv -> handleCheckSetObserv(state.check)
        }
    }

    private fun handleCheckSetObserv(check: Boolean) {
        if (check) {
            fragmentAttachListenerProprio?.goMovProprioList()
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