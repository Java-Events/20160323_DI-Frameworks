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

package org.rapidpm.demo.jaxenter.blog0018.step04.business;

import dagger.Module;
import dagger.Provides;
import org.rapidpm.demo.jaxenter.blog0018.Context;
import org.rapidpm.demo.jaxenter.blog0018.business.subservice.SubService;

import javax.inject.Named;
import javax.inject.Provider;

/**
 * Created by Sven Ruppert on 19.11.2014.
 */
@Module(library = true,
    includes = {SubService_A_Module.class, SubService_B_Module.class},
    complete = true)
public class SubServiceModule {

  @Provides
  SubService provideSubService(@Named("A") Provider<SubService> subServiceA,
                               @Named("B") Provider<SubService> subServiceB) {
    if (Context.getInstance().defaultImpl) {
      return subServiceA.get();
    } else {
      return subServiceB.get();
    }
  }


}
