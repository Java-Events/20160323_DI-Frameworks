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

package org.rapidpm.workshop.di.weld.se.v004;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.rapidpm.workshop.di.weld.se.v004.p01.ServiceP01;
import org.rapidpm.workshop.di.weld.se.v004.p02.ServiceP02;

import static java.lang.System.out;

public class MainV004 {

  public static void main(String[] args) {
    final String doWorkP01;
    final String doWorkP02;
    try (
        final WeldContainer weldP01 = new Weld()
            .containerId("p01")
            .disableDiscovery()
            .packages(ServiceP01.class)
            .beanClasses(Service.class)
            .initialize();
        final WeldContainer weldP02 = new Weld()
            .containerId("p02")
            .disableDiscovery()
            .packages(ServiceP02.class)
            .beanClasses(Service.class)
            .initialize()
    ) {
      doWorkP01 = weldP01.select(Service.class).get().doWork("Hello World");
      doWorkP02 = weldP02.select(Service.class).get().doWork("Hello World");
    }
    out.println("doWorkP01 = " + doWorkP01);
    out.println("doWorkP02 = " + doWorkP02);
  }

}
