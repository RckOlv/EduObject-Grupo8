# Compilador EduObject - Grupo 8 

Este repositorio contiene el código fuente correspondiente a la **Parte 3 del Trabajo Integrador** para el diseño de un mini-lenguaje orientado a objetos.

El proyecto consiste en la implementación de un **analizador léxico y sintáctico**, desarrollado en Java, que valida la estructura y gramática del lenguaje EduObject. Además, incluye un entorno gráfico (Mini IDE) para facilitar la carga y análisis del código fuente.



### 👥 Integrantes 
* Domínguez Kevin Gabriel
* Grabovieski Matías Alejandro
* Olivieri Ricardo

---

## 🛠️ Tecnologías Utilizadas
Para el desarrollo de este compilador se utilizaron las siguientes herramientas:
* **Java (JDK 25):** Lenguaje base del proyecto.
* **Maven:** Gestor de dependencias y ciclo de construcción.
* **JFlex:** Generador del analizador léxico.
* **CUP (Constructor of Useful Parsers):** Generador del analizador sintáctico LALR.
* **Java Swing:** Framework para el desarrollo del "Mini IDE".

---

## ⚙️ Requisitos Previos
Para poder compilar y ejecutar el proyecto en tu máquina local, es necesario contar con:
1. **Java Development Kit (JDK) 25**.
2. **Apache Maven** instalado y configurado en las variables de entorno (`PATH`).

---

## 🚀 Cómo compilar y ejecutar

El proyecto está configurado para automatizar la generación de las clases de JFlex y CUP, compilar el código de Java y ejecutar la interfaz gráfica en un solo paso utilizando Maven.

1. **Clonar el repositorio:**
   ```bash
   git clone (https://github.com/RckOlv/EduObject-Grupo8.git)
   cd EduObject-Grupo8

2. **Ejecutar el compilador y el Mini IDE:**
   Abre tu terminal en la raiz del proyecto y ejecuta el siguiente comando:

    ```bash
   mvn clean compile exec:java -Dexec.mainClass="compilador.MiniIDE"