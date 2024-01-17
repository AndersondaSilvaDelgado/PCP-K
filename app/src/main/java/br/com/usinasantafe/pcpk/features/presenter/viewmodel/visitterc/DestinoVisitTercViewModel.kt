package br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetDestinoProprio
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetDestinoVisitTerc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinoVisitTercViewModel @Inject constructor(
    private val setDestinoVisitTerc: SetDestinoVisitTerc,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<DestinoVisitTercFragmentState>()
    val uiLiveData: LiveData<DestinoVisitTercFragmentState> = _uiLiveData

    private fun checkSetDestino(check: Boolean) {
        _uiLiveData.value = DestinoVisitTercFragmentState.CheckSetDestino(check)
    }

    fun setDestino(destino: String) = viewModelScope.launch {
        checkSetDestino(setDestinoVisitTerc(destino))
    }

}

sealed class DestinoVisitTercFragmentState {
    data class CheckSetDestino(val check: Boolean) : DestinoVisitTercFragmentState()
}