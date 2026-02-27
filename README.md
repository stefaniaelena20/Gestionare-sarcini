    INTERFATA:
Am o fereastra cu 7 butoane

    Butoane:
 -Add Employee
 -Add Task
 -Assign Task
 -View Stats
 -Save
 -Load
 -Set Task Status

    Cum functioneaza?
Interfata aplicatiei este o fereastra grafica(realizata cu Java Swing)
care permite utilizatorului sa gestioneze angajati si sarcini. 
Prin butoane putem:
    Adauga angajati si task uri(simple sau complexe).
    Asigna task-uri angajatilor din lista.
    Modifica statusul unui task(Completed sau Uncompleted).
    Vizualiza statistici despre orele lucrate de ficare angajat.
    Salva/ incarca datele(prin serializare in fisier) pentru a 
    pastra progresul.
Totul este organizat intr-o interfata simpla cu butoane mari,
iar datele sunt gestionate automat in fundal prin clasele 
TaskManagement si Serialization.