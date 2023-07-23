package com.compdesign.compedittext.enums

enum class MasksEnum(val value: String) {
    NONE("-1"),
    CPF("###.###.###-##"),
    CNPJ("##.###.###/####-##"),
    DATE("##/##/####"),
    PHONE("(##) #####-####")
}
