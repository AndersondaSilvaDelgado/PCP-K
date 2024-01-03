package br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.RecoverHeader
import br.com.usinasantafe.pcpk.features.presenter.model.HeaderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuApontListViewModel @Inject constructor(
    private val recoverHeader: RecoverHeader,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<MenuApontListFragmentState>()
    val uiLiveData: LiveData<MenuApontListFragmentState> = _uiLiveData

    private fun setHeader(header: HeaderModel) {
        _uiLiveData.value = MenuApontListFragmentState.RecoverHeader(header)
    }

    fun recoverDataConfig() = viewModelScope.launch {
        setHeader(recoverHeader())
    }

}

sealed class MenuApontListFragmentState {
    data class RecoverHeader(val header: HeaderModel) : MenuApontListFragmentState()
    data class HasCloseMov(val hasCloseMov: Boolean) : MenuApontListFragmentState()
}