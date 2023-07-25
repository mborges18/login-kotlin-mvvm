package com.example.loginmvvm.access.signup.domain

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

@JsonAdapter(TypeMemberAdapter::class)
enum class TypeMemberEnum(val typeMember: String, val nameMember: String) {
    GOLD("GOLD", "Ouro"),
    SILVER("SILVER", "Prata"),
    BRONZE("BRONZE", "Bronze");

    companion object {
        fun from(name: String) = values().find { it.nameMember == name } ?: BRONZE
    }
}

private class TypeMemberAdapter : JsonDeserializer<TypeMemberEnum> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext) =
        runCatching { TypeMemberEnum.valueOf(json.asString) }
            .getOrDefault(TypeMemberEnum.BRONZE)
}
