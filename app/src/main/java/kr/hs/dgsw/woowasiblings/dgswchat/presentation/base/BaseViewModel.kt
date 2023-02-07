package kr.hs.dgsw.woowasiblings.dgswchat.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val isLoading = MutableLiveData<Boolean>()
    fun getIsLoading(): LiveData<Boolean> = isLoading

    protected val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> get() = _errorMsg

}