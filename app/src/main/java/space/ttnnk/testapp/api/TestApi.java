package space.ttnnk.testapp.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import space.ttnnk.testapp.api.response.IdListResponse;
import space.ttnnk.testapp.api.response.IdUnitResponse;

public interface TestApi {
    @GET("entities/getAllIds")
    Observable<IdListResponse> getAllIds();

    @GET("object/{id}")
    Observable<IdUnitResponse> getId(@Path("id") int id);

}
