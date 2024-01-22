package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeVisitTerc
import br.com.usinasantafe.pcpk.databinding.FragmentTipoVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.TipoVisitTercFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.TipoVisitTercViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TipoVisitTercFragment : BaseFragment<FragmentTipoVisitTercBinding>(
    R.layout.fragment_tipo_visit_terc,
    FragmentTipoVisitTercBinding::bind,
) {

    private val viewModel: TipoVisitTercViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        viewList()
        setListener()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            state -> handleStateChange(state)
        }
    }

    private fun viewList() {
        val optionType = listOf(
            "TERCEIRO",
            "VISITANTE",
        )
        val listAdapter = CustomAdapter(optionType).apply {
            onItemClick = { text, _ ->
                when(text){
                    "TERCEIRO" -> viewModel.setTipo(TypeVisitTerc.TERCEIRO)
                    "VISITANTE" -> viewModel.setTipo(TypeVisitTerc.VISITANTE)
                }
            }
        }
        binding.listViewTipo.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun handleStateChange(state: TipoVisitTercFragmentState){
        when(state){
            is TipoVisitTercFragmentState.CheckSetTipo -> handleCheckSetTipo(state.check)
        }
    }

    private fun setListener() {
        with(binding) {
            buttonRetornarTipo.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goPlaca(FlowApp.ADD)
            }
        }
    }

    private fun handleCheckSetTipo(check: Boolean) {
        if (check) {
            fragmentAttachListenerVisitTerc?.goCPFVisitTerc(TypeAddOcupante.ADDMOTORISTA)
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_falha_insercao_campo,
                "PLACA"
            ), requireContext()
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerVisitTerc) {
            fragmentAttachListenerVisitTerc = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerVisitTerc = null
    }

}