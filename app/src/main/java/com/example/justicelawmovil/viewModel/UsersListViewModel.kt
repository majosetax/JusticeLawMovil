package com.example.justicelawmovil.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.justicelawmovil.data.UsuarioListRepository
//import com.example.justicelawmovil.data.UsuarioListRepositoryInterface
//import com.example.justicelawmovil.model.UsersListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
//
//class UsersListViewModel (
//    private val repository: UsuarioListRepositoryInterface = UsuarioListRepository()
//): ViewModel() {
//
//    private val _userList = MutableStateFlow<UsersListModel?>(null)
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    private val _isLoading = MutableStateFlow<Boolean>(true)
//
//    val userList: StateFlow<UsersListModel?> get() = _userList.asStateFlow()
//    val errorMessage: StateFlow<String?> get() = _errorMessage.asStateFlow()
//    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()
//
//    fun obtenerUsuarios(){
//        viewModelScope.launch {
//            _isLoading.value = true
//            val response = repository.obtenerUsuarios(0,23)
//            if(response.isSuccessful){
//                val body = response.body()
//                if(body != null){
//                    Log.d("success", "$body?,size")
//                    _isLoading.value = false
//                    _userList.value = body
//                }
//                else{
//                    val error = response.body()
//                    if(error != null) {
//                        Log.d("User list error", error.toString())
//                        _isLoading.value = false
//                        _errorMessage.value = error.toString()
//                    }
//                    }
//                }
//            }
//        }
//
//
//    }
