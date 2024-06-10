package dev.mysticmaster.tamngon247.util

import java.util.Random

fun generateRandomString(): String {
    val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    val random = Random()
    return (1..8)
        .map { random.nextInt(characters.length) }
        .map(characters::get)
        .joinToString("")
}