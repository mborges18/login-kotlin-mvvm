package com.compdesign.compedittext

import android.content.Context
import android.content.res.TypedArray
import android.text.InputFilter
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.compdesign.R
import com.compdesign.compedittext.enums.EndIconActionEnum
import com.compdesign.compedittext.enums.MasksEnum
import com.compdesign.compedittext.extentions.getEnum
import com.compdesign.compedittext.extentions.setMask
import com.compdesign.databinding.CompEditTextInputBinding


class CompEditTextInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var ediText: AppCompatEditText? = null
    private var drawableStart = 0
    private var drawableEnd: Int = 0
    private var counter: Int = 0
    private var endIconActionEnum: EndIconActionEnum = EndIconActionEnum.NONE
    private var passwordToggleOpen = true

    private val binding = CompEditTextInputBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CompEditTextInput, defStyle, 0)
        handlerProperties(a)
        handlerColorActive()
        a.recycle()
    }

    fun setData(value: String, listenerData: (text: String) -> Unit) {
        ediText?.setText(value)
        doListener(listenerData)
        ediText?.background = ContextCompat.getDrawable(context, R.drawable.border_edit_text_input)
        handlerColor(R.color.colorGrayLight)
        binding.tvHolder.animate().translationY(-VALUE_70).translationX(-VALUE_70).duration = DURATION
    }

    fun setListener(listenerData: (text: String) -> Unit) {
        doListener(listenerData)
    }

    fun getData() = ediText?.text.toString()

    fun getError() = binding.tvError.text.toString()

    fun getPlaceHolder() = binding.tvHolder.text.toString()

    fun clearField() = ediText?.setText("")

    fun setError(msg: String) {
        ediText?.clearFocus()
        handlerColor(R.color.colorGrayLight)
        ediText?.setTextColor(ContextCompat.getColor(context, R.color.colorRed))
        ediText?.background = ContextCompat.getDrawable(context, R.drawable.border_edit_text_error)
        handlerColor(R.color.colorRed)
        binding.tvError.isVisible = true
        binding.tvError.text = msg
    }

    fun normalizeField() {
        handlerClearError()
        handlerColor(R.color.colorGrayLight)
    }

    fun bindEnabled(it: Boolean) {
        ediText?.clearFocus()
        ediText?.isEnabled = it
    }

    private fun handlerProperties(a: TypedArray) {
        ediText = binding.edtInput
        val idInput = a.getResourceId(R.styleable.CompEditTextInput_comp_id, 0)
        val hint: String? = a.getString(R.styleable.CompEditTextInput_comp_hint)
        counter = a.getInt(R.styleable.CompEditTextInput_comp_counter, 0)
        val inputType: Int = a.getInt(R.styleable.CompEditTextInput_android_inputType, 0)
        val maxLength: Int = a.getInt(R.styleable.CompEditTextInput_android_maxLength, MAX_VALUE)
        drawableStart = a.getResourceId(R.styleable.CompEditTextInput_android_drawableStart, 0)
        drawableEnd = a.getResourceId(R.styleable.CompEditTextInput_android_drawableEnd, 0)
        endIconActionEnum = a.getEnum(R.styleable.CompEditTextInput_comp_endIconAction, EndIconActionEnum.NONE)
        val masks = a.getEnum(R.styleable.CompEditTextInput_comp_mask, MasksEnum.NONE)


        ediText?.inputType = inputType
        ediText?.filters = arrayOf(InputFilter.LengthFilter(maxLength))
        ediText?.setCompoundDrawablesWithIntrinsicBounds(drawableStart, 0, drawableEnd, 0)
        hint?.let {
            binding.tvHolder.text = it
        }

        handlerCounter()
        handlerEndIcon()
        handlerMasks(masks)
        ediText?.id = idInput
    }

    private fun doListener(listenerData: (text: String) -> Unit) {
        ediText?.doAfterTextChanged { editable ->
            binding.tvCounter.text = editable?.let{ doCounter(it.length.toString(), counter.toString()) }
            listenerData(editable.toString())
        }
    }

    private fun doCounter(value: String, counter: String) = "$value de $counter"

    private fun handlerColorActive() {
        handlerColor(R.color.colorGrayLight)
        ediText?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                handlerClearError()
                handlerColor(R.color.blue)
                binding.tvHolder.animate().translationY(-VALUE_70)
                    .translationX(-VALUE_70).duration = DURATION
            } else {
                if (binding.tvError.isVisible.not()) {
                    handlerColor(R.color.colorGrayLight)
                }
                if (ediText?.text.toString().isEmpty()) {
                    binding.tvHolder.animate().translationY(VALUE_0)
                        .translationX(VALUE_0).duration = DURATION
                }
            }
        }
    }

    private fun handlerColor(color: Int) {
        if (drawableStart != 0) {
            ediText?.compoundDrawables?.let {
                DrawableCompat.setTint(
                    DrawableCompat.wrap(it[0]),
                    ContextCompat.getColor(context, color)
                )
            }
        }
        binding.tvHolder.setTextColor(ContextCompat.getColor(context, color))
    }

    private fun handlerClearError() {
        ediText?.background =
            ContextCompat.getDrawable(context, R.drawable.border_edit_text_input)
        ediText?.setTextColor(ContextCompat.getColor(context, R.color.black))
        binding.tvError.isVisible = false
        binding.tvError.text = ""
    }

    private fun handlerMasks(mask: MasksEnum) {
        mask.takeIf {
            it != MasksEnum.NONE
        }?.let {
            ediText?.inputType = InputType.TYPE_CLASS_NUMBER
            when(mask) {
                MasksEnum.CPF -> ediText?.setMask(mask)
                MasksEnum.CNPJ -> ediText?.setMask(mask)
                MasksEnum.PHONE -> ediText?.setMask(mask)
                MasksEnum.DATE -> ediText?.setMask(mask)
                else -> {}
            }
        }
    }

    private fun handlerCounter() {
        counter.takeIf {
            it > 0
        }?.let {
            ediText?.filters = arrayOf(InputFilter.LengthFilter(counter))
            binding.tvCounter.isVisible = true
            binding.tvCounter.text = doCounter("0", it.toString())
        }
    }

    private fun handlerEndIcon() {
        when (endIconActionEnum) {
            EndIconActionEnum.PASSWORD_TOGGLE -> {
                binding.ibEnd.isVisible = true
                binding.ibEnd.setImageResource(R.drawable.ic_eye_open)
                binding.ibEnd.setOnClickListener {
                    passwordToggleOpen = passwordToggleOpen.not()
                    if (passwordToggleOpen) {
                        binding.ibEnd.setImageResource(R.drawable.ic_eye_open)
                        ediText?.transformationMethod =
                            PasswordTransformationMethod.getInstance()
                    } else {
                        binding.ibEnd.setImageResource(R.drawable.ic_eye_close)
                        ediText?.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                    }
                }
            }
            EndIconActionEnum.CLEAR_FIELD -> {
                binding.ibEnd.isVisible = true
                binding.ibEnd.setImageResource(R.drawable.ic_clear)
                binding.ibEnd.setOnClickListener {
                    clearField()
                }
            }
            EndIconActionEnum.NONE -> {
                binding.ibEnd.isVisible = false
            }
        }
    }

    companion object {
        const val MAX_VALUE = 100
        const val VALUE_70 = 70.toFloat()
        const val VALUE_0 = 0.toFloat()
        const val DURATION = 100.toLong()
    }
}
