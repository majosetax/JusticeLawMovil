package com.example.justicelawmovil.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.justicelawmovil.data.UsuarioDetailsRepository
//import com.example.justicelawmovil.data.UsuarioDetailsRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//class UserDetailsViewModel (private val repository: UsuarioDetailsRepositoryInterface = UsuarioDetailsRepository()): ViewModel(){
//    private val _userDetails = MutableStateFlow<UsersDetailModel?>(null)
//    private val _isLoading = MutableStateFlow<Boolean>(true)
//    private val _getError = MutableStateFlow<Boolean>(false)
//
//    val userDetails: StateFlow<UsersDetailModel?> get() = _userDetails.asStateFlow()
//    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()
//    val getError: StateFlow<Boolean> get() = _getError.asStateFlow()
//
//    fun fetchDetails(name: String){
//
//        viewModelScope.launch {
// _isLoading.value = true
//            val result = repository.obtenerUsuarioPorId(name)
//            val error = result.errorBody()
//            val data = result.body()
//            if(error!= null ||  !result.isSuccessful){
//                Log.d("Get data","Get data")
//                _isLoading.value = false
//                _userDetails.value = data
//            } else{
//                Log.d("Got nothing","Got data")
//                _isLoading.value = false
//            }
//        }
//    }
//}