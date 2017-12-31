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
    you'll need to create some additional threads.
    
    Android does provide a number of ways of creating additional threads, such as services and IntentServices, but none of
    these solutions are particularly easy to implement, and they can quickly result in complex, verbose code that’s prone to
    errors.
    
    RxJava aims to take the pain out of creating multi-threaded Android apps, by providing special schedulers and operators.
    These give you an easy way of specifying the thread where work should be performed and the thread where the results of
    this work should be posted.

    * **subscribeOn(Scheduler):** By default, an Observable emits its data on the thread where the subscription was
    declared,**i.e.** where you called the **.subscribe** method. In Android, this is generally the main UI thread. You can
    use the **subscribeOn()** operator to define a different Scheduler where the Observable should execute 
    and emit its data.
   
    * **observeOn(Scheduler):** You can use this operator to redirect your Observable’s emissions to a different Scheduler,
    effectively changing the thread where the Observable’s notifications are sent, and by extension the thread where its
    data is consumed.
    
    RxJava comes with a number of schedulers that you can use to create different threads, including:

    * **Schedulers.io():** Designed to be used for IO-related tasks.
    * **Schedulers.computation():** Designed to be used for computational tasks. By default, the number of threads 
        in the computation scheduler is limited to the number of CPUs available on your device.
    * **Schedulers.newThread():** Creates a new thread.
    
    *Expample:*
    ```javascript 
       observable.subscribeOn(Schedulers.newThread());
    ```
    *Another Example:*
   ```javascript 
       Observable.just(1, 2, 3)
          .subscribeOn(Schedulers.newThread())
          .subscribe(Observer);
   ```
   Another long-standing problem with multithreading on Android is that you can only update your app's UI from the main
   thread. Typically, whenever you need to post the results of some background work to your app's UI, you have to create a
   dedicated Handler.But, RxJava has a much more straightforward solution.

   This means that with just two lines of code, you can create a new thread and send the results of work performed on this
   thread to Android's main UI thread:
   ```javascript    
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
   ```
      
 ### Adding RxJava to Android Studio
   ```javascript    
      dependencies {
             compile 'io.reactivex.rxjava2:rxjava:2.0.5'
       } 
   ```
### Create an Observable
An Observable is similar to an Iterable in that, given a sequence, it'll iterate through that sequence and emit each item, although Observables typically don't start emitting data until an Observer subscribes to them.

Each time an **Observable** emits an item, it notifies its assigned **Observer** using the **onNext()** method. Once an **Observable** has transmitted all of its values, it terminates by calling either:

  * **onComplete:** Called if the operation was a success.
  * **onError:** Called if an Exception was thrown.

**Observable Example:**

  ```javascript  
       Observable<String> stringObservable
                = Observable.just("Hello"); // This code emmits a single String "Hello"
   ```

### Create an Observer:
**Observers** are objects that you assign to an **Observable**, using the **subscribe()** operator. Once an **Observer** is subscribed to an **Observable**, it’ll react whenever its **Observer** emits one of the following:

* **onNext:** The Observable has emitted a value.
* **onError:** An error has occurred.
* **onComplete:** The Observable has finished emitting all its values.

**Example:**
 ```javascript   
        Observer<String> stringObserver = new Observer<String>() {
            @Override
            public void onError(Throwable e) {
                // Called when the observable encounters an error
                Log.d(TAG,"onError: "+ e.getStackTrace().toString());
            }

            @Override
            public void onComplete() {
                // Called when the observable has no more data to emit
                Log.d(TAG,"onComplete");
            }

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                // Called each time the observable emits data
                Log.d(TAG,"onNext: "+s);
            }
        };
   
    stringObservable.subscribe(stringObserver); // Subscribed Observer in Observable
 ```

## Example of some observable:
  
 * **Observable.just():**
 
     You can use the **.just()** operator to convert any object into an **Observable.** The result **Observable** will then
     emit the original object and complete.
     
     **Example:**
      See above example

 * **Observable.fromArray():**
 
    The **.from()** operator allows you to convert a **collection of objects** into an **observable** stream. You can
    convert an array into an Observable using **Observable.fromArray**, a Callable into an Observable using
    **Observable.fromCallable**, and an Iterable into an Observable using **Observable.fromIterable**.
    
    **Example:**
    ```javascript 
      // Emits each item of the array, one at a time.
       Observable<Integer> myArrayObservable = Observable.fromArray(new Integer[]{1,2,3,4,5,6});
    ```
 * **Observable.range():**
 
    You can use the **.range()** operator to emit a range of sequential integers. The first integer you provide is the
    initial value, and the second is the number of integers you want to emit.
    
    **Example:**
    ```javascript   
       Observable<Integer> observable = Observable.range(0, 5);
    ```
 * **Observable.interval():**
 
   This operator creates an Observable that emits an infinite sequence of ascending integers, with each emission separated
   by a time interval chosen by you.
   
    **Example:**
    ```javascript   
       Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
    ```

 * **Observable.empty():**

   The **empty()** operator creates an Observable that emits no items but terminates normally, which can be useful when you
   need to quickly create an Observable for testing purposes.
   
    **Example:**
    ```javascript   
       Observable<String> observable = Observable.empty();
    ```
