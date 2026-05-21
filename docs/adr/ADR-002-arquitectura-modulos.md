# ADR-002: Arquitectura de Modulos

## Estado

Aceptado — 2026

## Contexto

El proyecto integrador necesita una estructura clara para separar responsabilidades entre UI, dominio, datos y funcionalidades.  
La guia solicita estructura profesional con modulos app, feature y core.

## Alternativas consideradas

1. Modulo unico con separacion por paquetes
   + Configuracion inicial sencilla.
   + Menor complejidad de Gradle.
   - Escala peor cuando el proyecto crece.
   - Menor aislamiento entre funcionalidades.

2. Arquitectura multi-modulo
   + Separacion clara de responsabilidades.
   + Permite compilar features de forma independiente.
   + Mejor organizacion para evaluacion y crecimiento.
   - Mayor configuracion inicial.

## Decision

Se adopta una arquitectura multi-modulo:

- app: punto de entrada.
- core/domain: entidades y contratos.
- core/data: persistencia, repositorios e integraciones futuras.
- core/ui: Design System y componentes reutilizables.
- feature: funcionalidades independientes.

## Consecuencias

Se acepta mayor configuracion inicial de Gradle, pero se obtiene una arquitectura mas profesional, escalable y coherente con el proyecto integrador.
