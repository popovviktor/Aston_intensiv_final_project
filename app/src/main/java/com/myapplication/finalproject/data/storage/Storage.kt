package com.myapplication.finalproject.data.storage

import com.myapplication.finalproject.domain.models.CharactersDomain

interface Storage {
    fun save(characters: CharactersDomain)
    fun get():CharactersDomain
}