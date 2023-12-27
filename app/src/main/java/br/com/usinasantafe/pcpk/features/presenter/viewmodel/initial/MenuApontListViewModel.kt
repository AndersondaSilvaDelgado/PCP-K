package br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuApontListViewModel @Inject constructor(
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<MenuApontListFragmentState>()
    val uiLiveData: LiveData<MenuApontListFragmentState> = _uiLiveData

}

sealed class MenuApontListFragmentState {
    data class HasCloseMov(val hasCloseMov: Boolean) : MenuApontListFragmentState()
}