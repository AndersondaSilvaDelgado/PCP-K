package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.dialog.GenericDialogProgressBar
import br.com.usinasantafe.pcpk.common.extension.onBackPressed
import br.com.usinasantafe.pcpk.common.extension.setListenerButtonsGeneric
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.extension.showToast
import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import br.com.usinasantafe.pcpk.common.utils.StatusUpdate
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
import br.com.usinasantafe.pcpk.databinding.FragmentVeiculoProprioBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.VeiculoProprioFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.VeiculoProprioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VeiculoProprioFragment : BaseFragment<FragmentVeiculoProprioBinding>(
    R.layout.fragment_veiculo_proprio,
    FragmentVeiculoProprioBinding::bind
) {

    private val viewModel: VeiculoProprioViewModel by viewModels()
    private var genericDialogProgressBar: GenericDialogProgressBar? = null
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null
    private lateinit var describeUpdate: String
    private lateinit var typeAddEquip : TypeAddEquip
    private var pos: Int = 0

    companion object {
        const val KEY_TYPE_EQUIP_PROPRIO = "key_type_equip_proprio";
        const val KEY_POS_EQUIP_PROPRIO = "key_type_equip_proprio";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeAddEquip = TypeAddEquip.values()[arguments?.getInt(KEY_TYPE_EQUIP_PROPRIO)!!]
        pos = arguments?.getInt(KEY_POS_EQUIP_PROPRIO)!!
        observeState()
        setListener()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(viewLifecycleOwner) { state ->
            handleStateChange(state)
        }
    }

    private fun setListener() {
        with(binding) {
            setListenerButtonsGeneric(layoutBotoes, editTextPadrao)
            layoutBotoes.buttonOkPadrao.setOnClickListener {
                if (editTextPadrao.text.isEmpty()) {
                    showGenericAlertDialog(
                        getString(
                            R.string.texto_campo_vazio,
                            "NUMERO DO EQUIPAMENTO"
                        ), requireContext()
                    )
                    return@setOnClickListener
                }
                viewModel.checkNroVeiculo(editTextPadrao.text.toString())
            }
            layoutBotoes.buttonAtualPadrao.setOnClickListener {
                viewModel.updateDataEquip()
            }
        }
    }

    private fun handleStateChange(state: VeiculoProprioFragmentState) {
        when (state) {
            is VeiculoProprioFragmentState.CheckEquip -> handleCheckNroEquip(state.checkNroEquip)
            is VeiculoProprioFragmentState.CheckSetVeicEquipSeg -> handleCheckSetNroEquip(state.checkSetNroVeiculo)
            is VeiculoProprioFragmentState.FeedbackUpdate -> handleFeedbackUpdate(state.statusUpdate)
            is VeiculoProprioFragmentState.SetResultUpdate -> handleSetResultUpdate(state.resultUpdateDatabase)
        }
    }

    private fun handleCheckNroEquip(checkMatric: Boolean) {
        if (checkMatric) {
            viewModel.setNroVeiculo(binding.editTextPadrao.text.toString(), typeAddEquip, pos)
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_dado_invalido_com_atual,
                "NUMERO DO EQUIPAMENTO"
            ), requireContext()
        )
    }

    private fun handleCheckSetNroEquip(checkSetMatricColab: Boolean) {
        if (checkSetMatricColab) {
            when(typeAddEquip) {
                TypeAddEquip.ADDVEICULO,
                TypeAddEquip.ADDVEICULOSEG -> fragmentAttachListenerProprio?.goVeicSegList(typeAddEquip)
                TypeAddEquip.CHANGEVEICULO,
                TypeAddEquip.CHANGEVEICULOSEG -> fragmentAttachListenerProprio?.goDetalhe(pos)
            }
            return
        }
        showGenericAlertDialog(
            getString(
                R.string.texto_falha_insercao_campo,
                "NUMERO DO EQUIPAMENTO",
            ), requireContext()
        )
    }

    private fun handleSetResultUpdate(resultUpdateDatabase: ResultUpdateDatabase?) {
        resultUpdateDatabase?.let {
            if (genericDialogProgressBar == null) {
                showProgressBar()
            }
            describeUpdate = resultUpdateDatabase.describe
            genericDialogProgressBar!!.setValue(resultUpdateDatabase)
        }
    }

    private fun handleFeedbackUpdate(statusUpdate: StatusUpdate) {
        when (statusUpdate) {
            StatusUpdate.UPDATED -> {
                hideProgressBar()
                showToast(
                    getString(R.string.texto_msg_atualizacao, "EQUIPAMENTO"),
                    requireContext()
                )
            }
            StatusUpdate.FAILURE -> {
                hideProgressBar()
                showToast(
                    getString(R.string.texto_update_failure, describeUpdate),
                    requireContext()
                )
            }
        }
    }

    private fun showProgressBar() {
        genericDialogProgressBar = GenericDialogProgressBar(requireContext())
        genericDialogProgressBar!!.show()
        genericDialogProgressBar!!.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
        )
    }

    private fun hideProgressBar() {
        if (genericDialogProgressBar != null) {
            genericDialogProgressBar!!.cancel()
        }
        genericDialogProgressBar = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerProprio) {
            fragmentAttachListenerProprio = context
        }
        onBackPressed {
            fragmentAttachListenerProprio?.goMovProprioList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}