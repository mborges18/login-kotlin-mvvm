package com.compdesign.compedittext.extentions

import android.content.res.TypedArray
import android.widget.EditText
import com.compdesign.compedittext.enums.MasksEnum
import com.compdesign.compedittext.mask.MaskEditText

inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, default: T) =
    getInt(index, -1).let {
        if (it >= 0) enumValues<T>()[it] else default
    }

fun EditText.setMask(mask: MasksEnum) {
    addTextChangedListener(
        MaskEditText(
            mask.value,
            this
        )
    )
}