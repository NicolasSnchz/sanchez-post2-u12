# ADR-003: Persistencia y Sincronizacion

## Estado

Aceptado — 2026

## Contexto

La aplicacion debe estar preparada para guardar informacion localmente y evolucionar hacia sincronizacion offline-first.  
El proyecto anterior ya trabajo conceptos como syncStatus, updatedAt, WorkManager y Last-Write-Wins.

## Alternativas consideradas

1. Solo memoria local
   + Simple para prototipo.
   - Los datos se pierden al cerrar la app.

2. Room Database
   + Persistencia local robusta.
   + Compatible con Flow.
   + Facil integracion con WorkManager.
   - Requiere configuracion adicional.

3. Backend remoto directo sin cache local
   + Datos centralizados.
   - La app falla o pierde utilidad sin internet.

## Decision

Se usara Room como base de persistencia local y se dejara preparada la arquitectura para sincronizacion offline-first con WorkManager y resolucion Last-Write-Wins.

## Consecuencias

Se acepta una implementacion inicial mas estructurada, pero se gana soporte para uso sin conexion, sincronizacion futura y mejor experiencia de usuario.
