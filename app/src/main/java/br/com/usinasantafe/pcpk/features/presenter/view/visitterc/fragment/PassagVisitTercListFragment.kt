package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialogCheck
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.databinding.FragmentPassagColabListBinding
import br.com.usinasantafe.pcpk.databinding.FragmentPassagVisitTercListBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.PassagColabListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.PassagColabListViewModel
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.PassagVisitTercListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.PassagVisitTercListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassagVisitTercListFragment : BaseFragment<FragmentPassagVisitTercListBinding>(
    R.layout.fragment_passag_visit_terc_list,
    FragmentPassagVisitTercListBinding::bind,
) {

    private val viewModel: PassagVisitTercListViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null

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
                fragmentAttachListenerVisitTerc?.goCPFVisitTerc(TypeAddOcupante.ADDPASSAGEIRO)
            }
            buttonOkPassageiro.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goDestino()
            }
            buttonCancPassageiro.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goCPFVisitTerc(TypeAddOcupante.ADDMOTORISTA)
            }
        }
    }

    private fun handleStateChange(state: PassagVisitTercListFragmentState){
        when(state){
            is PassagVisitTercListFragmentState.ListVisitTercPassag -> handleListPassag(state.passagList)
            is PassagVisitTercListFragmentState.CheckDeleteVisitTercPassag -> handleCheckDeleteColabPassag(state.check)
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
        showGenericAlertDialogCheck("DESEJA EXCLUIR O PASSAGEIRO?", requireContext()) {
            viewModel.deletePassag(pos)
        }
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