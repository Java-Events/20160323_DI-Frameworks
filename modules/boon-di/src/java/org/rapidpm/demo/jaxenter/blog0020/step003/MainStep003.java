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

package org.rapidpm.demo.jaxenter.blog0020.step003;


import org.boon.di.Context;
import org.boon.di.DependencyInjection;
import org.boon.di.Module;

/**
 * Created by Sven Ruppert on 01.12.2014.
 */
public class MainStep003 {

  public static void main(String[] args) {
    //NPE
//        final Module module = DependencyInjection.classes(Service.class);
//    final Service service1 = module.get(Service.class);
//    service1.printInfo();

    final Module moduleA = DependencyInjection.module(new ServiceModuleA());
    final Module moduleB = DependencyInjection.module(new ServiceModuleB());
//    final Context context = DependencyInjection.context(moduleA);
//    final Context context = DependencyInjection.context(moduleA,moduleB);
    final Context context = DependencyInjection.context(moduleB,moduleA);

//    System.out.println("service? = " + context.has(Service.class));

//    context.remove(moduleB);
//    context.remove(moduleA);

    final Service service2 = context.get(Service.class);
    service2.printInfo();
  }


  public static class ServiceModuleA{

    public Service provideServiceA(){
      System.out.println("provideServiceAA ...");
      return new ServiceImpl();
    }
    public Service provideServiceB(){
      System.out.println("provideServiceAB ...");
      return new ServiceImpl();
    }
    public Service provideServiceC(){
      System.out.println("provideServiceAC ...");
      return new ServiceImpl();
    }
//
//    public ServiceImpl provideServiceImpl(){
//      System.out.println("provideServiceImplA ...");
//      return new ServiceImpl();
//    }


  }
  public static class ServiceModuleB{

    public Service provideServiceA(){
      System.out.println("provideServiceBA ...");
      return new ServiceImpl();
    }

    public Service provideServiceB(){
      System.out.println("provideServiceBB ...");
      return new ServiceImpl();
    }

    public Service provideServiceC(){
      System.out.println("provideServiceBC ...");
      return new ServiceImpl();
    }

    public ServiceImpl provideServiceImpl(){
      System.out.println("provideServiceImplB ...");
      return new ServiceImpl();
    }


  }


}
