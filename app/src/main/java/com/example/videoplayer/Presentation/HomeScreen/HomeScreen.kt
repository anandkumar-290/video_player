package com.example.videoplayer.Presentation.HomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.videoplayer.Presentation.FolderScreen.FolderScreen
import com.example.videoplayer.Presentation.TabItemsData.TabItems
import com.example.videoplayer.Presentation.VideosScreen.VideosScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val tabs = listOf(
        TabItems(
            "Folder", Icons.Default.Folder,
            filledIcon = Icons.Outlined.Folder),
        TabItems(
            "All Videos", Icons.Default.VideoLibrary,
            filledIcon = Icons.Outlined.VideoLibrary
        )

    )

    val pagerState = rememberPagerState(pageCount = {tabs.size})
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    Scaffold(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text("video Max Player")
                        },scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->

        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, tab ->

                    Tab(
                        modifier = Modifier.fillMaxWidth(),
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Row (verticalAlignment = Alignment.CenterVertically){
                                Icon(
                                    if(pagerState.currentPage == index) tab.icon else tab.filledIcon,
                                    contentDescription = tab.title,
                                    modifier = Modifier.padding(end = 24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(tab.title)
                            }
                        },
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
            }

            HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) {page ->

                when(page){
                    0 -> FolderScreen(navController = navController)
                    1 -> VideosScreen(navController = navController)
                }
            }
        }
    }
}