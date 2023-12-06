package com.example.jetpackcompose

import android.content.res.Configuration
import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

private val messages: List<MyMessage> = listOf(
    MyMessage("Que es jetpack compose", "Jetpack Compose es el kit de herramientas moderno de Android para compilar IU nativas. Simplifica y acelera el desarrollo de la IU en Android. Haz que tu app cobre vida rápidamente con menos código, herramientas potentes y APIs intuitivas de Kotlin"),
    MyMessage("Para que sirve", "Jetpack Compose es el kit de herramientas moderno de Android para compilar IU nativas. Simplifica y acelera el desarrollo de la IU en Android"),
    MyMessage("Lenguajes de progracion", "El unico lenguaje de programacion en elque funciona jetpack compose es Kotlin"),
    MyMessage("Funcion en apps", "Jetcpack compose esta pensando para hacer aplicaciones web dandole diversas funciones interactivas al usario")

)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                MyMassages(messages)
            }

       }
    }
}

data class MyMessage(val title: String, val body: String)

@Composable
fun MyMassages(messages: List<MyMessage>){
    LazyColumn{
        items(messages){message ->
            MyComponent(message)
        }
    }
}

@Composable
fun MyComponent(message: MyMessage){
    Row(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .padding(start = 8.dp)) {
     MyImage()
     MyTexts(message)
    }
}

@Composable
fun MyImage() {
    Image(
        painterResource(R.drawable.ic_launcher_foreground),
        "Mi imagen de prueba",
        modifier= Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .size(64.dp)
    )
}

@Composable
fun MyTexts(message:MyMessage){
    var expanded by remember { mutableStateOf(false) }
    Column (modifier= Modifier
        .padding(start = 9.dp)
        .clickable {
            expanded = !expanded
        }) {
        MyText(
            message.title,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.typography.titleMedium
        )
        Spacer(modifier=Modifier.height(16.dp))
        MyText(
            message.body,
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.typography.titleMedium,
            if(expanded) Int.MAX_VALUE else 1
            )
    }
}

@Composable
fun MyText(text: String, color: Color, style: androidx.compose.ui.text.TextStyle, lines: Int = Int.MAX_VALUE){
    Text(text, color=color, style=style, maxLines = lines)
}

@Preview(showSystemUi = true)
@Preview(uiMode=Configuration.UI_MODE_NIGHT_YES)
@Composable
fun previewTexts(){
    JetpackComposeTheme {
        val scrollState= rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            MyMassages(messages)
        }
    }


}
