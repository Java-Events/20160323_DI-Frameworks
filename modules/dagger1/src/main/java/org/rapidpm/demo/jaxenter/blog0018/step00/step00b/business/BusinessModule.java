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

package org.rapidpm.demo.jaxenter.blog0018.step00.step00b.business;

import dagger.Module;
import dagger.Provides;
import org.rapidpm.demo.jaxenter.blog0018.step00.step00b.Main;
import org.rapidpm.demo.jaxenter.blog0018.step00.step00b.business.subservice.SubService;
import org.rapidpm.demo.jaxenter.blog0018.step00.step00b.business.subservice.impl_a.SubServiceA;
import org.rapidpm.demo.jaxenter.blog0018.step00.step00b.business.subservice.impl_b.SubServiceB;

import javax.inject.Named;

/**
 * Created by Sven Ruppert on 17.11.2014.
 */

@Module(library = true,
    injects = {Main.class},
    complete = true)
public class BusinessModule {
  @Provides
  MainService provideMainService(MainServiceImpl mainService) {
    return mainService;
  }

  @Provides
  @Named("A")
  SubService provideSubServiceA() {
    return new SubServiceA();
  }

  @Provides
  @Named("B")
  SubService provideSubServiceB() {
    return new SubServiceB();
  }

  @Provides
  SubService provideSubService(@Named("A") SubService subServiceA) {
    return subServiceA;
  }
}
