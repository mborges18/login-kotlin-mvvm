package com.compdesign.compheader

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.compdesign.R
import com.compdesign.databinding.CompHeaderBinding

class CompHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = CompHeaderBinding.inflate(LayoutInflater.from(context), this)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CompHeader, defStyleAttr, 0)
        handlerProperties(a)
        a.recycle()
    }

    fun bind(title: String, icon: Int, subTitle: String, description: String) {
        handlerComponents(title, icon, subTitle, description)
    }

    fun setListener(listener: (() -> Unit)? = null) {
        listener?.let { func ->
            binding.btnClose.isVisible = true
            binding.btnClose.setOnClickListener {
                func.invoke()
            }
        }
    }

    private fun handlerProperties(a: TypedArray) {
        val title: String = a.getString(R.styleable.CompHeader_comp_title) ?: ""
        val subTitle: String = a.getString(R.styleable.CompHeader_comp_subtitle) ?: ""
        val description: String = a.getString(R.styleable.CompHeader_comp_description) ?: ""
        val icon: Int = a.getResourceId(R.styleable.CompHeader_comp_icon, 0)
        handlerComponents(title, icon, subTitle, description)
    }

    private fun handlerComponents(title: String, icon: Int, subTitle: String, description: String) = with(binding) {
        txvTitle.text = title
        txvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0)
        txvSubTitle.text = subTitle
        txvDescription.text = description
    }
}