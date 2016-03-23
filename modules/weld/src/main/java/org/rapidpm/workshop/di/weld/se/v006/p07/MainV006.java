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

package org.rapidpm.workshop.di.weld.se.v006.p07;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.System.out;

public class MainV006 {

  public static void main(String[] args) {
    try (final WeldContainer container = new Weld()
        .containerId("main")
        .disableDiscovery()
        .packages(MainV006.class)
        .initialize()) {

      final Instance<Service> serviceInstance = container.select(Service.class);
      final Service service = serviceInstance.get();

      final String s = service.doWork();
      out.println("s = " + s);
    }
  }

  public interface SubService {
    String doWork();
  }

  @Qualifier
  @Retention(value = RetentionPolicy.RUNTIME)
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
  public @interface Production {
  }

  public static class Service {

    @Inject @Production SubService subservice;

    public String doWork() {
      return subservice.doWork();
    }
  }

  public static class SubServiceA implements SubService {
    public String doWork() {
      return "SubServiceA";
    }
  }

  @Production
  public static class SubServiceB implements SubService {
    public String doWork() {
      return "SubServiceB";
    }
  }
}
