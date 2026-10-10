# Adventure LCR

Legacy Code Retreat on adventure game taken from an old Atari ST magazine
by [Alex Leavins](https://github.com/ggnkua/Atari_ST_Sources/tree/master/C/Alex%20Leavins/ADVENTUR.STQ).

# TODO

* First Use [Golgen Master](https://stevenschwenke.de/whatIsTheGoldenMasterTechnique) technic to build a set of tests,
* Then, refactor the code using your favorite technics to bring
  it the more modern standards (SOLID, DDD, etc.)
* One concern could be maintainability and extensibility following Open/Close principle, how to easily change the games assets:
  * Rooms
  * Commands
  * Objects
  * etc.

# Detailed Steps and Hints

* **Mock Input/Output**: The code as no external dependencies rather than input/output, 
  this as to be abstracted in order to be tested, an interface representing the console interaction
  could be introduced and injected in the main object.
* **Variable Scope**: Global and static variables reduce the possibility of refactoring,
  look for global variables which could actually be transformed in to locals.
* **Scattered arrays**: Transform multiple arrays into an array of objects of related properties.
* **Arrays used as Set/Map**: Some arrays are used as Sets or Maps by using the object identifier as index into the array, use proper Set and Map when appropriate.
* **Switch statement**: Switch statement usually hide a polymorphic behavior or a registry pattern to access data,
  look for switches and introduce new interfaces and implementation classes for each case. These classes could already exist
  as data structure and now becomes real objects with behavior.
