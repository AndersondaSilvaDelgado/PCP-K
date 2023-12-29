package br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.RecoverBase
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverConfig
import br.com.usinasantafe.pcpk.features.presenter.model.ConfigModelOutput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuApontListViewModel @Inject constructor(
    private val recoverBase: RecoverBase,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<MenuApontListFragmentState>()
    val uiLiveData: LiveData<MenuApontListFragmentState> = _uiLiveData

    private fun setConfig(config: ConfigModelOutput) {
        _uiLiveData.value = MenuApontListFragmentState.RecoverConfig(config)
    }

    fun recoverDataConfig() = viewModelScope.launch {
        setConfig(recoverBase())
    }

}

sealed class MenuApontListFragmentState {
    data class RecoverConfig(val config: ConfigModelOutput) : MenuApontListFragmentState()
    data class HasCloseMov(val hasCloseMov: Boolean) : MenuApontListFragmentState()
}