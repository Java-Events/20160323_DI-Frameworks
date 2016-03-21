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

package org.rapidpm.demo.jaxenter.blog0020.step001;

import org.boon.di.Context;
import org.boon.di.DependencyInjection;
import org.boon.di.Module;


/**
 * Created by Sven Ruppert on 30.11.2014.
 */
public class MainStep001 {
  public static void main(String[] args) {
    final Service service = new Service();
    service.printInfo();
    //CDi not working
    final Module module = DependencyInjection.classes(Service.class);
    final Service service1 = module.get(Service.class);
    service1.printInfo();
    //factory Methods are now available
    final Context context = DependencyInjection.context(module);
    final Service service2 = context.get(Service.class);
    service2.printInfo();
  }
}
