/*
 * Copyright [2014] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.rapidpm.demo.jaxenter.blog0018.step06;

import dagger.ObjectGraph;
import org.rapidpm.demo.jaxenter.blog0018.business.MainService;
import org.rapidpm.demo.jaxenter.blog0018.step06.business.BusinessModule;

import javax.inject.Inject;

/**
 * Created by Sven Ruppert on 17.11.2014.
 */
public class Main {

  @Inject MainService mainService;

  public static void main(String[] args) {

    //bootstrapping
    Main main = new Main();
    final ObjectGraph objectGraphA = ObjectGraph.create(new BusinessModule());
    final ObjectGraph objectGraphB = ObjectGraph.create(new BusinessModule());

    objectGraphA.inject(main);
    System.out.println("main.mainService = " + main.mainService);
    System.out.println("####### next inject #######");
    objectGraphA.inject(main);
    System.out.println("main.mainService = " + main.mainService);

//    main.mainService.execute("Und Los");
//    Context.getInstance().defaultImpl = false;
//    main.mainService.execute("Und Los");
//    Context.getInstance().defaultImpl = true;
//    main.mainService.execute("Und Los");

//    System.out.println("main.mainService = " + main.mainService);
    //ohoh..  jetzt wird es durcheinander....
    System.out.println("####### next object graph #######");
    objectGraphB.inject(main);
    System.out.println("main.mainService = " + main.mainService);

//    main.mainService.execute("Und Los");
//    Context.getInstance().defaultImpl = false;
//    main.mainService.execute("Und Los");
//    Context.getInstance().defaultImpl = true;
//    main.mainService.execute("Und Los");


  }


}
