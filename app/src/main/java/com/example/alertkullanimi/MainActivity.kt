package com.example.alertkullanimi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alertkullanimi.ui.theme.AlertKullanimiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlertKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {
    val acilisKontorl = remember { mutableStateOf(false) }
    val acilisKontorlOzel = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { acilisKontorl.value = true }) {
            Text(text = "Varsayılan")
        }
        if (acilisKontorl.value) {
            AlertDialog(
                onDismissRequest = { acilisKontorl.value = false },
                title = { Text(text = "Başlık") },
                text = {
                    Text(
                        text = "Mesaj İçeriği"
                    )
                }, confirmButton = {
                    Text(
                        text = "Tamam",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            Log.e("Alert", "Tamam Seçildi")
                            acilisKontorl.value = false
                        })
                }, dismissButton = {
                    Text(
                        text = "İptal",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            Log.e("Alert", "İptal Seçildi")
                            acilisKontorl.value = false

                        })
                })
        }
        Button(onClick = { acilisKontorlOzel.value = true }) {
            Text(text = "Özel")
        }
        if (acilisKontorlOzel.value) {
            AlertDialog(
                onDismissRequest = { acilisKontorlOzel.value = false },
                title = { Text(text = "Başlık", color = Color.White) },
                text = {
                    TextField(value = tf.value, onValueChange = { tf.value = it }, label = {
                        Text(
                            text = "Veri"
                        )
                    })
                }, confirmButton = {
                    Text(
                        text = "Tamam",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            Log.e("Alert", "Tamam Seçildi : ${tf.value}")
                            acilisKontorlOzel.value = false
                            tf.value = ""
                        }.padding(all=10.dp))
                }, dismissButton = {
                    Text(
                        text = "İptal",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(all=10.dp).clickable {
                            Log.e("Alert", "İptal Seçildi")
                            acilisKontorlOzel.value = false
                            tf.value = ""
                        })
                }, backgroundColor = Color.LightGray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlertKullanimiTheme {
        Sayfa()
    }
}