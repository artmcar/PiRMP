package com.example.pirmp_5_makaryanaa
import java.io.Serializable

data class AuthorPoems(val id: Int, val name: String, val poems: List<String>) : Serializable
