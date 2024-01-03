package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
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
        viewModel.recoverListPassag()
    }

    private fun setListener() {
        with(binding) {
            buttonInserirPassageiro.setOnClickListener {
                viewModel.setStatusMovEquipAddPassag()
            }
            buttonOkPassageiro.setOnClickListener {
                fragmentAttachListenerProprio?.goDestino()
            }
            buttonCancPassageiro.setOnClickListener {
                viewModel.setStatusMovEquipAddMotorista()
            }
        }
    }

    private fun handleStateChange(state: PassagColabListFragmentState){
        when(state){
            is PassagColabListFragmentState.ListColabPassag -> handleListPassag(state.passagList)
            is PassagColabListFragmentState.CheckDeleteColabPassag -> handleCheckDeleteColabPassag(state.check)
            is PassagColabListFragmentState.CheckSetStatusMovAddPassag -> handleCheckStatusMovEquipAddPassag(state.check)
            is PassagColabListFragmentState.CheckSetStatusMovAddMotorista -> handleCheckStatusMovEquipAddMotorista(state.check)
        }
    }

    private fun handleListPassag(passagList: List<String>) {
        viewList(passagList)
    }

    private fun handleCheckDeleteColabPassag(check: Boolean) {
        if(check) {
            viewModel.recoverListPassag()
            return
        }
        showGenericAlertDialog(getString(R.string.texto_failure_delete, "PASSAGEIRO"), requireContext())
    }

    private fun handleCheckStatusMovEquipAddPassag(check: Boolean) {
        if(check) {
            fragmentAttachListenerProprio?.goMatricColab()
            return
        }
        showGenericAlertDialog(getString(R.string.texto_failure_app), requireContext())
    }


    private fun handleCheckStatusMovEquipAddMotorista(check: Boolean) {
        if(check) {
            fragmentAttachListenerProprio?.goMatricColab()
            return
        }
        showGenericAlertDialog(getString(R.string.texto_failure_app), requireContext())
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

    private fun showMessage(pos: Int){
        AlertDialog.Builder(requireContext())
            .setMessage("DESEJA EXCLUIR O PASSAGEIRO?")
            .setPositiveButton("SIM") { _, _ ->
                viewModel.deletePassag(pos)
            }
            .setNegativeButton("NÃO", null)
            .create()
            .show()
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