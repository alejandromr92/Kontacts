package com.alejandromr.kontacts

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun AppCompatImageView.loadImage(url: String?) {
    url?.let {
        if (url.isNotBlank()) {
            val circularProgressDrawable = CircularProgressDrawable(this.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            }

            Glide.with(this.context)
                .load(url)
                .placeholder(circularProgressDrawable)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        circularProgressDrawable.stop()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        circularProgressDrawable.stop()
                        return false
                    }
                })
                .transform(CircleCrop())
                .into(this)
        }
    }
}
