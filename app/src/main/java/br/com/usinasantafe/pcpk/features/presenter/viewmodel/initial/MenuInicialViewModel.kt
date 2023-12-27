package br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.CheckAcessApont
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuInicialViewModel @Inject constructor(
    private val checkAcessApont: CheckAcessApont,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<MenuInicialFragmentState>()
    val uiLiveData: LiveData<MenuInicialFragmentState> = _uiLiveData

    private fun checkAccess(acessApont: Boolean){
        _uiLiveData.value = MenuInicialFragmentState.HasAcessApont(acessApont)
    }

    fun checkAccessApont() = viewModelScope.launch {
        checkAccess(checkAcessApont())
    }

}

sealed class MenuInicialFragmentState {
    data class HasAcessApont(val hasAcessApont: Boolean) : MenuInicialFragmentState()
}