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
package org.teavm.dom.core;

import org.teavm.jso.JSArrayReader;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

/**
 *
 * @author Alexey Andreev
 * @param <T> type of nodes in this map.
 */
public interface NamedNodeMap<T extends Node> extends JSObject, JSArrayReader<T> {
    T getNamedItem(String name);

    T setNamedItem(T arg);

    T removeNamedItem(String name);

    T item(int index);

    @Override
    @JSProperty
    int getLength();

    T getNamedItemNS(String namespaceURI, String localName);

    T setNamedItemNS(T arg);

    T removeNamedItemNS(String namespaceURI, String localName);
}
