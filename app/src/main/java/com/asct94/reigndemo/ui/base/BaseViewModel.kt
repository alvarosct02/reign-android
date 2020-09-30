package com.asct94.reigndemo.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    val scrollY = MutableLiveData(0)

    val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            it.value = false
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}
