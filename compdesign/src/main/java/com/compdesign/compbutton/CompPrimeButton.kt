package com.compdesign.compbutton

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.ImageViewTarget
import com.compdesign.R
import com.compdesign.databinding.CompPrimeButtonBinding


class CompPrimeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
): FrameLayout(context, attrs, defStyle) {

    private var icon: Int = 0
    private var title: String = ""
    private val binding = CompPrimeButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CompPrimeButton, defStyle, 0)
        handlerProperties(a)
        a.recycle()
    }

    fun bind(title: String, icon: Int, onClick: () -> Unit) {
        this.title = title
        this.icon = icon
        handlerComponents(title, icon, onClick)
    }

    fun setTitle(title: String) {
        this.title = title
        binding.cpbTitle.text = title
    }

    fun setIcon(icon: Int) {
        this.icon = icon
        binding.cpbTitle.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
    }

    fun setClickListener(onClick: () -> Unit) {
        doListener(onClick)
    }

    fun bindEnabled(it: Boolean) {
        binding.root.isClickable = it
        binding.root.isFocusable = it
        if (it) {
            binding.root.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue))
        } else {
            binding.root.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorGrayLight))
        }
    }

    fun showLoading() {
        binding.cpbTitle.isVisible = false
        binding.cpbLoader.isVisible = true
    }

    fun hideLoading() {
        binding.cpbTitle.isVisible = true
        binding.cpbLoader.isVisible = false
    }

    private fun handlerProperties(a: TypedArray) {
        title = a.getString(R.styleable.CompPrimeButton_comp_prime_text) ?: ""
        icon = a.getResourceId(R.styleable.CompPrimeButton_comp_prime_icon, 0)
        handlerComponents(title, icon)
    }

    private fun handlerComponents(title: String, icon: Int, onClick: (() -> Unit)? = null) = with(binding) {
        binding.cpbTitle.text = title
        binding.cpbTitle.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        doListener(onClick)

        Glide.with(context)
            .asGif()
            .load(R.drawable.ic_gif_loader)
            .centerCrop()
            .into(object : ImageViewTarget<GifDrawable?>(binding.cpbLoader) {
                override fun setResource(resource: GifDrawable?) {
                    binding.cpbLoader.setImageDrawable(resource)
                }
            })
    }

    private fun doListener(onClick: (() -> Unit)? = null) {
        binding.root.setOnClickListener {
            onClick?.invoke()
        }
    }
}