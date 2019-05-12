package rx.tkb.com.rxandroid.form;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;
import rx.tkb.com.rxandroid.R;
import rx.tkb.com.rxandroid.RXUtility;

public class FormValidationActivity extends AppCompatActivity {

    @BindView(R.id.edtFirstName)
    EditText edtFirstName;

    @BindView(R.id.edtLastName)
    EditText edtLastName;

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.btnRegistration)
    Button btnRegistration;

    private String TAG = this.getClass().getSimpleName();

    private DisposableSubscriber<Boolean> disposableSubscriber = null;
    private Flowable<CharSequence> firstNameChangeObservable;
    private Flowable<CharSequence> lastNameChangeObservable;
    private Flowable<CharSequence> emailChangeObservable;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        unbinder = ButterKnife.bind(this);

        firstNameChangeObservable = RxTextView.textChanges(edtFirstName).skip(1).toFlowable(BackpressureStrategy.LATEST);
        lastNameChangeObservable = RxTextView.textChanges(edtLastName).skip(1).toFlowable(BackpressureStrategy.LATEST);
        emailChangeObservable = RxTextView.textChanges(edtEmail).skip(1).toFlowable(BackpressureStrategy.LATEST);

        combineLatestEvents();

        btnRegistration.setEnabled(false);

        //RX Button click
        RxView.clicks(btnRegistration)
                .subscribe(aVoid -> Toast.makeText(FormValidationActivity.this,"Clicked",Toast.LENGTH_LONG).show());

    }


    private void combineLatestEvents() {
        disposableSubscriber = new DisposableSubscriber<Boolean>() {
            @Override
            public void onNext(Boolean isValidForm) {
                if (isValidForm) {
                    btnRegistration.setEnabled(true);
                    btnRegistration.setBackgroundColor(ContextCompat.getColor(FormValidationActivity.this, R.color.colorPrimaryDark));
                } else {
                    btnRegistration.setEnabled(false);
                    btnRegistration.setBackgroundColor(ContextCompat.getColor(FormValidationActivity.this, R.color.colorGray));
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError       ==/   " + t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete       ==/    called");
            }
        };

        Flowable.combineLatest(firstNameChangeObservable, lastNameChangeObservable, emailChangeObservable,
                (firstName, lastName, email) -> {
                    boolean isValidFirstName = firstName != null && firstName.length() > 3;
                    if (!isValidFirstName) {
                        edtFirstName.setError("Invalid first name");
                    }

                    boolean isValidLastName = lastName != null && lastName.length() > 3;
                    if (!isValidLastName) {
                        edtLastName.setError("Invalid last name");
                    }

                    boolean isValidEmail = email != null && RXUtility.isValidEmail(String.valueOf(email));
                    if (!isValidEmail) {
                        edtEmail.setError("Invalid email addess");
                    }

                    return isValidFirstName && isValidLastName && isValidEmail;
                })
                .subscribe(disposableSubscriber);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        disposableSubscriber.dispose();
    }
}
