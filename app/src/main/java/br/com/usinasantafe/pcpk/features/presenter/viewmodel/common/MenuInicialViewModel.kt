package br.com.usinasantafe.pcpk.features.presenter.viewmodel.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.usinasantafe.cmm.common.utils.ResultUpdateDatabase
import br.com.usinasantafe.cmm.common.utils.StatusSend
import br.com.usinasantafe.cmm.common.utils.StatusUpdate
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.StartApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuInicialViewModel @Inject constructor(
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<MenuInicialFragmentState>()
    val uiLiveData: LiveData<MenuInicialFragmentState> = _uiLiveData

}

sealed class MenuInicialFragmentState {
    data class HasConfig(val hasConfig: Boolean) : MenuInicialFragmentState()
    data class FeedbackLoadingDataBase(val statusUpdateDataBase: StatusUpdate) :
        MenuInicialFragmentState()
    data class GetStatusSend(val statusSend: StatusSend) : MenuInicialFragmentState()
    data class SetResultUpdate(val resultUpdateDatabase: ResultUpdateDatabase) : MenuInicialFragmentState()
}