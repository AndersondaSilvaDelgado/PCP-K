package br.com.usinasantafe.pcpk.features.presenter.view.initial.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.databinding.FragmentNomeVigiaBinding
import br.com.usinasantafe.pcpk.features.presenter.view.initial.FragmentAttachListenerInitial
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial.NomeVigiaFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial.NomeVigiaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NomeVigiaFragment : BaseFragment<FragmentNomeVigiaBinding>(
    R.layout.fragment_nome_vigia,
    FragmentNomeVigiaBinding::bind
) {

    private val viewModel: NomeVigiaViewModel by viewModels()
    private var fragmentAttachListenerInitial: FragmentAttachListenerInitial? = null

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
                fragmentAttachListenerInitial?.goLocalInicial()
            }
            buttonCancNome.setOnClickListener {
                fragmentAttachListenerInitial?.goMatricVigia()
            }
        }
    }

    private fun handleStateChange(state: NomeVigiaFragmentState){
        when(state){
            is NomeVigiaFragmentState.GetNomeVigia -> handleNomeVigia(state.nomeVigia)
        }
    }

    private fun handleNomeVigia(nome: String){
        with(binding) {
            textViewNome.text = nome
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListenerInitial){
            fragmentAttachListenerInitial = context
        }
        onBackPressed {
            fragmentAttachListenerInitial?.goMatricVigia()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerInitial = null
    }

}