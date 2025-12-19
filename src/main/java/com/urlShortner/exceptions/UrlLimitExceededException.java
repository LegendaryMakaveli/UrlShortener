package com.urlShortner.exceptions;

public class UrlLimitExceededException extends ShortUrlException  {
    public UrlLimitExceededException(String message) { super(message);}
}
