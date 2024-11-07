package com.es.ApiRestPrueba.controller

import com.es.ApiRestPrueba.model.Libro
import org.springframework.web.bind.annotation.*

@RestController
class LibroController {

    //vamos a hacer un crud de libros
    //vamos a hacer un metodo que acepte C (Crear) -> POST
    // vamos a hacer un metodo que acepte R (Leer) -> GET
    // vamos a hacer un metodo que acepte U (Actualizar) -> PUT
    // vamos a hacer un metodo que acepte D (Eliminar) -> DELETE

    private val libros = mutableListOf<Libro>(
        Libro(1, "Dictadura", "Aventura", "Diego Linares")
    )

    @PostMapping("/libros")
    fun insert(
        @RequestBody libroNuevo: Libro
    ): Libro? {
        val libroEnLista = libros.find { it.id == libroNuevo.id }
        if (libroEnLista != null) {
            return null
        }else{
            libros.add(libroNuevo)
            return libroNuevo
        }
    }

    @GetMapping("/libros/{id}")
    fun getById(
        @PathVariable id: String
    ): Libro? {

        return libros.find { it.id == id.toLongOrNull() }
    }

    @PutMapping("/libros")
    fun update(
        @RequestBody libroNuevo: Libro
    ): Libro? {
        val libroEnLista = libros.find { it.id == libroNuevo.id }
        if (libroEnLista == null) {
            return null
        }else{
            libroEnLista.id = libroNuevo.id
            libroEnLista.nombre = libroNuevo.nombre
            libroEnLista.genero = libroNuevo.genero
            libroEnLista.autor = libroNuevo.autor
            return libroEnLista
        }
    }

    @DeleteMapping("/libros/{id}")
    fun deleteById(
        @PathVariable id: String
    ): Boolean {
        val libro = libros.find { it.id == id.toLongOrNull() }
        return if (libro != null) {
            libros.remove(libro)
        }else{
            false
        }
    }
}