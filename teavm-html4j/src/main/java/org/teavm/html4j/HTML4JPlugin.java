/*
 *  Copyright 2014 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.html4j;

import org.teavm.dependency.DependencyListener;
import org.teavm.vm.spi.RendererListener;
import org.teavm.vm.spi.TeaVMHost;
import org.teavm.vm.spi.TeaVMPlugin;

/**
 *
 * @author Alexey Andreev
 */
public class HTML4JPlugin implements TeaVMPlugin {
    @Override
    public void install(TeaVMHost host) {
        host.add(new JavaScriptBodyDependency());
        host.add(new JavaScriptBodyTransformer());
        host.add(new JCLHacks());
        host.add(new JavaScriptResourceInterceptor());
        EntryPointGenerator entryPointGen = new EntryPointGenerator(host.getProperties()
                .getProperty("html4j.entryPoints", ""));
        host.add((DependencyListener)entryPointGen);
        host.add((RendererListener)entryPointGen);
    }
}
