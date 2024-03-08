package com.example.note_presentation.addEdit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.LightGray
import com.example.note_presentation.note.NoteUiModel
import com.example.core.*
import com.example.core_ui.Blue
import com.example.core_ui.DarkGray
import com.example.core_ui.LocalSpacing
import com.example.note_presentation.component.AlarmLabel
import com.example.note_presentation.component.DateTimeDialog
import com.example.note_presentation.component.TagText
import com.example.note_presentation.utils.formatReminderDateTime


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun AddEditScreen(
    noteId: String?,
    navigateBack: () -> Unit,
    viewModel: AddEditViewModel = hiltViewModel(),
) {
    if (noteId != null) {
        viewModel.handleEvent(AddEditContract.AddEditViewEvent.GetNote(noteId))
        val value = viewModel.viewState.value
        when (value.addEditStateViewState) {
            AddEditContract.AddEditStateViewState.Loading -> {}
            is AddEditContract.AddEditStateViewState.Success -> {
                ShowAddEditScreen(
                    note = value.addEditStateViewState.note,
                    navigateBack = navigateBack,
                    onSaveNote = {
                        viewModel.handleEvent(
                            AddEditContract.AddEditViewEvent.EditNote(
                                it
                            )
                        )
                    }
                )
            }
        }
    } else {
        ShowAddEditScreen(
            note = NoteUiModel("", "", ""),
            navigateBack = navigateBack,
            onSaveNote = {
                viewModel.handleEvent(
                    AddEditContract.AddEditViewEvent.AddNote(
                        it
                    )
                )
            })
    }

}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAddEditScreen(
    note: NoteUiModel,
    navigateBack: () -> Unit,
    onSaveNote: (NoteUiModel) -> Unit,
) {

    val spacing = LocalSpacing.current

    val noteId by remember {
        mutableStateOf(note.id)
    }

    var title by remember {
        mutableStateOf(note.title)
    }
    var content by remember {
        mutableStateOf(note.content)
    }

    var shouldShowDialogDateTime by remember {
        mutableStateOf(false)
    }

    val rememberUpdateDateTime = remember {
        mutableStateOf(note.alarm)
    }


    Scaffold(
        containerColor = LightGray,
        topBar = {
            CenterAlignedTopAppBar(title = { }, modifier = Modifier
                .padding(16.dp), colors = TopAppBarColors(
                containerColor = LightGray,
                actionIconContentColor = Color.Black,
                navigationIconContentColor = Color.Black,
                scrolledContainerColor = LightGray,
                titleContentColor = LightGray
            ), navigationIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_left),
                    contentDescription = "null",
                    modifier = Modifier
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = DarkGray
                            ), shape = RoundedCornerShape(100.dp)
                        )
                        .padding(8.dp)
                        .clickable(onClick = navigateBack),
                    tint = Color.Black
                )
            },
                actions = {
                    IconButton(onClick = {
                        shouldShowDialogDateTime = true
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ringtone_add),
                            contentDescription = "null",
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = DarkGray
                                    ), shape = RoundedCornerShape(100.dp)
                                )
                                .padding(8.dp),
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.direct_inbox),
                            contentDescription = "null",
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = DarkGray
                                    ), shape = RoundedCornerShape(100.dp)
                                )
                                .padding(8.dp),
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = {
            Surface(
                color = LightGray,
                modifier = Modifier.padding(it)

            ) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Row {
                        TagText(text = "Work")
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        rememberUpdateDateTime.value?.let {
                            AlarmLabel(text = it.formatReminderDateTime())
                        }
                    }
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = title,
                        onValueChange = { newTitle ->
                            title = newTitle
                        },
                        placeholder = {
                            Text(
                                stringResource(R.string.note_title_place_holder_text),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.Gray,
                                fontFamily = FontFamily(Font(R.font.poppins_medium))
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = DarkGray
                        ),
                        maxLines = 2,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            cursorColor = DarkGray
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Sentences,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        value = content,
                        onValueChange = { newContent ->
                            content = newContent
                        },
                        placeholder = {
                            Text(
                                stringResource(R.string.note_content_place_holder_text),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.Gray,
                                fontFamily = FontFamily(Font(R.font.poppins_medium))
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = DarkGray
                        ),
                        maxLines = Int.MAX_VALUE,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            cursorColor = DarkGray
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Sentences,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                //    focus.moveFocus(FocusDirection.Down)
                            }
                        )
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                            .padding(end = 16.dp, start = 16.dp, bottom = 16.dp),
                        color = DarkGray.copy(alpha = 0.5f)
                    )

                }
            }

        },
        bottomBar = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(end = 16.dp, start = 16.dp, bottom = 16.dp)
                        .fillMaxWidth()
                ) {


                    FloatingActionButton(
                        onClick = {
                            onSaveNote(
                                NoteUiModel(
                                    id = noteId,
                                    title = title,
                                    content = content,
                                    label = "",
                                    alarm = rememberUpdateDateTime.value
                                )
                            )
                            navigateBack()

                        },
                        containerColor = Blue,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                        shape = RoundedCornerShape(100),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.tick_circle),
                            "Localized description"
                        )
                    }
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.text_button_text_labels),
                            fontSize = 13.sp,
                            fontWeight = FontWeight(500),
                            lineHeight = 19.5.sp,
                            color = Color.Black,
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.tag),
                            contentDescription = "Localized description",
                            tint = Color.Black
                        )
                    }

                }
            }


        }
    )
    DateTimeDialog(
        open = shouldShowDialogDateTime,
        onDateTimeUpdated = {
            rememberUpdateDateTime.value = it
            shouldShowDialogDateTime = false
        },
    ) {
        shouldShowDialogDateTime = false
    }
}
