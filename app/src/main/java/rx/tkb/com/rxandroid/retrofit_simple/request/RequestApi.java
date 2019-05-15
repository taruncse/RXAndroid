package rx.tkb.com.rxandroid.retrofit_simple.request;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.tkb.com.rxandroid.retrofit_simple.models.Comment;
import rx.tkb.com.rxandroid.retrofit_simple.models.Post;

public interface RequestApi {

    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    Observable<List<Comment>> getComments(
            @Path("id") int id
    );
}
