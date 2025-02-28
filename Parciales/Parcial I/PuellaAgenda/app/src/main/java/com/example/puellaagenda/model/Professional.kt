package com.example.puellaagenda.model
import java.io.Serializable

data class Professional (
    val pName: String,
    val pProfession: String,
    val pExperience: String,
    val pClients: String,
    val pImage: Int,
    val pMail: String,
    val pPhone: String
) : Serializable