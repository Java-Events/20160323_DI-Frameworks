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

package org.rapidpm.demo.jaxenter.blog0020.step004;

import org.boon.di.Context;
import org.boon.di.DependencyInjection;
import org.boon.di.Module;

/**
 * Created by Sven Ruppert on 02.12.2014.
 */
public class MainStep004 {

  public static void main(String[] args) {


    final DemoKlasse demoKlasse = new DemoKlasse();
    demoKlasse.setName("DemoKlasse");
    demoKlasse.setNummer(1);
    final SubDemoKlasse subDemoKlasse = new SubDemoKlasse();
    subDemoKlasse.setName("SubDemoKlasse");
    subDemoKlasse.setNummer(2);
//    subDemoKlasse.setLocalDateTime(LocalDateTime.now());
    demoKlasse.setSubDemoKlasse(subDemoKlasse);

    final Module module = DependencyInjection.prototypes(demoKlasse);
    final Context context = DependencyInjection.context(module);
    final DemoKlasse d1 = context.get(DemoKlasse.class);
    System.out.println("proto = " + demoKlasse);
    System.out.println("   d1 = " + d1);


  }

  public static class DemoKlasse {
    private String name;
    private int nummer;

    private SubDemo subDemoKlasse;

    public SubDemo getSubDemoKlasse() {
      return subDemoKlasse;
    }

    public void setSubDemoKlasse(SubDemo subDemoKlasse) {
      this.subDemoKlasse = subDemoKlasse;
    }

    public String getName() {
      System.out.println("getName = " + name);
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getNummer() {
      System.out.println("getNummer = " + nummer);
      return nummer;
    }

    public void setNummer(int nummer) {
      this.nummer = nummer;
    }
  }

  public static interface SubDemo {

  }

  public static class SubDemoKlasse implements SubDemo {
    private String name;
    private int nummer;

//    private LocalDateTime localDateTime;

    public String getName() {
      System.out.println("SubDemoKlasse.getName = " + name);
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getNummer() {
      System.out.println("SubDemoKlasse.getNummer = " + nummer);
      return nummer;
    }

    public void setNummer(int nummer) {
      this.nummer = nummer;
    }

//    public LocalDateTime getLocalDateTime() {
//      return localDateTime;
//    }
//
//    public void setLocalDateTime(LocalDateTime localDateTime) {
//      this.localDateTime = localDateTime;
//    }
  }
}
