package com.example.loginmvvm.access.signup.domain

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

@JsonAdapter(TypeMemberAdapter::class)
enum class TypeMemberEnum(val typeMember: String, val nameMember: String) {
    DISCIPLE("DISCIPLE", "Discípulo"),
    LEADER("LEADER", "Líder"),
    VISITOR("VISITOR", "Visitante");

    companion object {
        fun from(type: String) = values().find { it.name == type } ?: VISITOR
    }
}

private class TypeMemberAdapter : JsonDeserializer<TypeMemberEnum> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext) =
        runCatching { TypeMemberEnum.valueOf(json.asString) }
            .getOrDefault(TypeMemberEnum.VISITOR)
}