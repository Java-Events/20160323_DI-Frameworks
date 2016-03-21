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

package org.rapidpm.workshop.di.weld.se.v003;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class MainV003 {

  public static void main(String[] args) {

    final Weld weld = new Weld();
    try (WeldContainer container = weld.initialize()) {
      final MyEvent myEvent = new MyEvent();
      myEvent.setDataHolder("Message to transport..");
      container.event().select(MyEvent.class).fire(myEvent);
    }
  }


  public static class Service {

    @Inject SubService subservice;

    public String doWork(String data) {
      return subservice.doWork(data);
    }
  }

  public static class SubService {
    public String doWork(String data) {
      return "SubService - " + data;
    }
  }


  public static class Target {

    @Inject Service service;

    public void firedMethod(@Observes MyEvent myEvent) {
      System.out.println("myEvent = " + service.doWork(myEvent.getDataHolder()));
    }
  }


  public static class MyEvent {

    private String dataHolder;

    public String getDataHolder() {
      return dataHolder;
    }

    public void setDataHolder(final String dataHolder) {
      this.dataHolder = dataHolder;
    }

    @Override
    public String toString() {
      return "MyEvent{" +
          "dataHolder='" + dataHolder + '\'' +
          '}';
    }
  }

}
