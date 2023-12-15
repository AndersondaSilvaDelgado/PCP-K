package br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.CheckPasswordConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SenhaViewModel @Inject constructor (
    private val checkPasswordConfig: CheckPasswordConfig
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<SenhaFragmentState>()
    val uiLiveData: LiveData<SenhaFragmentState> = _uiLiveData

    fun verificarSenha(senha: String) = viewModelScope.launch {
        if(checkPasswordConfig(senha)){
            _uiLiveData.value = SenhaFragmentState.Success
        } else {
            _uiLiveData.value = SenhaFragmentState.Error
        }
    }

}

sealed class SenhaFragmentState  {
    object Success : SenhaFragmentState()
    object Error : SenhaFragmentState()
}