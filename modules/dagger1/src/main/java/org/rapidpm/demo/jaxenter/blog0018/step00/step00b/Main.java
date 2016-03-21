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

package org.rapidpm.demo.jaxenter.blog0018.step00.step00b;

import dagger.ObjectGraph;
import org.rapidpm.demo.jaxenter.blog0018.step00.step00b.business.BusinessModule;
import org.rapidpm.demo.jaxenter.blog0018.step00.step00b.business.MainService;

import javax.inject.Inject;

/**
 * Created by Sven Ruppert on 17.11.2014.
 */
public class Main {

  @Inject MainService mainService;

  public static void main(String[] args) {
    //bootstrapping
    final ObjectGraph objectGraph
        = ObjectGraph.create(new BusinessModule());
    final Main main = objectGraph.get(Main.class);
//    Main main = new Main();
//    objectGraph.inject(main);
    System.out.println("execute = " + main.mainService.execute("Und Los"));
    System.out.println("execute = " + main.mainService.execute("Und Los"));
    System.out.println("execute = " + main.mainService.execute("Und Los"));
  }
}
