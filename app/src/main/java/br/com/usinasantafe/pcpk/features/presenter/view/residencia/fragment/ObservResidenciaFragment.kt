package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentObservResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
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
    private lateinit var typeMov: TypeMov
    private var pos: Int? = null

    companion object {
        const val KEY_TYPE_MOV_RESIDENCIA = "key_type_mov_residencia";
        const val KEY_POS_MOV_RESIDENCIA = "key_pos_mov_residencia";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeMov = TypeMov.values()[arguments?.getInt(KEY_TYPE_MOV_RESIDENCIA)!!]
        if(typeMov == TypeMov.OUTPUT){
            pos = arguments?.getInt(KEY_POS_MOV_RESIDENCIA)
        }
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
                    viewModel.setObserv(null, pos, typeMov)
                    return@setOnClickListener
                }
                viewModel.setObserv(editTextObserv.text.toString(), pos, typeMov)
            }
            buttonCancObserv.setOnClickListener {
                if(typeMov == TypeMov.INPUT) {
                    fragmentAttachListenerResidencia?.goMotorista()
                } else {
                    fragmentAttachListenerResidencia?.goMovResidenciaList()
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
            fragmentAttachListenerResidencia?.goMovResidenciaList()
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
        if(context is FragmentAttachListenerResidencia){
            fragmentAttachListenerResidencia = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerResidencia = null
    }

}