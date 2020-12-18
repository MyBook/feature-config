package ru.mybook.common.test

import java.util.UUID

fun randomString() = UUID.randomUUID().toString()

fun randomBoolean() = Math.random() > 0.5

fun randomLong() = (Math.random() * Long.MAX_VALUE).toLong()
