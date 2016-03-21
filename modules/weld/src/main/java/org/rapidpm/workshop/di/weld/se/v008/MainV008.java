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

package org.rapidpm.workshop.di.weld.se.v008;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.time.LocalDateTime;

import static java.lang.System.out;

public class MainV008 {

  public static void main(String[] args) {
    try (final WeldContainer container = new Weld()
        .containerId("main")
        .disableDiscovery()
        .packages(MainV008.class)
        .initialize()) {

      final Instance<Service> serviceInstance = container.select(Service.class);
      final Service service = serviceInstance.get();

      final String s = service.doWork();
      out.println("s = " + s);
    }
  }

  @Produces
  public SubService producerForSubService(InjectionPoint iP, BeanManager beanManager) {
    return () -> "fresh created " + LocalDateTime.now() + " for target class " + iP.getMember().getDeclaringClass().getName();
  }

  public interface SubService {
    String doWork();
  }

  public static class Service {

    @Inject SubService subservice;

    public String doWork() {
      return subservice.doWork();
    }
  }
}
