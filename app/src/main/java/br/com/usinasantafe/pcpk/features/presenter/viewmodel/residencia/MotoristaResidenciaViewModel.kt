package br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetMotoristaResidencia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MotoristaResidenciaViewModel @Inject constructor(
    private val setMotoristaResidencia: SetMotoristaResidencia,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<MotoristaResidenciaFragmentState>()
    val uiLiveData: LiveData<MotoristaResidenciaFragmentState> = _uiLiveData

    private fun checkSetMotorista(check: Boolean) {
        _uiLiveData.value = MotoristaResidenciaFragmentState.CheckSetMotorista(check)
    }

    fun setMotorista(veiculo: String, flowApp: FlowApp, pos: Int) = viewModelScope.launch {
        checkSetMotorista(setMotoristaResidencia(veiculo, flowApp, pos))
    }

}

sealed class MotoristaResidenciaFragmentState {
    data class CheckSetMotorista(val check: Boolean) : MotoristaResidenciaFragmentState()
}