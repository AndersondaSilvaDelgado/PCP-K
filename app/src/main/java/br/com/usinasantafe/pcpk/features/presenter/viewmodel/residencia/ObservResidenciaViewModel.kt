package br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetObservResidencia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObservResidenciaViewModel @Inject constructor(
    private val setObservResidencia: SetObservResidencia,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<ObservResidenciaFragmentState>()
    val uiLiveData: LiveData<ObservResidenciaFragmentState> = _uiLiveData

    private fun checkSetObserv(check: Boolean) {
        _uiLiveData.value = ObservResidenciaFragmentState.CheckSetObserv(check)
    }

    fun setObserv(observ: String?, pos: Int, typeMov: TypeMov?, flowApp: FlowApp) = viewModelScope.launch {
        checkSetObserv(setObservResidencia(observ, typeMov, pos, flowApp))
    }

}

sealed class ObservResidenciaFragmentState {
    data class CheckSetObserv(val check: Boolean) : ObservResidenciaFragmentState()
}