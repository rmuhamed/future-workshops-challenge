package com.futureworkshops.codetest.android.data.exception;

/**
 * Created by romh on 2018-11-18
 */
public class EndOfWorldException extends Throwable {

  public EndOfWorldException() {
    super();
  }

  public String getTitle() {
    return "¡End of World was started!";
  }

  @Override
  public String getMessage() {
    return "Nobody can scape ...";
  }
}
