package rx.tkb.com.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basicFormatUsingString();
    }

    // This method is to understand a basic example using a single string.
    private void basicFormatUsingString() {

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
                Log.d(TAG,"onSubscribe: "+d.isDisposed());
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
