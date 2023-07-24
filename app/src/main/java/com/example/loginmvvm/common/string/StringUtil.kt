package com.example.loginmvvm.common.string

object StringUtil {

    fun handlerJustNumber(s: String): String{
        var ss = s
        return try {
            val re = Regex("[^0-9]")
            ss = re.replace(ss, "")
            return ss
        }catch (e:Exception){
            s
        }
    }
}