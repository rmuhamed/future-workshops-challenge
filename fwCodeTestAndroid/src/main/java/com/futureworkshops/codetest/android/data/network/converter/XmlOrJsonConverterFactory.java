package com.futureworkshops.codetest.android.data.network.converter;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by romh on 2018-11-18
 */
public class XmlOrJsonConverterFactory extends Converter.Factory {
  private final Converter.Factory xmlConverterFactory = SimpleXmlConverterFactory.create();
  private final Converter.Factory jsonConverterFactory = GsonConverterFactory.create();

  public static XmlOrJsonConverterFactory create() {
    return new XmlOrJsonConverterFactory();
  }

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
    Converter<ResponseBody, ?> converter = null;
    for (Annotation annotation : annotations) {
      if (annotation.annotationType() == XML.class) {
        converter = this.xmlConverterFactory.responseBodyConverter(type, annotations, retrofit);
        break;
      }

      if (annotation.annotationType() == JSON.class) {
        converter = this.jsonConverterFactory.responseBodyConverter(type, annotations, retrofit);
        break;
      }
    }

    return converter;
  }
}
