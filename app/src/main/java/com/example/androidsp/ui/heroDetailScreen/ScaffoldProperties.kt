package com.example.androidsp.ui.heroDetailScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopBar(title: String = "Detalle de héroe", onMenuClicked: () -> Unit){
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { onMenuClicked()}) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Hero menu button")
            }
        }
    )
}

/*
Componente no visto en clase: Drawer.
 */
@Composable
fun CustomDrawer(items: List<DrawerItems>, onItemClick: (DrawerItems) -> Unit){
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(30.dp)) {
        items.forEach(){
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable {
                    onItemClick(it)
                }){
                Icon(imageVector = it.icon, contentDescription = it.text)
                Spacer(modifier = Modifier.width(40.dp))
                Text(text = it.text, style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Preview
@Composable
fun CustomTopBar_Preview(){
    CustomTopBar {

    }
}

@Preview(showBackground = true)
@Composable
fun CustomDrawer_Preview(){
    CustomDrawer(items = DrawerItems.values().toList()){

    }
}