# Notes

## Advantages of the Kotlin Data Classes

ref: https://spring.io/guides/tutorials/spring-boot-kotlin

### Common Methods

Kotlin data classes automatically generate common methods such as:

* **equals()**: To compare instances.
* **hashCode()**: To use objects in hashed collections.
* **toString()**: To easily print the object’s state.
* **copy()**: To create shallow copies of instances with optional changes.

### Immutability with `val` 

Data classes use val (immutable) by default for properties, promoting immutability in your domain model. Immutable objects reduce side effects, simplify debugging, and improve thread-safety.

### Enhanced Null Safety

One of Kotlin's standout features is its null safety. Unlike Java, where null references are a common cause of runtime exceptions (such as the infamous NullPointerException), Kotlin provides built-in mechanisms to prevent these errors at compile time.

* Non-nullable types: These are types that cannot hold a null value. If you try to assign null to a non-nullable type, the compiler will throw an error.

````kotlin
val name: String = "John" // Non-nullable String
name = null // Compilation error!
````

* Nullable types: These are types that can hold a null value. Nullable types are declared by appending a question mark (?) to the type.

````kotlin 
val name: String? = "John" // Nullable String
name = null // Allowed
````

## Overriding the hashCode() in Kotlin Data Class

Overriding the hashCode() function in your Account entity class is essential to maintain the integrity and proper behavior when instances of this class are used in hashed data structures such as HashMap, HashSet, or other similar collections.

To avoid the looping between the model relationships, we should override the hashCode method and remove the relationships

## @Component Vs @Service

### @Component:

* It is a generic stereotype annotation. 
* You can use it on any class that you want to register as a Spring-managed bean, but it doesn't imply any specific role for the class.
* It is a generic annotation for any Spring component.

### @Service:

* @Service is a specialized form of @Component, and it is used specifically to indicate that a class performs service layer logic.
* By using @Service, you're indicating that the class contains business logic or service operations.
* While @Service functions the same way as @Component, using @Service makes your intention clearer for others reading the code, since the class is part of the service layer.