package com.example.demo.service;

import com.example.demo.service.impl.MovieSubscriptionServiceImpl;


public interface MovieSubscriptionService {
    final String secretKey = MovieSubscriptionServiceImpl.SecurityConstants.SECRET_KEY;

}
