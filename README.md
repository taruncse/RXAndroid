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
    RxJava also provides a standard workflow that you can use to handle all data and events across your application—create
    an Observable, create an Observer, assign the observable to that observer, rinse and repeat.
    
  * **Multithreading Made Easy**
    The problem is that Android is single-threaded by default, so if your app is ever going to multi-task successfully then
    you'll need to create some additional threads.Android does provide a number of ways of creating additional threads, such
    as services and IntentServices, but none of these solutions are particularly easy to implement, and they can quickly
    result in complex, verbose code that’s prone to errors.RxJava aims to take the pain out of creating multi-threaded
    Android apps, by providing special schedulers and operators. These give you an easy way of specifying the thread where
    work should be performed and the thread where the results of this work should be posted.
   
    Expample:
    ```javascript 
       observable.subscribeOn(Schedulers.newThread());
    ```
