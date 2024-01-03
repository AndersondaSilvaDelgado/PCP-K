package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.GetTipoMov
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetDestinoProprio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinoProprioViewModel @Inject constructor(
    private val setDestinoProprio: SetDestinoProprio,
    private val getTipoMov: GetTipoMov,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<DestinoProprioFragmentState>()
    val uiLiveData: LiveData<DestinoProprioFragmentState> = _uiLiveData

    private fun checkSetDestino(check: Boolean) {
        _uiLiveData.value = DestinoProprioFragmentState.CheckSetDestino(check)
    }

    fun setDestino(destino: String) = viewModelScope.launch {
        checkSetDestino(setDestinoProprio(destino))
    }

    fun checkNextFragment() = viewModelScope.launch {
        if(getTipoMov() == TypeMov.INPUT){
            _uiLiveData.value = DestinoProprioFragmentState.GoFragmentObserv
        } else {
            _uiLiveData.value = DestinoProprioFragmentState.GoFragmentNotaFiscal
        }
    }
}

sealed class DestinoProprioFragmentState {
    data class CheckSetDestino(val check: Boolean) : DestinoProprioFragmentState()
    object GoFragmentObserv : DestinoProprioFragmentState()
    object GoFragmentNotaFiscal : DestinoProprioFragmentState()
}