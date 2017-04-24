package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.repository.DataRepository;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * Created by MienHV1 on 4/24/2017.
 */

public class UpLoadImageFileUserCase extends RxUserCase<DataResponse<ImageRespone>,UpLoadImageFileUserCase.RequestValue> {
    public UpLoadImageFileUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<ImageRespone>> buildPagrams(RequestValue pagram) {
        return mDataRepository.upLoadImageFilesMutilPart(pagram.files)
                .observeOn(Schedulers.io())
                .map(new Function<DataResponse<ImageRespone>, DataResponse<ImageRespone>>() {
                    @Override
                    public DataResponse<ImageRespone> apply(@NonNull DataResponse<ImageRespone> imageResponeDataResponse) throws Exception {
                        return imageResponeDataResponse;
                    }
                });
    }
    public static class RequestValue implements RxUserCase.RequestValue{
        ArrayList<MultipartBody.Part> files;

        public RequestValue( ArrayList<MultipartBody.Part> files) {
            this.files = files;
        }
    }
}
