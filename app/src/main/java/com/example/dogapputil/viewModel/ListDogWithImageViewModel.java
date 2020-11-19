package com.example.dogapputil.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.dogapputil.model.BreedResult;
import com.example.dogapputil.model.DogBreedModel;
import com.example.dogapputil.service.DogApiService;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ListDogWithImageViewModel extends AndroidViewModel {


    public MutableLiveData<List<BreedResult>> dogs = new MutableLiveData<List<BreedResult>>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();


    private DogApiService dogApiService = new DogApiService();

    //RxJava To dispose the api call within the life cycle of the ViewModel
    private CompositeDisposable disposableWithImage = new CompositeDisposable();

    public ListDogWithImageViewModel(@NonNull Application application) {
        super(application);
    }

    public void updateDogList() {
        loading.setValue(true);
        disposableWithImage.add(
        dogApiService.dogsListImage()
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeWith(new DisposableSingleObserver<List<BreedResult>>() {
              @Override
              public void onSuccess(List<BreedResult> dogBreedModels) {
                processResult(dogBreedModels);
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

    private void processResult(List<BreedResult> dogBreedModels) {
        List<DogBreedModel> dogsWithImage = new ArrayList<DogBreedModel>();

        for(BreedResult breedResult: dogBreedModels) {
            dogsWithImage.addAll(breedResult.getBreeds());
        }

        if(dogsWithImage.size() > 0 ) dogs.setValue(dogBreedModels);

        error.setValue(false);
        loading.setValue(false);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposableWithImage.clear();
    }
}
