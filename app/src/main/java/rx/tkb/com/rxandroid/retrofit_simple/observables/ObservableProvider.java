package rx.tkb.com.rxandroid.retrofit_simple.observables;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.tkb.com.rxandroid.retrofit_simple.models.Post;
import rx.tkb.com.rxandroid.retrofit_simple.request.ServiceGenerator;

public class ObservableProvider {
    //TODO finish the Observable provider task.
    public static Observable<Post> getPostsObservable(){
        return ServiceGenerator.getRequestApi()
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<List<Post>, ObservableSource<Post>>) posts -> {
                    //adapter.setPosts(posts);
                    return Observable.fromIterable(posts).subscribeOn(Schedulers.io());
                });
    }

}
