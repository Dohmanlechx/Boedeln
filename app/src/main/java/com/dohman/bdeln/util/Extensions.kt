package com.dohman.bdeln.util

import kotlin.random.Random

fun Int.randomize(): Int = Random.nextInt(this)

fun List<Int>.isWrongGuess() = this.isEmpty()