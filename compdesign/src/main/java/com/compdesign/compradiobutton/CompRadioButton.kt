package com.compdesign.compradiobutton

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.compdesign.R
import com.compdesign.databinding.CompRadioButtonBinding
import com.compdesign.databinding.ViewRadioBinding

class CompRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = CompRadioButtonBinding.inflate(LayoutInflater.from(context), this)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CompRadioButton, defStyleAttr, 0)
        handlerRadio(a)
        a.recycle()
    }

    fun setData(listNames: ArrayList<Pair<String, Boolean>>, getData: (text: String) -> Unit) {
        binding.rgpComp.removeAllViews()
        handlerList(listNames)
        handlerListener(getData)
    }

    fun bindEnabled(it: Boolean) {
        binding.rgpComp.isEnabled = it
    }

    private fun handlerRadio(a: TypedArray) {
        val list: Array<CharSequence> = a.getTextArray(R.styleable.CompRadioButton_radio_names) ?: emptyArray()
        val listPair = arrayListOf<Pair<String, Boolean>>()
        list.forEach {
            val ar = it.split("|")
            listPair.add(Pair(ar[0], ar[1].toBoolean()))
        }
        handlerList(listPair)
    }

    private fun handlerListener(getData: ((text: String) -> Unit)? = null) {
        binding.rgpComp.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = group.findViewById(checkedId) as RadioButton
            if(radio.isChecked) {
                getData?.invoke(radio.text.toString())
            }
        }
    }

    private fun handlerList(listNames: ArrayList<Pair<String, Boolean>>) {
        listNames.takeIf {
            it.isNotEmpty()
        }?.let { list ->
            list.forEach { pair ->
                ViewRadioBinding.inflate(
                    LayoutInflater.from(context),
                    binding.rgpComp, false
                ).run {
                    root.apply {
                        id = View.generateViewId()
                        text = pair.first
                        isChecked = pair.second
                    }.also {
                        binding.rgpComp.addView(it)
                    }
                }
            }
        }
    }
}
