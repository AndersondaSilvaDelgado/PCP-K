package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        viewModel.recoverDataNomeVigia()
    }

    private fun setListener() {
        with(binding) {
            buttonOkNome.setOnClickListener {
                fragmentAttachListenerProprio?.goPassagList()
            }
            buttonCancNome.setOnClickListener {
                fragmentAttachListenerProprio?.goMatricColab()
            }
        }
    }

    private fun handleStateChange(state: NomeColabFragmentState){
        when(state){
            is NomeColabFragmentState.GetNomeColab -> handleNomeVigia(state.nomeColab)
        }
    }

    private fun handleNomeVigia(nome: String){
        with(binding) {
            textViewNome.text = nome
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListenerProprio){
            fragmentAttachListenerProprio = context
        }
        onBackPressed {
            fragmentAttachListenerProprio?.goMatricColab()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}