package com.jchunga.salesapp.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VisitViewModel @Inject constructor(

): ViewModel(){

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri : LiveData<Uri?> = _imageUri

    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }


}