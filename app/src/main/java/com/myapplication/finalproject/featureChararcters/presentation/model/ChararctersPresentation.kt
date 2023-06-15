package com.myapplication.finalproject.featureChararcters.presentation.model

data class ChararctersPresentation(
    var info    : InfoPresentation?              = null,
    var results : ArrayList<CharacterPresentation>? = null
)
data class InfoPresentation (
    var count : Int?    = null,
    var pages : Int?    = null,
    var next  : String? = null,
    var prev  : String? = null
)
data class OriginPresentation (
    var name : String? = null,
    var url  : String? = null
)
data class LocationPresentation (
    var name : String? = null,
    var url  : String? = null
)
data class CharacterPresentation(
    var id       : Int?              = null,
    var name     : String?           = null,
    var status   : String?           = null,
    var species  : String?           = null,
    var type     : String?           = null,
    var gender   : String?           = null,
    var origin   : OriginPresentation?           = OriginPresentation(),
    var location : LocationPresentation?         = LocationPresentation(),
    var image    : String?           = null,
    var episode  : ArrayList<String> = arrayListOf(),
    var url      : String?           = null,
    var created  : String?           = null
)