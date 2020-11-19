package com.example.dogapputil.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.dogapputil.model.DogBreedModel;
import com.example.dogapputil.service.DogApiService;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ListDogViewModel extends AndroidViewModel {

    public MutableLiveData<List<DogBreedModel>> dogs = new MutableLiveData<List<DogBreedModel>>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();


    private DogApiService  dogApiService = new DogApiService();

    //RxJava To dispose the api call within the life cycle of the ViewModel
    private CompositeDisposable disposable = new CompositeDisposable();


    public ListDogViewModel(@NonNull Application application) {
        super(application);
    }

    public void updateDogList() {
      loading.setValue(true);
      disposable.add(
      dogApiService.dogsList()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<List<DogBreedModel>>() {
            @Override
            public void onSuccess(List<DogBreedModel> dogBreedModels) {
                dogs.setValue(dogBreedModels);
                error.setValue(false);
                loading.setValue(false);
            }
            @Override
            public void onError(Throwable e) {
                error.setValue(true);
                loading.setValue(false);
                e.printStackTrace();
            }
        })
       );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
