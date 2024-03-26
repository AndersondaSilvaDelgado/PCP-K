package br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.common.RecoverHeader
import br.com.usinasantafe.pcpk.features.domain.usecases.common.CloseSendAllMov
import br.com.usinasantafe.pcpk.features.presenter.model.HeaderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuApontListViewModel @Inject constructor(
    private val recoverHeader: RecoverHeader,
    private val closeSendAllMov: CloseSendAllMov,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<MenuApontListFragmentState>()
    val uiLiveData: LiveData<MenuApontListFragmentState> = _uiLiveData

    private fun setHeader(header: HeaderModel) {
        _uiLiveData.value = MenuApontListFragmentState.RecoverHeader(header)
    }

    private fun checkClosedAllMov(state: Boolean) {
        _uiLiveData.value = MenuApontListFragmentState.CheckCloseAllMov(state)
    }

    fun recoverDataConfig() = viewModelScope.launch {
        setHeader(recoverHeader())
    }

    fun closedAllMov() = viewModelScope.launch {
        checkClosedAllMov(closeSendAllMov())
    }

}

sealed class MenuApontListFragmentState {
    data class RecoverHeader(val header: HeaderModel) : MenuApontListFragmentState()
    data class CheckCloseAllMov(val state: Boolean) : MenuApontListFragmentState()
}