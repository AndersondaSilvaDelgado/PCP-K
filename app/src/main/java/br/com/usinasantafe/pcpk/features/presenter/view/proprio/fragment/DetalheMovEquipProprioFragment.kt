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
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.databinding.FragmentDetalheMovEquipProprioBinding
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipProprioModel
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.DetalheMovEquipProprioFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.DetalheMovEquipProprioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalheMovEquipProprioFragment : BaseFragment<FragmentDetalheMovEquipProprioBinding>(
    R.layout.fragment_detalhe_mov_equip_proprio,
    FragmentDetalheMovEquipProprioBinding::bind
) {

    private val viewModel: DetalheMovEquipProprioViewModel by viewModels()
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null
    private var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.pos = fragmentAttachListenerProprio?.getPos()!!
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
                fragmentAttachListenerProprio?.goMovProprioList()
            }
        }

    }

    private fun showMessage(){
        showGenericAlertDialogCheck("DESEJA REALMENTE FECHAR O MOVIMENTO?", requireContext()) {
            viewModel.closeMov(pos)
        }
    }

    private fun handleStateChange(state: DetalheMovEquipProprioFragmentState){
        when(state){
            is DetalheMovEquipProprioFragmentState.RecoverDetalhe ->  handleDetalhe(state.detalhe)
            is DetalheMovEquipProprioFragmentState.CheckMov -> handleCloseMov(state.check)
        }
    }

    private fun handleCloseMov(check: Boolean) {
        if (check) {
            fragmentAttachListenerProprio?.goMovProprioList()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_failure_app,
            ), requireContext()
        )
    }

    private fun handleDetalhe(detalhe: DetalheMovEquipProprioModel) {

        val detalhes = listOf(
            detalhe.dthr,
            detalhe.tipoMov,
            detalhe.veiculo,
            detalhe.motorista,
            detalhe.passageiro,
            detalhe.destino,
            detalhe.veiculoSec,
            detalhe.notaFiscal,
            detalhe.observ
        )

        val listAdapter = CustomAdapter(detalhes).apply {
            onItemClick = { _, position ->
                when(position){
                    2 -> fragmentAttachListenerProprio?.goVeiculoProprio(TypeAddEquip.CHANGEVEICULO, pos)
                    3 -> fragmentAttachListenerProprio?.goMatricColab(TypeAddOcupante.CHANGEMOTORISTA, pos)
                    4 -> fragmentAttachListenerProprio?.goPassagList(TypeAddOcupante.CHANGEPASSAGEIRO, pos)
                    5 -> fragmentAttachListenerProprio?.goDestino(FlowApp.CHANGE, pos)
                    6 -> fragmentAttachListenerProprio?.goVeicSegList(TypeAddEquip.ADDVEICULOSEG, pos)
                    7 -> fragmentAttachListenerProprio?.goNotaFiscal(FlowApp.CHANGE, pos)
                    8 -> fragmentAttachListenerProprio?.goObserv(FlowApp.CHANGE, pos)
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
        if (context is FragmentAttachListenerProprio) {
            fragmentAttachListenerProprio = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}