package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.databinding.FragmentObservVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.ObservVisitTercFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.ObservVisitTercViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ObservVisitTercFragment : BaseFragment<FragmentObservVisitTercBinding>(
    R.layout.fragment_observ_visit_terc,
    FragmentObservVisitTercBinding::bind
) {

    private val viewModel: ObservVisitTercViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null
    private lateinit var typeMov: TypeMov
    private var pos: Int? = null

    companion object {
        const val KEY_TYPE_MOV_VISIT_TERC = "key_type_mov_visit_terc";
        const val KEY_POS_MOV_VISIT_TERC = "key_pos_mov_visit_terc";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeMov = TypeMov.values()[arguments?.getInt(KEY_TYPE_MOV_VISIT_TERC)!!]
        if(typeMov == TypeMov.OUTPUT){
            pos = arguments?.getInt(KEY_POS_MOV_VISIT_TERC)
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
                    fragmentAttachListenerVisitTerc?.goDestino()
                } else {
                    fragmentAttachListenerVisitTerc?.goMovVisitTercList()
                }
            }
        }
    }

    private fun handleStateChange(state: ObservVisitTercFragmentState) {
        when (state) {
            is ObservVisitTercFragmentState.CheckSetObserv -> handleCheckSetObserv(state.check)
        }
    }

    private fun handleCheckSetObserv(check: Boolean) {
        if (check) {
            fragmentAttachListenerVisitTerc?.goMovVisitTercList()
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
        if(context is FragmentAttachListenerVisitTerc){
            fragmentAttachListenerVisitTerc = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerVisitTerc = null
    }

}