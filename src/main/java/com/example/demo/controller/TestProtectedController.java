package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Recursos Protegidos", description = "Endpoints que exigem autenticação JWT")
@SecurityRequirement(name = "bearerAuth") 
public class TestProtectedController {

    @Operation(summary = "🔓 Endpoint acessível por qualquer usuário autenticado (USER ou ADMIN)")
    @GetMapping("/hello")
    public String hello() {
        return "✅ Olá! Você acessou um endpoint protegido com sucesso!";
    }

    @Operation(summary = "🔒 Endpoint acessível apenas por usuários com a role ADMIN")
    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/admin")
    public String adminOnly() {
        return "🔐 Bem-vindo, Administrador! Este é um recurso restrito.";
    }
}
