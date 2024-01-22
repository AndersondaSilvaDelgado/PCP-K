package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

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
import br.com.usinasantafe.pcpk.databinding.FragmentDetalheMovEquipResidenciaBinding
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipResidenciaModel
import br.com.usinasantafe.pcpk.features.presenter.view.residencia.FragmentAttachListenerResidencia
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.DetalheMovEquipResidenciaFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.DetalheMovEquipResidenciaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalheMovEquipResidenciaFragment : BaseFragment<FragmentDetalheMovEquipResidenciaBinding>(
    R.layout.fragment_detalhe_mov_equip_residencia,
    FragmentDetalheMovEquipResidenciaBinding::bind
) {

    private val viewModel: DetalheMovEquipResidenciaViewModel by viewModels()
    private var fragmentAttachListenerResidencia: FragmentAttachListenerResidencia? = null
    private var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.pos = fragmentAttachListenerResidencia?.getPos()!!
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
                fragmentAttachListenerResidencia?.goMovResidenciaStartedList()
            }
        }

    }

    private fun showMessage(){
        showGenericAlertDialogCheck("DESEJA REALMENTE FECHAR O MOVIMENTO?", requireContext()) {
            viewModel.closeMov(pos)
        }
    }

    private fun handleStateChange(state: DetalheMovEquipResidenciaFragmentState){
        when(state){
            is DetalheMovEquipResidenciaFragmentState.CheckMov -> handleCloseMov(state.check)
            is DetalheMovEquipResidenciaFragmentState.RecoverDetalhe -> handleDetalhe(state.detalhe)
        }
    }

    private fun handleCloseMov(check: Boolean) {
        if (check) {
            fragmentAttachListenerResidencia?.goMovResidenciaStartedList()
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_failure_app,
            ), requireContext()
        )
    }

    private fun handleDetalhe(detalhe: DetalheMovEquipResidenciaModel) {

        val detalhes = listOf(
            detalhe.dthr,
            detalhe.tipoMov,
            detalhe.veiculo,
            detalhe.placa,
            detalhe.motorista,
            detalhe.observ
        )

        val listAdapter = CustomAdapter(detalhes).apply {
            onItemClick = { _, position ->
                when(position){
                    2 -> fragmentAttachListenerResidencia?.goVeiculo(FlowApp.CHANGE, pos)
                    3 -> fragmentAttachListenerResidencia?.goPlaca(FlowApp.CHANGE, pos)
                    4 -> fragmentAttachListenerResidencia?.goMotorista(FlowApp.CHANGE, pos)
                    5 -> fragmentAttachListenerResidencia?.goObserv(null, FlowApp.CHANGE, pos)
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
        if (context is FragmentAttachListenerResidencia) {
            fragmentAttachListenerResidencia = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerResidencia = null
    }

}