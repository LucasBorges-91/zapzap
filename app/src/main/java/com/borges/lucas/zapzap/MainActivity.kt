package com.borges.lucas.zapzap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borges.lucas.zapzap.ui.theme.ZapZapTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZapZapTheme {
                // A surface container using the 'background' color from the theme
                App()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun App() {
    val items = remember {
        listOf(
            NavItem(
                icon = Icons.Default.Message,
                label = "Message"
            ),
            NavItem(
                icon = Icons.Default.Update,
                label = "Updates"
            ),
            NavItem(
                icon = Icons.Default.People,
                label = "Communities"
            ),
            NavItem(
                icon = Icons.Default.Call,
                label = "Calls"
            )
        )
    }
    var selectedItem by remember {
        mutableStateOf( items.first() )
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text( text = "Zap Zap" )
                },
                actions = {
                    Row(
                        Modifier.padding( 8.dp ),
                        horizontalArrangement = Arrangement.spacedBy( 16.dp )
                    ) {
                        Icon( Icons.Default.CameraAlt, contentDescription = "Camera" )
                        Icon( Icons.Default.MoreVert, contentDescription = "More Options" )
                    }
                })
        },
        bottomBar = {
            BottomAppBar {
                items.forEach { navItem ->
                    NavigationBarItem(
                        selected = navItem == selectedItem,
                        onClick = {
                            selectedItem = navItem
                        },
                        icon = {
                            Icon( navItem.icon, contentDescription = navItem.label )
                        },
                        label = {
                            Text( text = navItem.label )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ChatsListScreen(Modifier.padding(innerPadding))
    }
}

data class NavItem(
    val icon: ImageVector,
    val label: String
)

@Composable
fun ChatsListScreen( modifier: Modifier = Modifier ) {
    Box(modifier = modifier) {

    }
}

@Preview
@Composable
fun AppPreview() {
    ZapZapTheme {
        App()
    }
}