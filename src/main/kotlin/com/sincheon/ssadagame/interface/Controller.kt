package com.sincheon.ssadagame.`interface`

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "hello")
@RestController
class Controller {
    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }
}
