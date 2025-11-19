# MINIPROYECTO 3 – Sistema de Batalla RPG con Modelo Vista Controlador (MVC)
Materia: Programación Orientada a EventosAutores:Andrés Felipe Quiceno – 2477362Yonier Alejandro Vega – 2477056Nicolás Cardona – 2477349

# Descripción 

En esta tercera entrega se adaptó el mini proyecto 2 a modelo vista controlador . Separando la lógica de la interfaz ya que todo quedó en carpetas diferentes y todo se conecta por un controlador que se comunica tanto con el modelo como con la vista. Todo para simular la épica Batalla del dragón Quest.

# Mejoras Implementadas 
* Se modificó pro completo la GUI con el objetivo de crear una más intuitiva para el usuario .
* Se le agregaron imágenes a los héroes y monstruos con el objetivo de mejorar la experiencia 
* Se le agrego música de batalla del juego para mejorar la ambientación 
* Se corrigió el Bug de congelamiento utilizando Swing worker. El cual es una clase aparte que maneja un hilo especial para ejecutar tareas pesadas dentro de una interfaz debido a que el hilo de swing estaba sobrecargado por el hecho de que la lógica es pesada ya que es muy densa en código.
* Se le agrego el modelo vista controlador para tener la interfaz más organizada y estructurada.
# Como Funciona las Partidas 
* Primero se selecciona al héroe con el cual batallar cabe decir que seleccionó la imagen del héroe correspondiente 
* Segundo escojo al monstruo al cual atakar seleccióno la imagen del monstruo correspondiente 
* Tercero se habilita las opciones de atakar, defenderse,habilidad. Escojo cual quiera de las tres y aparece lo sucedido en el JTextArea 
* Al final cuando se acaba la Partida aparece el botón de volver a jugar y comienza una partida nueva .
# Mejoras para Futuras Actualizaciones a Tener en cuenta

*Añadirle Uso de objetos ya que el dragon quest original los utiliza como por ejemplo (pociones de vida,de poder, de veneno etc).
*Implementar daños en area para algunas habilidades que lo requieren Implementar mas Habilidades por cada personaje a escojer con su respectivo coste de puntos magicos(MP)
* Ponerlo en lógica más fiel al dragón Quest vlll que es una lógica por turnos y ordenada por velocidad. Nosotros la tenemos es en 1 vs 1 y el más rápido es que hace su turno primero.

# Ejecución 
*Paso 1 en GitHub Meterse a nuestro repositorio enlace: https://github.com/yonier-123/MINIPROYECTO3.git   copiar y pegarlo en el navegador de preferencia
*Paso 2: darle click izquierdo a code y hay en la parte de abajito darle click a descargar zip

*Paso 3: darle click derecho al archivo descargado y extraerlo

*Paso 4: abrir Visual Studio Code y darle click a las 3 rayitas horizontales que estan al extremo izquierdo en la parte de arriba luego de eso darle click a file

*paso 5: en file darle click open file y buscar la carpeta extraida y darle click

*paso 6: darle click a src y luego darle click a app para hay si ejecutar nuestro sistema de RPG correctamente. Compilar y ejecutar desde App.java.# Files.
