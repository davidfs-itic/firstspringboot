package com.dfdocencia.backend.firstspringboot.controller

import com.dfdocencia.backend.firstspringboot.model.Torneig
import com.dfdocencia.backend.firstspringboot.repositori.TorneigRepositori
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/tornejos")
class TornejosController(private val torneigRepositori: TorneigRepositori) {

    @PostMapping("/guardar")
    fun guardar(@RequestBody TorneigBody: Torneig):Torneig{
       return torneigRepositori.save(TorneigBody)
    }

    @GetMapping("/llista")
        fun Llista(): List<Torneig> = torneigRepositori.findAll()

    @GetMapping("/llista/nom/{nom}")
    fun trobarTorneigPerNom(@PathVariable("nom") nom: String): Torneig? = null
    @GetMapping("/llista/data/{data}")

    fun trobarTorneigPerData(@PathVariable("data") data: String): Torneig? = null

    fun trobarTorneigPerId(id: Long): Torneig? = null



}