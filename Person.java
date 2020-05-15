/**
 * Esta clase Person guarda el nombre de la persona
 */
public class Person {
 // Variable nombre	
 private String name;

 // Constructor por defecto
 public Person() {
 }

 /* Constructor
  * @param name El nombre de la persona
  */
 public Person(String name){
   this.name = name;
 }

 /* Método set de nombre
  * @param n El nombre de la persona
  */
 public void setName(String n) {
     name = n;
 }

 /* Método get de nombre
  * @return name de la persona
  */
 public String getName() {
     return name;
 }
}
