# RXAndroid

### What is RX-Java
RxJava, a popular open-source implementation of the ReactiveX library that brings reactive programming to the Java Virtual Machine (JVM). RxJava is designed to take the pain out of working with asynchronous streams of data.

RxJava is a library that lets you create applications in the reactive programming style. At its core, reactive programming provides a clean, efficient way of processing and reacting to streams of real-time data, including data with dynamic values.

RxJava pretty much treats everything as a stream of data—everything from variables to properties, caches, and even user input events like clicks and swipes.

The data emitted by each stream can either be a value, an error, or a "completed" signal, although you don’t necessarily have to implement the last two

RX-Java use Observer software design pattern. Essentially, in RxJava you have ***Observable*** objects that **emit a stream** of data and then terminate, and ***Observer** objects that subscribe to Observables. An **Observer receives a notification** each time their assigned Observable emits **a value, an error, or a completed signal**.


### So at a very high level, RxJava is all about:

  * Creating an **Observable**.
  * Giving that **Observable** some data to emit.
  * Creating an **Observer**.
  * Assigning the **Observer** to an **Observable**.
  * Giving the **Observer** tasks to perform whenever it receives an emission from its assigned **Observable**.


### Why RxJava?
  * **More Concise, Readable Code:**
    RxJava simplifies the code required to handle data and events by allowing you to describe what you want to achieve, 
    rather  than writing a list of instructions for your app to work through. RxJava also provides a standard workflow 
    that you can use to handle all data and events across your application
