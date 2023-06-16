package com.RipeChecker.RiCek

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.RipeChecker.RiCek.ui.theme.Purple40
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
//        fun uriToFile(imageUri: Uri?, context: Context) : File{
//            val contentResolver: ContentResolver = context.contentResolver
//            val directory = File(context.cacheDir, "images")
//            directory.mkdirs()
//            val file = File.createTempFile("gambar_dipilih", ".jpg", directory)
//            val inputStream = contentResolver.openInputStream(imageUri!!) as InputStream
//            val outputStream: OutputStream = FileOutputStream(file)
//            val buf = ByteArray(1024)
//            var len: Int
//            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
//            outputStream.close()
//            inputStream.close()
//
//            return file
//        }


    }

}

@Composable
fun DeteksiCompose(
) {
    var hasImage by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var hasil = remember {
        mutableStateOf("")
    }
    var getFile: File? = null
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
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Purple40)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Detect",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 15.dp)
                )
            }
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

            } else {
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
                onClick = {
                    val uri = ComposeFileProvider.getImageUri(context)
                    imageUri = uri
                    ambilFoto.launch(uri)
                },
                border = BorderStroke(2.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "Take from camera",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
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
                    text = "Select from gallery",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            var popup by remember { mutableStateOf(false) }
            Button(
                onClick = {
                    if (hasImage && imageUri != null) {
                        getFile = uriToFile(imageUri!!, context)
                        uploadImage(context, getFile!!, hasil)
                        popup = true
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "Detect", fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
            }

            if (popup) {
                Popup(
                    popupPositionProvider = PopupWindow(),
                    onDismissRequest = { popup = true },
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White,
                        border = BorderStroke(3.dp, Color.Black)
                    ) {
                        Column(
                            modifier = Modifier.padding(100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = hasil.value)
                            Spacer(modifier = Modifier.height(10.dp))
                            TextButton(onClick = {
                                popup = false
                                imageUri = null
                            }) {
                                Text(text = "Oke")
                            }
                        }
                    }
                }
            }


        }

    }
}
private fun uploadImage(
    context: Context,
    file: File,
    result: MutableState<String>
) {
    var url = "https://ricek1-oes4ruxepa-et.a.run.app"
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitAPI = retrofit.create(DeteksiApiService::class.java)
    val requestImageFile = file.asRequestBody("images/jpg".toMediaTypeOrNull())
    val imageMultipart = MultipartBody.Part.createFormData(
        "img",
        file.name,
        requestImageFile
    )
    val call: Call<com.RipeChecker.RiCek.Response?>? = retrofitAPI.postData(imageMultipart)
    call!!.enqueue(object : Callback<com.RipeChecker.RiCek.Response?> {
        override fun onResponse(call: Call<com.RipeChecker.RiCek.Response?>?, response: Response<com.RipeChecker.RiCek.Response?>) {
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            val hasil: com.RipeChecker.RiCek.Response? = response.body()
            val respons =
                "Result: " + "\n" + "Predicted Class : " + hasil!!.predictedClass + "\n" + " Confidence: " + hasil!!.confidence
            result.value = respons
        }

        override fun onFailure(call: Call<com.RipeChecker.RiCek.Response?>, t: Throwable) {
            result.value = "Error found is : " + t.message
        }
    })

}

class PopupWindow(
    private val x: Int = 0,
    private val y: Int = 0
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        return IntOffset(
            (windowSize.width - popupContentSize.width) / 2 + x,
            (windowSize.height - popupContentSize.height) / 2 + y
        )
    }
}