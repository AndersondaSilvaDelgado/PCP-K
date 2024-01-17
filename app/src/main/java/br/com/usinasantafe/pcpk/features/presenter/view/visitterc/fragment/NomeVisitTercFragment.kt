package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.databinding.FragmentNomeVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.model.DisplayDataVisitTercModel
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment.CPFVisitTercFragment.Companion.KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.NomeVisitTercFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.NomeVisitTercViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NomeVisitTercFragment : BaseFragment<FragmentNomeVisitTercBinding>(
    R.layout.fragment_nome_visit_terc,
    FragmentNomeVisitTercBinding::bind
) {

    private val viewModel: NomeVisitTercViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null
    private lateinit var cpfVisitTerc: String
    private lateinit var typeAddOcupante: TypeAddOcupante

    companion object {
        const val KEY_CPF_VISIT_TERC = "key_cpf_visit_terc";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cpfVisitTerc = arguments?.getString(KEY_CPF_VISIT_TERC)!!
        typeAddOcupante = TypeAddOcupante.values()[arguments?.getInt(KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC)!!]
        observeState()
        startEvents()
        setListener()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            state -> handleStateChange(state)
        }
    }

    private fun startEvents() {
        viewModel.recoverData(cpfVisitTerc)
    }

    private fun setListener() {
        with(binding) {
            buttonOkNome.setOnClickListener {
                viewModel.setCPFVisitTerc(cpfVisitTerc, typeAddOcupante)
            }
            buttonCancNome.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goCPFVisitTerc(typeAddOcupante)
            }
        }
    }

    private fun handleStateChange(state: NomeVisitTercFragmentState){
        when(state){
            is NomeVisitTercFragmentState.GetDataVisitTerc -> handleDataVisitTerc(state.display)
            is NomeVisitTercFragmentState.CheckSetCPF -> handleCheckSetCPF(state.checkSetCPF)
        }
    }

    private fun handleDataVisitTerc(display: DisplayDataVisitTercModel){
        with(binding) {
            textViewTituloNome.text = display.tipo
            textViewNome.text = display.nome
            textViewEmpresa.text = display.empresas
        }
    }

    private fun handleCheckSetCPF(checkSetMatricColab: Boolean) {
        if (checkSetMatricColab) {
            fragmentAttachListenerVisitTerc?.goPassagList()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_falha_insercao_campo,
                "MATRICULA DO COLABORADOR"
            ), requireContext()
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListenerVisitTerc){
            fragmentAttachListenerVisitTerc = context
        }
        onBackPressed {
            fragmentAttachListenerVisitTerc?.goCPFVisitTerc(typeAddOcupante)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerVisitTerc = null
    }

}