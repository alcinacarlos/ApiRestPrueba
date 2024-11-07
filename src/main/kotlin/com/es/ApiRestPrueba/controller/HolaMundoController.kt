package com.es.ApiRestPrueba.controller

import com.es.ApiRestPrueba.model.Saludo
import com.es.ApiRestPrueba.model.Usuario
import org.springframework.web.bind.annotation.*

//las peticioens que entren, el dispatcher va a repartir al controller adecuado
@RestController //con esta anotacion spring ya sabe que esta clase es un controlador
class HolaMundoController {

    val mapaUsuarios:MutableMap<String, String> = mutableMapOf("diego" to "1234", "hola" to "369uve")
    @GetMapping("/saludo")
    fun getHolaMundo(
        @RequestParam(name = "nombre") nombre:String
    ):Saludo{
        return Saludo("Hola", nombre)
    }

    @GetMapping("/user")
    fun getCredential(
        @RequestParam(name = "nombre") nombreU:String
    ):Usuario{
        val password = mapaUsuarios[nombreU]
        return if (password != null) Usuario(nombreU, password) else return Usuario("Not found", "Not found")
    }

    @PostMapping("/usercreate")
    fun postCredential(
        @RequestParam(name = "nombre") nombreU:String,
        @RequestParam(value = "password") password:String
    ):Usuario{
        mapaUsuarios[nombreU] = password
        return Usuario(nombreU, password)
    }
    @DeleteMapping("/userdel")
    fun delCredential(
        @RequestParam(name = "nombre") nombreU:String,
    ):String{
        mapaUsuarios.remove(nombreU)
        return "User deleted"
    }

}