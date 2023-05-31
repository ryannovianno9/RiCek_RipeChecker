package com.RipeChecker.RiCek

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.RipeChecker.RiCek.ui.theme.RiCekTheme
import java.io.File

class ComposeFileProvider : FileProvider(
    R.xml.file_paths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile("gambar_dipilih", ".jpg", directory)
            val authority = context.packageName + ".file_provider"
            return getUriForFile(context, authority, file)
        }
    }
}

@Composable
fun DeteksiCompose(
    modifier: Modifier = Modifier,
) {

    var hasImage by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val bukaGaleri = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )
    val ambilFoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )
    val context = LocalContext.current

    Column() {
        IconButton(
            onClick = { },
            modifier = Modifier.padding(top = 20.dp, start = 20.dp).size(60.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Show more",
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 30.dp)
        ) {

            if (hasImage && imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    modifier = Modifier
                        .size(190.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Gray, CircleShape),
                    contentDescription = "Selected image",
                )
            }
            else{
                Image(
                    painter = painterResource(R.drawable.preview_image),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(190.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Gray, CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))
            OutlinedButton(
                onClick = { val uri = ComposeFileProvider.getImageUri(context)
                    imageUri = uri
                    ambilFoto.launch(uri)},
                border = BorderStroke(2.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "Ambil dari kamera", fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedButton(
                onClick = { bukaGaleri.launch("image/*") },
                border = BorderStroke(2.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "Pilih dari galeri", fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "Deteksi", fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    RiCekTheme {
        DeteksiCompose(Modifier)
    }
}


