package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

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
import br.com.usinasantafe.pcpk.databinding.FragmentPassagVisitTercListBinding
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
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
    private lateinit var typeAddOcupante: TypeAddOcupante
    private var pos: Int = 0

    companion object {
        const val KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC_PASSAG_LIST =
            "key_type_ocupante_veic_visit_terc_passag_list";
        const val KEY_POS_PASSAG_VISIT_TERC = "key_pos_passag_visit_terc";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeAddOcupante =
            TypeAddOcupante.values()[arguments?.getInt(KEY_TYPE_OCUPANTE_VEIC_VISIT_TERC_PASSAG_LIST)!!]
        pos = arguments?.getInt(KEY_POS_PASSAG_VISIT_TERC)!!
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
        viewModel.recoverListPassag(typeAddOcupante, pos)
    }

    private fun setListener() {
        with(binding) {
            buttonInserirPassageiro.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goCPFVisitTerc(typeAddOcupante)
            }
            buttonOkPassageiro.setOnClickListener {
                when (typeAddOcupante) {
                    TypeAddOcupante.ADDMOTORISTA,
                    TypeAddOcupante.ADDPASSAGEIRO -> fragmentAttachListenerVisitTerc?.goDestino(FlowApp.ADD)
                    TypeAddOcupante.CHANGEMOTORISTA,
                    TypeAddOcupante.CHANGEPASSAGEIRO -> fragmentAttachListenerVisitTerc?.goDetalhe(pos)
                }
            }
            buttonCancPassageiro.setOnClickListener {
                when (typeAddOcupante) {
                    TypeAddOcupante.ADDMOTORISTA,
                    TypeAddOcupante.ADDPASSAGEIRO -> fragmentAttachListenerVisitTerc?.goCPFVisitTerc(TypeAddOcupante.ADDMOTORISTA)
                    TypeAddOcupante.CHANGEMOTORISTA,
                    TypeAddOcupante.CHANGEPASSAGEIRO -> fragmentAttachListenerVisitTerc?.goDetalhe(pos)
                }
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
            viewModel.recoverListPassag(typeAddOcupante, pos)
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

    private fun showMessage(posList: Int){
        showGenericAlertDialogCheck("DESEJA EXCLUIR O PASSAGEIRO?", requireContext()) {
            viewModel.deletePassag(posList, typeAddOcupante, pos)
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