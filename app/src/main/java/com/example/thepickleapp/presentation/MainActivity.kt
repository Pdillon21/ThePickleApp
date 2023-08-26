package com.example.thepickleapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.thepickleapp.presentation.main_screen.MainScreen
import com.example.thepickleapp.presentation.ui.theme.ThePickleAppTheme
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThePickleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = pickleAppColors().surface
                ) {
                    MainScreen()
                }
            }
        }
    }
}