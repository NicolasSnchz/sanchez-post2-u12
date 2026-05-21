# sanchez-post1-u12

Proyecto Android para la actividad Unidad 12 - Post Contenido 1: Proyecto Integrador.

## Nombre del proyecto

NotesSync Integrador

## Problem statement

Los usuarios necesitan una aplicacion movil para gestionar notas importantes incluso cuando no tienen conexion a internet. La aplicacion debe permitir crear, consultar y revisar notas de forma clara, manteniendo una base tecnica preparada para crecimiento, sincronizacion futura y separacion profesional de responsabilidades.

## Integrante

- Nicolas Sanchez

## Arquitectura

El proyecto usa una estructura modular:

- app: punto de entrada de la aplicacion.
- core/domain: entidades y contratos principales.
- core/data: espacio preparado para persistencia, repositorios y sincronizacion.
- core/ui: espacio preparado para Design System y componentes reutilizables.
- feature/notes: modulo funcional de notas.

## Diagrama de arquitectura

![Diagrama de arquitectura](docs/architecture-diagram.png)

## ADRs

Los ADRs principales estan en docs/adr/:

- ADR-001: Stack Tecnologico
- ADR-002: Arquitectura de Modulos
- ADR-003: Persistencia y Sincronizacion

Cada ADR contiene Estado, Contexto, Alternativas consideradas, Decision y Consecuencias.

## Version Catalog

El proyecto centraliza versiones en:

gradle/libs.versions.toml

## Comandos de verificacion

.\gradlew.bat assembleDebug

.\gradlew.bat :feature:notes:assembleDebug

## Prototipo Figma

Enlace al prototipo navegable:

https://www.figma.com/design/wzcqCFHFmx1IfDAi3MwbVZ/NotesSync-Integrador---U12-Post-1?node-id=0-1&t=IfkSbNG0wbOyRFzv-1

El prototipo incluye:

- Splash
- Login
- Lista de notas
- Crear nota
- Detalle nota
- Offline Sync
- Design System

## Design System minimo

Colores:

- Primary: #1E3A8A
- Secondary: #2563EB
- Background: #F8FAFC
- Error: #DC2626
- On Primary: #FFFFFF

Tipografia:

- Headline
- Title
- Body
- Label

Componentes:

- Button
- TextField
- Card
- TopAppBar
- SyncStatusChip

## Evidencia del prototipo

![Prototipo Figma](evidencias/01_figma_prototipo.png)

## Resultado

El repositorio contiene la base tecnica del proyecto integrador, estructura modular, ADRs, Version Catalog, diagrama de arquitectura, prototipo Figma documentado y evidencia visual del Design System.
