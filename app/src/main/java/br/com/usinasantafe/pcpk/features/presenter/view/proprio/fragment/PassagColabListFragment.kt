package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialogCheck
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.databinding.FragmentPassagColabListBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.PassagColabListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.PassagColabListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassagColabListFragment : BaseFragment<FragmentPassagColabListBinding>(
    R.layout.fragment_passag_colab_list,
    FragmentPassagColabListBinding::bind,
) {

    private val viewModel: PassagColabListViewModel by viewModels()
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null
    private lateinit var typeAddOcupante: TypeAddOcupante
    private var pos: Int = 0

    companion object {
        const val KEY_TYPE_OCUPANTE_VEIC_PROPRIO_PASSAG_LIST =
            "key_type_ocupante_veic_proprio_passag_list";
        const val KEY_POS_PASSAG_PROPRIO = "key_pos_passag_proprio";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeAddOcupante =
            TypeAddOcupante.values()[arguments?.getInt(KEY_TYPE_OCUPANTE_VEIC_PROPRIO_PASSAG_LIST)!!]
        pos = arguments?.getInt(KEY_POS_PASSAG_PROPRIO)!!
        observeState()
        startEvents()
        setListener()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(viewLifecycleOwner) { state ->
            handleStateChange(state)
        }
    }

    private fun startEvents() {
        viewModel.recoverListPassag()
    }

    private fun setListener() {
        with(binding) {
            buttonInserirPassageiro.setOnClickListener {
                fragmentAttachListenerProprio?.goMatricColab(typeAddOcupante)
            }
            buttonOkPassageiro.setOnClickListener {
                when (typeAddOcupante) {
                    TypeAddOcupante.ADDMOTORISTA,
                    TypeAddOcupante.ADDPASSAGEIRO -> fragmentAttachListenerProprio?.goDestino(
                        FlowApp.ADD
                    )

                    TypeAddOcupante.CHANGEMOTORISTA,
                    TypeAddOcupante.CHANGEPASSAGEIRO -> fragmentAttachListenerProprio?.goDetalhe(pos)
                }
            }
            buttonCancPassageiro.setOnClickListener {
                fragmentAttachListenerProprio?.goMatricColab(typeAddOcupante)
            }
        }
    }

    private fun handleStateChange(state: PassagColabListFragmentState) {
        when (state) {
            is PassagColabListFragmentState.ListColabPassag -> handleListPassag(state.passagList)
            is PassagColabListFragmentState.CheckDeleteColabPassag -> handleCheckDeleteColabPassag(
                state.check
            )
        }
    }

    private fun handleListPassag(passagList: List<String>) {
        viewList(passagList)
    }

    private fun handleCheckDeleteColabPassag(check: Boolean) {
        if (check) {
            viewModel.recoverListPassag()
            return
        }
        showGenericAlertDialog(
            getString(R.string.texto_failure_delete, "PASSAGEIRO"),
            requireContext()
        )
    }

    private fun viewList(passagList: List<String>) {

        val localListView = passagList.map { it }

        val listAdapter = CustomAdapter(localListView).apply {
            onItemClick = { _, pos ->
                showMessage(pos)
            }
        }
        binding.listViewPassag.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun showMessage(pos: Int) {
        showGenericAlertDialogCheck("DESEJA EXCLUIR O PASSAGEIRO?", requireContext()) {
            viewModel.deletePassag(pos)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerProprio) {
            fragmentAttachListenerProprio = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}