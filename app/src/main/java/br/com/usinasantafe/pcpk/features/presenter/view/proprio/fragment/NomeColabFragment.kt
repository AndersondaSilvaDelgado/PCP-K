package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.databinding.FragmentNomeColabBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.NomeColabFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.NomeColabViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NomeColabFragment : BaseFragment<FragmentNomeColabBinding>(
    R.layout.fragment_nome_colab,
    FragmentNomeColabBinding::bind
) {

    private val viewModel: NomeColabViewModel by viewModels()
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null
    private lateinit var matricColab: String
    private lateinit var typeAddOcupante: TypeAddOcupante
    private var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.matricColab = fragmentAttachListenerProprio?.getMatricColab()!!
        this.typeAddOcupante = fragmentAttachListenerProprio?.getTypeAddOcupante()!!
        this.pos = fragmentAttachListenerProprio?.getPos()!!
        observeState()
        startEvents()
        setListener()

    }

    private fun observeState(){
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            state -> handleStateChange(state)
        }
    }

    private fun startEvents() {
        viewModel.recoverDataNome(matricColab)
    }

    private fun setListener() {
        with(binding) {
            buttonOkNome.setOnClickListener {
                viewModel.setMatricMotorista(matricColab, typeAddOcupante, pos)
            }
            buttonCancNome.setOnClickListener {
                fragmentAttachListenerProprio?.goMatricColab(typeAddOcupante)
            }
        }
    }

    private fun handleStateChange(state: NomeColabFragmentState){
        when(state){
            is NomeColabFragmentState.GetNomeColab -> handleNomeVigia(state.nomeColab)
            is NomeColabFragmentState.CheckSetMatric -> handleCheckSetMatricColab(state.checkSetMatric)
        }
    }

    private fun handleNomeVigia(nome: String){
        with(binding) {
            textViewNome.text = nome
        }
    }

    private fun handleCheckSetMatricColab(checkSetMatricColab: Boolean) {
        if (checkSetMatricColab) {
            when(typeAddOcupante) {
                TypeAddOcupante.ADDMOTORISTA,
                TypeAddOcupante.ADDPASSAGEIRO -> fragmentAttachListenerProprio?.goPassagList(typeAddOcupante)
                TypeAddOcupante.CHANGEMOTORISTA,
                TypeAddOcupante.CHANGEPASSAGEIRO -> fragmentAttachListenerProprio?.goDetalhe(pos)
            }
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
        if(context is FragmentAttachListenerProprio){
            fragmentAttachListenerProprio = context
        }
        onBackPressed {
            fragmentAttachListenerProprio?.goMatricColab(typeAddOcupante)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}