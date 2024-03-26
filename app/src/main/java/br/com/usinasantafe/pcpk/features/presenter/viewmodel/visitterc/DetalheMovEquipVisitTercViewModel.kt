package br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.visitterc.RecoverDetalheMovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.domain.usecases.visitterc.CloseSendMovVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.model.DetalheMovEquipVisitTercModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalheMovEquipVisitTercViewModel @Inject constructor (
    private val recoverDetalheMovEquipVisitTerc: RecoverDetalheMovEquipVisitTerc,
    private val closeSendMovVisitTerc: CloseSendMovVisitTerc,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<DetalheMovEquipVisitTercFragmentState>()
    val uiLiveData: LiveData<DetalheMovEquipVisitTercFragmentState> = _uiLiveData

    private fun setDetalhe(detalhe: DetalheMovEquipVisitTercModel) {
        _uiLiveData.value = DetalheMovEquipVisitTercFragmentState.RecoverDetalhe(detalhe)
    }

    private fun checkCloseMov(check: Boolean) {
        _uiLiveData.value = DetalheMovEquipVisitTercFragmentState.CheckMov(check)
    }

    fun recoverDataDetalhe(pos: Int) = viewModelScope.launch {
        setDetalhe(recoverDetalheMovEquipVisitTerc(pos))
    }

    fun closeMov(pos: Int) = viewModelScope.launch {
        checkCloseMov(closeSendMovVisitTerc(pos))
    }

}

sealed class DetalheMovEquipVisitTercFragmentState {
    data class RecoverDetalhe(val detalhe: DetalheMovEquipVisitTercModel) : DetalheMovEquipVisitTercFragmentState()
    data class CheckMov(val check: Boolean) : DetalheMovEquipVisitTercFragmentState()
}