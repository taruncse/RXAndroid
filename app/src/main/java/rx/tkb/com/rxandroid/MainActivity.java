package rx.tkb.com.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //basicFormatUsingString();

        observableJust();
    }

    private void AnotherWay() {
        final String TAG = "AnotherWay";
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
                                                               @Override

                                                               public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                                                                   //Use onNext to emit each item in the stream//
                                                                   e.onNext(1);
                                                                   e.onNext(2);
                                                                   e.onNext(3);
                                                                   e.onNext(4);

                                                                   //Once the Observable has emitted all items in the sequence, call onComplete//
                                                                   e.onComplete();
                                                               }
                                                           }
        );



        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: All Done!");
            }
        };

//Create our subscription//
        observable.subscribe(observer);
    }

    // This method is to understand a basic example using a single string.
    private void observableJust() {

        final String TAG = "basicFormatUsingString";

        //Basic format of creating a Observable.
        Observable<String> stringObservable
                = Observable.just("Hello"); // Emits a string "Hello"

        //Basic format of creating a Observer.
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

        stringObservable.subscribe(stringObserver);
    }
}
