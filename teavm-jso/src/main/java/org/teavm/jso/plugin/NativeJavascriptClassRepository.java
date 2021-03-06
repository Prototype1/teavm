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
package org.teavm.jso.plugin;

import java.util.HashMap;
import java.util.Map;
import org.teavm.jso.JSObject;
import org.teavm.model.ClassReader;
import org.teavm.model.ClassReaderSource;
import org.teavm.model.ElementModifier;

/**
 *
 * @author Alexey Andreev
 */
class NativeJavascriptClassRepository {
    private ClassReaderSource classSource;
    private Map<String, Boolean> knownJavaScriptClasses = new HashMap<>();
    private Map<String, Boolean> knownJavaScriptImplementations = new HashMap<>();

    public NativeJavascriptClassRepository(ClassReaderSource classSource) {
        this.classSource = classSource;
        knownJavaScriptClasses.put(JSObject.class.getName(), true);
    }

    public boolean isJavaScriptClass(String className) {
        Boolean known = knownJavaScriptClasses.get(className);
        if (known == null) {
            known = examineIfJavaScriptClass(className);
            knownJavaScriptClasses.put(className, known);
        }
        return known;
    }

    public boolean isJavaScriptImplementation(String className) {
        Boolean known = knownJavaScriptImplementations.get(className);
        if (known == null) {
            known = examineIfJavaScriptImplementation(className);
            knownJavaScriptImplementations.put(className, known);
        }
        return known;
    }

    private boolean examineIfJavaScriptClass(String className) {
        ClassReader cls = classSource.get(className);
        if (cls == null || !(cls.hasModifier(ElementModifier.INTERFACE) || cls.hasModifier(ElementModifier.ABSTRACT))) {
            return false;
        }
        for (String iface : cls.getInterfaces()) {
            if (isJavaScriptClass(iface)) {
                return true;
            }
        }
        if (cls.getParent() != null && !cls.getParent().equals(cls.getName())) {
            if (isJavaScriptClass(cls.getParent())) {
                return true;
            }
        }
        return false;
    }

    private boolean examineIfJavaScriptImplementation(String className) {
        if (isJavaScriptClass(className)) {
            return false;
        }
        ClassReader cls = classSource.get(className);
        if (cls == null) {
            return false;
        }
        if (cls.getParent() != null && !cls.getParent().equals(cls.getName())) {
            if (isJavaScriptClass(cls.getParent())) {
                return true;
            }
        }
        for (String iface : cls.getInterfaces()) {
            if (isJavaScriptClass(iface)) {
                return true;
            }
        }
        return false;
    }
}
