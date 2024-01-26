package com.alopgal962.corrutinas.VM

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class VMCorrutina:ViewModel() {

    //Si es verdadero es rojo, si es falso es azul
    private var _quecolor by mutableStateOf(false)

    //Numero de veces que se ha pulsado
    private var _numapis by mutableStateOf(0)

    //Texto que muestra el numero de veces llamada la api
    private var _texto by mutableStateOf("")

    //booleano que cambia segun se ha realizado una llamada o no
    private var _isloading by mutableStateOf(false)

    //Cambia el booleano para que ahora se devuelva el otro color
    fun cambiacolor(){
        _quecolor=!_quecolor
    }

    //Se crea una variable color, que sera rojo o azul segun el estado de la variable 'quecolor'
    //Este se devuelve y el boton 1 recibe por parametros el color
    fun coloractual():Color{
        var color:Color
        when (_quecolor){
            true -> color = Color.Red
            false -> color = Color.Blue
        }
        return color
    }

    //Se devuelve el texto actual, no se ponen comprobaciones de si el numapi > 1
    //Ya que por defecto el texto esta vacio, asi que nada mas ya haya
    fun textoactual():String{
        return _texto
    }


    //Devuelve el booleano de si esta cargando o no
    fun loadingOno():Boolean{
        return _isloading
    }


    fun fetchData(){
        viewModelScope.launch {
            try {
                _isloading=true
                llamarApi()
            }catch (e:Exception){
                println("Error ${e.message}")
            } finally {
                _isloading = false
            }
        }
    }

    private suspend fun llamarApi(){
        val result = withContext(Dispatchers.IO){
            delay(5000)
            _numapis+=1
            "Respuesta de la API $_numapis"
        }
        _texto = result
    }



}