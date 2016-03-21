/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.rapidpm.demo.jaxenter.blog0020.step006;

import org.boon.di.Context;
import org.boon.di.DependencyInjection;
import org.boon.di.Module;
import org.boon.di.ProviderInfo;

import javax.inject.Named;

/**
 * Created by Sven Ruppert on 03.12.2014.
 */
public class MainStep006 {
  public static void main(String[] args) {
    final Module suppliers = DependencyInjection.suppliers(
        ProviderInfo.providerOf("", ServiceImpl.class, ServiceImplA::new),//default
        ProviderInfo.providerOf("ImplA", ServiceImpl.class, ServiceImplA::new),
        ProviderInfo.providerOf("ImplB", ServiceImpl.class, ServiceImplB::new)
    );

    final Context context = DependencyInjection.context(suppliers);

    //kein Zugriff ohne Named
    System.out.println(context.invoke("", "work", "invoked.."));
    System.out.println(context.invoke("ImplA", "work", "invoked.."));
    System.out.println(context.invoke("ImplB", "work", "invoked.."));

    //with interfaces
    final Module module = DependencyInjection.module(new Object() {
      public Service provide(){  return new ServiceA();}
      @Named("A") public Service provideA(){ return new ServiceA();}
//      @Named("ImplA") public Service provideA(){ return new ServiceA();}
      @Named("B") public Service provideB(){ return new ServiceB();}
    });

    final Context ctx = DependencyInjection.context(module);

    System.out.println(ctx.invoke("A", "work", "invoked.."));
//    System.out.println(ctx.invoke("ImplA", "work", "invoked.."));
    System.out.println(ctx.invoke("B", "work", "invoked.."));

    final Context combine = context.combine(ctx);
    System.out.println(combine.invoke("ImplA", "work", "combine invoked.."));
    System.out.println(combine.invoke("ImplB", "work", "combine invoked.."));
    System.out.println(combine.invoke("A", "work", "combine invoked.."));
//    System.out.println(combine.invoke("ImplA", "work", "combine invoked..")); //ups ;-)
    System.out.println(combine.invoke("B", "work", "combine invoked.."));

  }


  //interfaces
  public static interface Service{
    public String work(String txt);
  }
  public static class ServiceA implements Service{
    public String work(String txt){
      return "ServiceA - " + txt;
    }
  }
  public static class ServiceB implements Service{
    public String work(String txt){
      return "ServiceB - " + txt;
    }
  }

  //no interfaces
  public static class ServiceImpl{
    public String work(String txt){
      return "ServiceImpl - " + txt;
    }
  }
  public static class ServiceImplA extends ServiceImpl{
    public String work(String txt){
      return "ServiceImplA - " + txt;
    }
  }
  public static class ServiceImplB extends ServiceImpl{
    public String work(String txt){
      return "ServiceImplB - " + txt;
    }
  }

}
