package com.example.inscribeapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core_ui.LightGray
import com.example.inscribeapp.navigation.Route
import com.example.inscribeapp.ui.theme.InscribeAppTheme
import com.example.note_presentation.addEdit.AddEditScreen
import com.example.note_presentation.note.NoteScreen
import com.example.note_presentation.note.NoteUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InscribeAppTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavHost(navController = navController, startDestination = Route.NOTE) {
                        composable(Route.NOTE) {
                            NoteScreen(
                                navigateToAddEditNoteScreen = { noteId ->
                                    navController.navigate(Route.ADD_EDIT + "/$noteId")
                                }
                            )
                        }
                        composable(route = Route.ADD_EDIT + "/{noteId}", arguments = listOf(
                            navArgument("noteId") {
                                type = NavType.StringType
                                nullable = true
                            }
                        )) {
                            val noteId = it.arguments?.getString("noteId")
                            AddEditScreen(noteId = noteId, navigateBack = {
                                navController.navigateUp()
                            })
                        }
                    }
                }
            }
        }
    }
}

