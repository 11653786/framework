package com.yt.webservice.hessian.impl;

import com.yt.webservice.hessian.HessianService;

/**
 * Created by user on 2015/9/12.
 */
public class HessianServiceImpl implements HessianService{

    public void hello(String name) {

        System.out.println("hello: "+name);
    }
}
