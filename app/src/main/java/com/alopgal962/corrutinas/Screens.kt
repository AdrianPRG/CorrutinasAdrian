package com.alopgal962.corrutinas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alopgal962.corrutinas.VM.VMCorrutina

//Funcion que contendra la screen principal
@Composable
fun Pantalla1(ViewModel:VMCorrutina){
    //Columna con dos botones
    // El primero llama al viewmodel para cambiar el color y recibe por parametros el color correspondiente
    //El segundo tiene que ver con el delay y con el Circularprogress
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(onClick = { ViewModel.cambiacolor() }, colors = ButtonDefaults.buttonColors(containerColor = (ViewModel.coloractual()))) {
            Text(text = "Cambiar Color")
        }
        //Funcion que muestra el circularprogress o el text
        Circular(viewmodel = ViewModel)
        Button(onClick = { ViewModel.fetchData() }, Modifier.padding(top = 30.dp)) {
            Text(text = "LLamar a API")
        }
    }
}

/*
Funcion composable que, dependiendo de si esta cargando o no,
 muestra el circularProgres, y si no, muestra el texto del numero de veces pulsado
 */
@Composable
fun Circular(viewmodel:VMCorrutina){
    if (viewmodel.loadingOno()){
        CircularProgressIndicator(Modifier.padding(top = 30.dp))
    }
    else  Text(text = viewmodel.textoactual(), Modifier.padding(top = 30.dp))
}
