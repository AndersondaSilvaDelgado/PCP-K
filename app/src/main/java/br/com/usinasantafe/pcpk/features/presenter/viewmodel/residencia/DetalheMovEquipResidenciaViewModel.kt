package br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.RecoverDetalheMovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetStatusSendCloseMovResidencia
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipResidenciaModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetalheMovEquipResidenciaViewModel @Inject constructor (
    private val recoverDetalheMovEquipResidencia: RecoverDetalheMovEquipResidencia,
    private val setStatusSendCloseMovResidencia: SetStatusSendCloseMovResidencia,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<DetalheMovEquipResidenciaFragmentState>()
    val uiLiveData: LiveData<DetalheMovEquipResidenciaFragmentState> = _uiLiveData

    private fun setDetalhe(detalhe: DetalheMovEquipResidenciaModel) {
        _uiLiveData.value = DetalheMovEquipResidenciaFragmentState.RecoverDetalhe(detalhe)
    }

    private fun checkCloseMov(check: Boolean) {
        _uiLiveData.value = DetalheMovEquipResidenciaFragmentState.CheckMov(check)
    }

    fun recoverDataDetalhe(pos: Int) = viewModelScope.launch {
        setDetalhe(recoverDetalheMovEquipResidencia(pos))
    }

    fun closeMov(pos: Int) = viewModelScope.launch {
        checkCloseMov(setStatusSendCloseMovResidencia(pos))
    }

}

sealed class DetalheMovEquipResidenciaFragmentState {
    data class RecoverDetalhe(val detalhe: DetalheMovEquipResidenciaModel) : DetalheMovEquipResidenciaFragmentState()
    data class CheckMov(val check: Boolean) : DetalheMovEquipResidenciaFragmentState()
}