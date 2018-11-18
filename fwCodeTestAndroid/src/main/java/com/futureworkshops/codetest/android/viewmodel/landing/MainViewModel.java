package com.futureworkshops.codetest.android.viewmodel.landing;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.futureworkshops.codetest.android.data.exception.EndOfWorldException;
import com.futureworkshops.codetest.android.domain.model.Resource;
import com.futureworkshops.codetest.android.domain.repositories.NetworkRepository;

/**
 * Created by romh on 2018-11-18
 */
public class MainViewModel extends ViewModel {
  private MutableLiveData<Resource<Object, EndOfWorldException>> liveData;
  private NetworkRepository repository;

  public void initialise(NetworkRepository repository) {
    this.repository = repository;
  }

  public LiveData<Resource<Object, EndOfWorldException>> getImportantAction() {
    if (this.liveData == null) {
      this.liveData = new MutableLiveData<>();
    }

    this.loadImportantAction();

    return this.liveData;
  }

  private void loadImportantAction() {
    this.repository.getImportAction(throwable -> {
      Resource<Object, EndOfWorldException> resource = new Resource.Builder<Object, EndOfWorldException>()
          .result(Resource.status.endOfWorld)
          .data(null)
          .throwable(new EndOfWorldException())
          .build();

      this.liveData.postValue(resource);
    });
  }
}
