package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.Job
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {

    lateinit var cakeImageView: ImageView
    var scope =CoroutineScope(Job()+ Dispatchers.Default)
    val handler = Handler(Looper.getMainLooper(), Handler.Callback {
        cakeImageView.alpha = it.what / 100f
        true
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cakeImageView = findViewById(R.id.imageView)

        findViewById<Button>(R.id.revealButton).setOnClickListener{
            scope.cancel()
            scope = CoroutineScope(Job() + Dispatchers.Default)
            scope.launch{
                countdown()
            }
        }

    }suspend fun countdown(){
        repeat(100) {
            handler.sendEmptyMessage(it)
            delay(20)
        }
    }
}