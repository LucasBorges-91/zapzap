package com.borges.lucas.zapzap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.LaunchedEffect
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

sealed class NavItem(
    val icon: ImageVector,
    val label: String
) {
    data object Messages: NavItem(
        icon = Icons.Default.Message,
        label = "Messages"
    )

    data object Updates: NavItem(
        icon = Icons.Default.Update,
        label = "Updates"
    )

    data object Communities: NavItem(
        icon = Icons.Default.People,
        label = "Communities"
    )

    data object Calls: NavItem(
        icon = Icons.Default.Call,
        label = "Calls"
    )
}

@OptIn( ExperimentalMaterial3Api::class )
@Composable
fun App() {
    val items = remember {
        listOf(
            NavItem.Messages,
            NavItem.Updates,
            NavItem.Communities,
            NavItem.Calls
        )
    }
    var selectedItem by remember {
        mutableStateOf( items.first() )
    }
    val pagerState = rememberPagerState {
        items.size
    }
    LaunchedEffect( selectedItem ) {
        pagerState.animateScrollToPage( items.indexOf( selectedItem ) )
    }
    LaunchedEffect( pagerState.targetPage ) {
        selectedItem = items[pagerState.targetPage]
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
        HorizontalPager(
            pagerState,
            Modifier.padding( innerPadding )
        ) { page ->
            when ( items[page] ) {
                NavItem.Calls -> CallsScreen()
                NavItem.Communities -> CommunitiesScreen()
                NavItem.Messages -> MessagetScreen()
                NavItem.Updates -> UpdatesScreen()
            }
        }
    }
}

@Composable
fun MessagetScreen( modifier: Modifier = Modifier ) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Message")
    }
}

@Composable
fun UpdatesScreen( modifier: Modifier = Modifier ) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Updates")
    }
}

@Composable
fun CommunitiesScreen( modifier: Modifier = Modifier ) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Communities")
    }
}

@Composable
fun CallsScreen( modifier: Modifier = Modifier ) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Calls")
    }
}

@Preview
@Composable
fun AppPreview() {
    ZapZapTheme {
        App()
    }
}