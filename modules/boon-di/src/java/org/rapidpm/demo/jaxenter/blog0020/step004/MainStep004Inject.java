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

import javax.inject.Inject;

/**
 * Created by Sven Ruppert on 02.12.2014.
 */
public class MainStep004Inject {

  public static void main(String[] args) {

    final Module module = DependencyInjection.module(new DemoModule());
    final Context ctx = DependencyInjection.context(module);
    final DemoKlasse demoKlasse = ctx.get(DemoKlasse.class);
    demoKlasse.setName("DemoKlasse");
    final SubDemoKlasse subDemoKlasse = (SubDemoKlasse) demoKlasse.getSubDemoKlasse();
    subDemoKlasse.setName("SubDemoKlasse");
    final Module modulePrototype = DependencyInjection.prototypes(demoKlasse);
    final Context context = DependencyInjection.context(modulePrototype,module);
    final DemoKlasse d1 = context.get(DemoKlasse.class);
    System.out.println("proto = " + demoKlasse);
    System.out.println("proto.getSubDemoKlasse() = " + demoKlasse.getSubDemoKlasse());
    System.out.println("   d1 = " + d1);
    System.out.println("d1.getSubDemoKlasse()    = " + d1.getSubDemoKlasse());

  }

  public static class DemoModule{
    public DemoKlasse provideDemoKlasse(){ return new DemoKlasse();}
    public SubDemo provideSubDemoKlasse(){ return new SubDemoKlasse();}
  }


  public static class DemoKlasse {
    private String name;

    @Inject SubDemo subDemoKlasse;

    public SubDemo getSubDemoKlasse() {
      return subDemoKlasse;
    }

    public void setSubDemoKlasse(SubDemo subDemoKlasse) {
      this.subDemoKlasse = subDemoKlasse;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

  }

  public static interface SubDemo { }

  public static class SubDemoKlasse implements SubDemo {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
