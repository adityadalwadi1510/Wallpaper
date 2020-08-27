package com.example.myapplication

import android.app.Activity
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import java.io.File

class MainActivity : AppCompatActivity() {
    val PICK_IMAGE = 101
    lateinit var bitmap : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.set_wallpaper_from_gallery_menu_attach) {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE
            )

        }
        else if (item.itemId == R.id.get_wallpaper_from_gallery_menu_attach) {
                val wallpaperManager:WallpaperManager= WallpaperManager.getInstance(this)
                wallpaperManager.setBitmap(bitmap)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode==PICK_IMAGE && resultCode== Activity.RESULT_OK){
            val uri= Uri.parse(data.toString());
            bitmap=BitmapFactory.decodeFile(uri.path)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}