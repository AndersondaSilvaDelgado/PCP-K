package br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetVeiculoResidencia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VeiculoResidenciaViewModel @Inject constructor(
    private val setVeiculoResidencia: SetVeiculoResidencia,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<VeiculoResidenciaFragmentState>()
    val uiLiveData: LiveData<VeiculoResidenciaFragmentState> = _uiLiveData

    private fun checkSetVeiculo(check: Boolean) {
        _uiLiveData.value = VeiculoResidenciaFragmentState.CheckSetVeiculo(check)
    }

    fun setVeiculo(veiculo: String) = viewModelScope.launch {
        checkSetVeiculo(setVeiculoResidencia(veiculo))
    }

}

sealed class VeiculoResidenciaFragmentState {
    data class CheckSetVeiculo(val check: Boolean) : VeiculoResidenciaFragmentState()
}