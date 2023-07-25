package com.example.loginmvvm.access.signup.domain

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

@JsonAdapter(TypeStatusAdapter::class)
enum class TypeStatusEnum(val typeStatus: String, val nameStatus: String) {
    ACTIVE("ACTIVE", "Ativo"),
    INACTIVE("INACTIVE", "Inativo");

    companion object {
        fun from(type: String) = TypeMemberEnum.values().find { it.name == type } ?: INACTIVE
    }
}

private class TypeStatusAdapter : JsonDeserializer<TypeStatusEnum> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext) =
        runCatching { TypeStatusEnum.valueOf(json.asString) }
            .getOrDefault(TypeStatusEnum.INACTIVE)
}