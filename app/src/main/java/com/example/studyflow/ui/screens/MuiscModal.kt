package com.example.studyflow.ui.screens

import androidx.compose.runtime.Composable
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyflow.R


sealed class AppIcons {
    object Menu : AppIcons()
    object Headphones : AppIcons()
    object Home : AppIcons()
    object Favorite : AppIcons()
    object Search : AppIcons()
    object Person : AppIcons()
    object Settings : AppIcons()
    object MusicNote : AppIcons()
    object PlayArrow : AppIcons()
    object Pause : AppIcons()
    object ArrowBack : AppIcons()

    val imageVector: ImageVector
        get() = when (this) {
            is Menu -> Icons.Default.Menu
            is Headphones -> Icons.Default.Headphones
            is Home -> Icons.Default.Home
            is Favorite -> Icons.Default.Favorite
            is Search -> Icons.Default.Search
            is Person -> Icons.Default.Person
            is Settings -> Icons.Default.Settings
            is MusicNote -> Icons.Default.MusicNote
            is PlayArrow -> Icons.Default.PlayArrow
            is Pause -> Icons.Default.Pause
            is ArrowBack -> Icons.Default.ArrowBack
        }
}

data class Song(
    val title: String,
    val artist: String,
    val views: String,
    val imagesRes: Int
)

data class Album(
    val title: String,
    val artist: String,
    val views: String,
    val imageRes: Int
)


@Composable
fun EnergizingSongsPage(onBack: () -> Unit) {
    var showComingSoon by remember { mutableStateOf(false) }
    val songs = listOf(
        Song("Mama Eh", "By Eiyanna", "700 Thousand views", R.drawable.mama_eh),
        Song("Caliente", "By Eiyanna", "11 Million views", R.drawable.caliente),
        Song("Not like Us", "By Kendrick Lamar", "973 Million views", R.drawable.not_like_us)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E1D25))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = AppIcons.ArrowBack.imageVector,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Energizing Songs",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            LazyColumn {
                items(songs) { song ->
                    SongCard(song = song) {
                        showComingSoon = true
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        if (showComingSoon) {
            ComingSoonDialog {
                showComingSoon = false
            }
        }
    }
}
@Composable
fun FocusSongsPage(onBack: () -> Unit) {
    val songs = listOf(
        Song("Deep Concentration", "By Bella", "950 Thousand views", R.drawable.deep),
        Song("Study Beats", "By Bella", "1.4 Million views", R.drawable.chill_study),
        Song("No Distractions", "By Zeyna", "600 Thousand views", R.drawable.no_d)
    )
    var showComingSoon by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E1D25))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = AppIcons.ArrowBack.imageVector,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Focus Songs",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyColumn {
                items(songs) { song ->
                    SongCard(song = song) {
                        showComingSoon = true
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        if (showComingSoon) {
            ComingSoonDialog {
                showComingSoon = false
            }
        }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioBottomSheet(
    showSheet: Boolean,
    onDismiss: () -> Unit,
    sheetState: SheetState,
    onFullMusicClick: () -> Unit
) {
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = Color(0xFF0E1D25),
            scrimColor = Color.Black.copy(alpha = 0.5f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                AudioPlayerCard("Rain", R.raw.song)
                AudioPlayerCard("Birds", R.raw.song)
                AudioPlayerCard("Campfire", R.raw.song)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onDismiss()
                        onFullMusicClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A5ACD)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    Text("Full Music Version", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A5ACD)
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Close", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun SongCard(song: Song, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF172f3e))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = song.imagesRes),
                contentDescription = "Song Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = song.title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = song.artist,
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = song.views,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Composable
fun FullMusicPage(
    onBack: () -> Unit,
    onEnergiseClick: () -> Unit,
    onRelaxClick: () -> Unit,
    onFocusClick: () -> Unit
) {
    val albums = listOf(
        Album("Energise", "By Mama mia and others", "3.2 Million views",R.drawable.energise),
        Album("Relax", "By Shriane and others", "6.1 Million views",R.drawable.relax),
        Album("Focus", "By Bella and others", "1.1 Million views",R.drawable.feel_good)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E1D25))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = AppIcons.ArrowBack.imageVector,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Popular Albums",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyColumn {
                items(albums) { album ->
                    AlbumCard(
                        album = album,
                        onClick = {
                            when (album.title) {
                                "Energise" -> onEnergiseClick()
                                "Relax" -> onRelaxClick()
                                "Focus" -> onFocusClick()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun RelaxSongsPage(onBack: () -> Unit) {
    val songs = listOf(
        Song("Peaceful Waters", "By Shriane", "1.2 Million views", R.drawable.waters),
        Song("Soft Wind", "By Shriane", "800 Thousand views", R.drawable.wind),
        Song("Silent Dreams", "By Liyah", "2.5 Million views", R.drawable.dream)
    )
    var showComingSoon by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E1D25))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = AppIcons.ArrowBack.imageVector,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Relaxing Songs",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyColumn {
                items(songs) { song ->
                    SongCard(song = song) {
                        showComingSoon = true
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        if (showComingSoon) {
            ComingSoonDialog {
                showComingSoon = false
            }
        }
        }
}


@Composable
fun AlbumCard(
    album: Album,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF172f3e))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = album.imageRes),
                contentDescription = "Album Cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = album.title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = album.artist,
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = album.views,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Composable
fun AudioPlayerCard(title: String, audioResId: Int) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    val mediaPlayer = remember { MediaPlayer.create(context, audioResId) }
    // mediaPlayer.setVolume(100f, 100f)
    // Some ppl say that the Emulator is the problem, so myb try to put it on ur phone and run it
    // If that does not fix the issue, try uncommenting my line line (437) and then putting it on ur phone
    // Read below what should be tried later

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C3E50))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            IconButton(
                onClick = {
                    if (isPlaying) {
                        // MediaPlayer.create(context, audioResId).pause()
                        mediaPlayer.pause()
                    } else {
                        // MediaPlayer.create(context, audioResId).start()
                        mediaPlayer.start()
                        mediaPlayer.isLooping = true
                    }
                    isPlaying = !isPlaying
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (isPlaying) AppIcons.Pause.imageVector else AppIcons.PlayArrow.imageVector,
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }
}


@Composable
fun MusicCard(title: String) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1B263B))
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                AppIcons.MusicNote.imageVector,
                contentDescription = "Music",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Text(title, color = Color.White, fontSize = 14.sp)
        }
    }
}
@Composable
fun ComingSoonDialog(onDismiss: () -> Unit) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("OK")
            }
        },
        title = { Text("Coming Soon") },
        text = { Text("This feature is coming soon!") },
        containerColor = Color(0xFF1B263B),
        titleContentColor = Color.White,
        textContentColor = Color.White
    )
}
