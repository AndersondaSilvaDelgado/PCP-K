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
import br.com.usinasantafe.pcpk.databinding.FragmentDetalheMovEquipVisitTercBinding
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipVisitTercModel
import br.com.usinasantafe.pcpk.features.presenter.view.visitterc.FragmentAttachListenerVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.DetalheMovEquipVisitTercFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.DetalheMovEquipVisitTercViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalheMovEquipVisitTercFragment : BaseFragment<FragmentDetalheMovEquipVisitTercBinding>(
    R.layout.fragment_detalhe_mov_equip_visit_terc,
    FragmentDetalheMovEquipVisitTercBinding::bind
) {

    private val viewModel: DetalheMovEquipVisitTercViewModel by viewModels()
    private var fragmentAttachListenerVisitTerc: FragmentAttachListenerVisitTerc? = null
    private var pos: Int = 0

    companion object {
        const val KEY_POS_DETALHE_VISIT_TERC = "key_pos_detalhe_visit_terc";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pos = arguments?.getInt(KEY_POS_DETALHE_VISIT_TERC)!!
        observeState()
        setListener()
        startEvents()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            state -> handleStateChange(state)
        }
    }

    private fun startEvents() {
        viewModel.recoverDataDetalhe(pos)
    }

    private fun setListener() {
        with(binding) {
            buttonFecharMov.setOnClickListener {
                showMessage()
            }
            buttonRetDetalheMov.setOnClickListener {
                fragmentAttachListenerVisitTerc?.goMovVisitTercListEmpty()
            }
        }

    }

    private fun showMessage(){
        showGenericAlertDialogCheck("DESEJA REALMENTE FECHAR O MOVIMENTO?", requireContext()) {
            viewModel.closeMov(pos)
        }
    }

    private fun handleStateChange(state: DetalheMovEquipVisitTercFragmentState){
        when(state){
            is DetalheMovEquipVisitTercFragmentState.CheckMov -> handleCloseMov(state.check)
            is DetalheMovEquipVisitTercFragmentState.RecoverDetalhe -> handleDetalhe(state.detalhe)
        }
    }

    private fun handleCloseMov(check: Boolean) {
        if (check) {
            fragmentAttachListenerVisitTerc?.goMovVisitTercListEmpty()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_failure_app,
            ), requireContext()
        )
    }

    private fun handleDetalhe(detalhe: DetalheMovEquipVisitTercModel) {

        val detalhes = listOf(
            detalhe.dthr,
            detalhe.tipoMov,
            detalhe.veiculo,
            detalhe.placa,
            detalhe.tipoVisitTerc,
            detalhe.motorista,
            detalhe.passageiro,
            detalhe.destino,
            detalhe.observ
        )

        val listAdapter = CustomAdapter(detalhes).apply {
            onItemClick = { _, position ->
                when(position){
                    2 -> fragmentAttachListenerVisitTerc?.goVeiculo(FlowApp.CHANGE, pos)
                    3 -> fragmentAttachListenerVisitTerc?.goPlaca(FlowApp.CHANGE, pos)
//                    4 -> fragmentAttachListenerProprio?.goPassagList(TypeAddOcupante.CHANGEPASSAGEIRO, pos)
//                    5 -> fragmentAttachListenerProprio?.goDestino(FlowApp.CHANGE, pos)
//                    6 -> fragmentAttachListenerProprio?.goVeicSegList(TypeAddEquip.ADDVEICULOSEG, pos)
//                    7 -> fragmentAttachListenerProprio?.goNotaFiscal(FlowApp.CHANGE, pos)
//                    8 -> fragmentAttachListenerProprio?.goObserv(FlowApp.CHANGE, pos)
                }
            }
        }
        binding.listViewDetalheMov.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
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