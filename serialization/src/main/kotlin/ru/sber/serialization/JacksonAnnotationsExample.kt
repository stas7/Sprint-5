package ru.sber.serialization

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

@JsonIgnoreProperties(value = ["age"], ignoreUnknown = true)
data class User(
    @JsonProperty("name")
    val firstName: String,
    val lastName: String,
    val age: Int,
)

fun main() {
    val objectMapper = ObjectMapper()
        .registerModule(KotlinModule())
        .enable(SerializationFeature.INDENT_OUTPUT)
    val initialObject = User("Василий", "Васильев", 30)
    val serializedData = objectMapper.writeValueAsString(initialObject)
    println("Сериализованные данные: $serializedData")

    val data = """{"name":"Василий", "lastName":"Васильев", "age":30, "hobbies": "Футбол"}"""
    val deserializedObject = objectMapper.readValue<User>(data)
    println("Входные данные: $data")
    println("Десериализованный объект: $deserializedObject")
}