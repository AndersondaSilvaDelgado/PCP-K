package br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetObservVisitTerc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObservVisitTercViewModel @Inject constructor(
    private val setObservVisitTerc: SetObservVisitTerc,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<ObservVisitTercFragmentState>()
    val uiLiveData: LiveData<ObservVisitTercFragmentState> = _uiLiveData

    private fun checkSetObserv(check: Boolean) {
        _uiLiveData.value = ObservVisitTercFragmentState.CheckSetObserv(check)
    }

    fun setObserv(observ: String?, pos: Int?, typeMov: TypeMov) = viewModelScope.launch {
        checkSetObserv(setObservVisitTerc(observ = observ, pos = pos, typeMov = typeMov))
    }

}

sealed class ObservVisitTercFragmentState {
    data class CheckSetObserv(val check: Boolean) : ObservVisitTercFragmentState()
}