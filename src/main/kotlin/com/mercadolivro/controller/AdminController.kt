package com.mercadolivro.controller


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin")
class AdminController() {

    @GetMapping("/reports")
    fun report(): String {
        return "this a Report. Only Admin can see it!"
    }


}