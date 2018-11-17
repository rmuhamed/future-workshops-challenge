package com.futureworkshops.codetest.android.domain.model;

/**
 * Created by romh on 2018-11-17
 */
public class Resource<T, E> {
  public enum status { successful, error}

  private status result;
  private E throwable;
  private T data;

  private Resource(status result, T data, E throwable) {
    this.result = result;
    this.data = data;
    this.throwable = throwable;
  }

  public status getResult() {
    return result;
  }

  public T getData() {
    return data;
  }

  public E getThrowable() {
    return throwable;
  }

  public static class Builder<T, E> {
    private status result;
    private E throwable;
    private T data;

    public Builder data(T data) {
      this.data = data;
      return this;
    }

    public Builder throwable(E throwable) {
      this.throwable = throwable;
      return this;
    }

    public Builder result(status result) {
      this.result = result;
      return this;
    }

    public Resource build () {
      return new Resource<>(this.result, this.data, this.throwable);
    }
  }
}
