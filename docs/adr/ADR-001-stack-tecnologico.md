# ADR-001: Stack Tecnologico

## Estado

Aceptado — 2026

## Contexto

El proyecto integrador sera una aplicacion Android para gestion de notas con soporte para arquitectura modular, interfaz moderna y futura sincronizacion offline-first.  
El equipo necesita una base tecnica estable, compatible con Android Studio, Gradle 8.x y Version Catalogs.

## Alternativas consideradas

1. Android nativo con Kotlin y Jetpack Compose
   + Integracion directa con el ecosistema Android.
   + Mejor compatibilidad con Gradle, Room, WorkManager y pruebas.
   - Requiere conocimiento especifico de Kotlin y Compose.

2. Flutter
   + Desarrollo multiplataforma.
   + UI rapida de construir.
   - Menor alineacion con los contenidos Android nativos de la asignatura.

3. Android XML tradicional
   + Conocido y estable.
   - Menos moderno y menos alineado con Compose.

## Decision

Se elige Android nativo con Kotlin, Jetpack Compose, Gradle Version Catalogs, Room, WorkManager y arquitectura modular.

## Consecuencias

Se acepta una curva tecnica mayor, pero se gana una base mas escalable, mantenible y coherente con los temas vistos en la asignatura.
