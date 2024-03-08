package com.example.note_presentation.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R.*
import com.example.core_ui.Blue
import com.example.core_ui.DarkGray
import com.example.core_ui.LightGray
import com.example.core_ui.LocalSpacing
import com.example.note_presentation.component.GridNotesCard
import com.example.note_presentation.component.LayoutToggleButton
import com.example.note_presentation.component.NotesCard

@Composable
fun NoteScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    navigateToAddEditNoteScreen: (noteId: String?) -> Unit
) {
    val value = viewModel.viewState.value
    LaunchedEffect(key1 = true) {
        viewModel.handleEvent(NoteContract.NoteViewEvent.GetNotes)
    }
    when (value.noteViewState) {
        NoteContract.NoteViewState.Loading -> {

        }

        is NoteContract.NoteViewState.Success -> ShowNoteList(
            listOfAllNotes = value.noteViewState.notes,
            navigateToAddEditNoteScreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowNoteList(
    listOfAllNotes: List<NoteUiModel>,
    navigateToAddEditNoteScreen: (noteId: String?) -> Unit
) {
    val spacing = LocalSpacing.current
    var isGridView by rememberSaveable { mutableStateOf(true) }
    val listState: LazyListState = rememberLazyListState()
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val staggeredGState: LazyStaggeredGridState = rememberLazyStaggeredGridState()
    Scaffold(
        containerColor = LightGray,
        topBar = {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                colors = SearchBarDefaults.colors(containerColor = LightGray),
                query = searchQuery,
                onQueryChange = { search ->
                    searchQuery = search
                },
                onSearch = {},
                active = false,
                onActiveChange = {},
                placeholder = {
                    Row {
                        Icon(
                            imageVector = ImageVector.vectorResource(drawable.search_normal),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.padding(start = spacing.spaceSmall)
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        Text(stringResource(string.search_your_notes))
                    }
                },
                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(40.dp)
                    )


                },
                trailingIcon = {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LayoutToggleButton(isGridView = isGridView,
                            onToggleClick = { isGridView = !isGridView })
                        Icon(
                            imageVector = ImageVector.vectorResource(drawable.group_36693),
                            contentDescription = null,
                            tint = Color.Black,
                        )
                    }


                }) {}

        }, content = { it ->
            Surface(
                color = LightGray,
                modifier = Modifier.padding(it)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                            .padding(end = 16.dp, start = 16.dp, bottom = 16.dp),
                        color = DarkGray.copy(alpha = 0.5f)
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp),
                        text = "Recent All Note",
                        fontFamily = FontFamily(
                            Font(font.poppins_medium)
                        ),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    )

                    if (listOfAllNotes.isNotEmpty()) {
                        if (isGridView) {
                            LazyVerticalStaggeredGrid(
                                columns = StaggeredGridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp
                                    ),
                                contentPadding = PaddingValues(bottom = 95.dp),
                                state = staggeredGState
                            ) {
                                itemsIndexed(listOfAllNotes.filter { note ->
                                    note.title.contains(searchQuery, true)
                                }) { _, notesModel ->
                                    GridNotesCard(notesModel, onClick = {
                                        navigateToAddEditNoteScreen(notesModel.id)
                                    })
                                }
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp
                                    )
                                    .background(Color.Transparent),
                                contentPadding = PaddingValues(bottom = 95.dp),
                                state = listState
                            ) {
                                items(listOfAllNotes,
                                    key = { it.id!! },
                                    contentType = { it.id }) { notesModel ->
                                    Box {
                                        NotesCard(noteModel = notesModel, onClick = {
                                            navigateToAddEditNoteScreen(notesModel.id)
                                        })
                                    }
                                }
                            }
                        }
                    }

                }
            }

        },
        bottomBar = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Column {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                            .padding(end = 16.dp, start = 16.dp, bottom = 16.dp),
                        color = DarkGray.copy(alpha = 0.5f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(end = 16.dp, start = 16.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    ) {


                        FloatingActionButton(
                            onClick = {
                                navigateToAddEditNoteScreen(null)
                            },
                            containerColor = Blue,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                            shape = RoundedCornerShape(100),
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(drawable.add),
                                "Localized description"
                            )
                        }
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = string.text_button_text_labels),
                                fontSize = 13.sp,
                                fontWeight = FontWeight(500),
                                lineHeight = 19.5.sp,
                                color = Color.Black,
                            )
                            Spacer(modifier = Modifier.width(spacing.spaceSmall))
                            Icon(
                                imageVector = ImageVector.vectorResource(drawable.tag),
                                contentDescription = "Localized description",
                                tint = Color.Black
                            )
                        }

                    }
                }

            }


        })

}

