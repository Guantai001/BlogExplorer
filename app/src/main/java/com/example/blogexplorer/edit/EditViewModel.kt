package com.example.blogexplorer.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogexplorer.api.RetrofitInstance
import com.example.blogexplorer.models.Post
import kotlinx.coroutines.launch

private const val TAG = "EditViewModel"
class EditViewModel : ViewModel() {
    private val _post: MutableLiveData<Post?> = MutableLiveData()
    val post: LiveData<Post?>
        get() = _post

    private val _currentStatus = MutableLiveData<ResultStatus>(ResultStatus.IDLE)
    val currentStatus: LiveData<ResultStatus>
        get() = _currentStatus

    private val _wasDeleteSuccessful = MutableLiveData<Boolean>(false)
    val wasDeleteSuccessful: LiveData<Boolean>
    get() =  _wasDeleteSuccessful
    fun updatePost(postId: Int, newPostData: Post) {
    viewModelScope.launch {
        try {
        _post.value = null
            _currentStatus.value = ResultStatus.WORKING
        val updatePost = RetrofitInstance.api.updatePost(postId,newPostData)
         Log.i(TAG,"update post $updatePost")
                 _post.value = updatePost
            _currentStatus.value = ResultStatus.SUCCESS
    }catch (e:Exception){
        _currentStatus.value = ResultStatus.ERROR
    }
    }
    }

    fun patchPost(postId: Int, title: String, body: String) {
        viewModelScope.launch {
            try {
            _post.value = null
                _currentStatus.value = ResultStatus.WORKING
            val patchPost= RetrofitInstance.api.patchPost(postId, mapOf("title" to title,"body" to body ))
             Log.i(TAG,"patched post $patchPost")
            _post.value= patchPost
                _currentStatus.value = ResultStatus.SUCCESS
        }catch (e:Exception){
                _currentStatus.value = ResultStatus.ERROR
        }
        }
    }

   fun deletePost(postId: Int) {
       viewModelScope.launch{
           try {
               _currentStatus.value = ResultStatus.WORKING
               RetrofitInstance.api.deletePost("1234AuthToken",postId)
               _post.value = null
               _wasDeleteSuccessful.value = true
               _currentStatus.value = ResultStatus.SUCCESS
           }catch (e:java.lang.Exception){
               _currentStatus.value = ResultStatus.ERROR
           }
       }
   }
}

