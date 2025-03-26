
- Exercici
Hem de desenvolupar una aplicació per gestionar un Escape Room virtual, on els usuaris puguin gaudir d'aventures emocionants i resoldre enigmes desafiant. En el nivell 1, com a persistència MySQL.

L'aplicació ha de complir amb els següents requisits:

En el nostre Escape Room virtual, que té un nom específic, oferim una varietat de sales temàtiques, pistes intrigants i objectes de decoració únics.
Cada sala té assignat un nivell de dificultat per proporcionar una experiència equilibrada i desafiadora
Les pistes estan dissenyades amb temes específics per guiar els jugadors en la cerca de solucions
Els objectes de decoració contribueixen a crear una atmosfera immersiva i memorable a cada sala, utilitzant diferents tipus de materials.
Tots els elements tenen un preu associat que reflecteix el seu valor en el joc
El nostre Escape Room virtual ha de mantenir un inventari actualitzat de totes les sales, pistes i objectes de decoració disponibles.
A més, portarem un registre del valor total de l'inventari per tenir una visió clara dels nostres actius.
L'aplicació oferirà una funcionalitat per emetre certificats de superació d'enigmes, on es registraran els assoliments aconseguits pels jugadors durant la seva experiència a l'Escape Room.
A més, se'ls podran atorgar possibles regals o recompenses com a reconeixement per la seva habilitat i destresa en resoldre els reptes plantejats.
Pel que fa a les funcionalitats a mostrar per pantalla, s'espera que inclogui com a mínim les següents:

Crear un nou Escape Room virtual.
Afegir una nova sala amb el seu respectiu nivell de dificultat.
Incorporar pistes temàtiques per enriquir l'experiència de joc.
Introduir objectes de decoració per ambientar les sales de manera única.
Mostrar l'inventari actualitzat, mostrant les quantitats disponibles de cada element (sales, pistes i objectes de decoració).
Visualitzar el valor total en euros de l'inventari de l'Escape Room virtual.
Permetre la retirada de sales, pistes o objectes de decoració de l'inventari.
Generar tiquets de venda per als diferents jugadors/es.
Calcular i mostrar el total d'ingressos generats per vendes de tiquets de l'Escape Room virtual.
Notificar als usuaris sobre esdeveniments importants a l'Escape Room, com l'addició de noves pistes, la finalització d'una sala, etc.
Els usuaris interessats en aquests esdeveniments podran sol·licitar registrar-se per poder rebre notificacions quan es produeixin esdeveniments rellevants


Tecnologies Utilitzades:
	Java
	Lombok
	MySQL 8.0
	
Requisits:
	Lombok
	Java 21 o superior.
	
Instal·lació:
	Cal instal·lar Java 21 o superior.
	Cal descarregar lombok a l'ordinador i instal·lar.
		https://projectlombok.org/download
		java -jar lombok.jar
	Cal instal·lar el plugin de lombok en el IDE de treball.
		https://projectlombok.org/setup/intellij (En el cas de l'IDE intellij)
	Cal instal·lar el MySQl 8.0 i desplegar l'entorn.
	Modificar la informació de l'arxiu  hibernate.cfg.xml per adaptar la crida al servidor si no és localhost:3306
	
Execució
    Descarregar el projecte.
	Compilar el projecte.
	Executar el main.
	
Desplegament:
    Cal desplegar els compilats en el lloc determinat.

Contribucions:
	Llegeix aquest document.
	Revisa les "issues" obertes en cas de voler participar en el projecte.
    Descarrega una copia en local o clona el projecte per treballar sobre ella.
	
	Pots crear tant crear un "fork" del mateix, com crear una branca sobre la que desenvolupar els teu codi.
	
	En acabar, si el que vols és participar del projecte original, pots realitzar un "pull request" per tal de demanar d'unir el teu codi amb el principal del repositori.
	