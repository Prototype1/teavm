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
package org.teavm.javascript.spi;

import java.io.IOException;
import java.util.Properties;
import org.teavm.codegen.SourceWriter;
import org.teavm.common.ServiceRepository;
import org.teavm.javascript.ast.Expr;
import org.teavm.model.ValueType;

/**
 *
 * @author Alexey Andreev
 */
public interface InjectorContext extends ServiceRepository {
    Expr getArgument(int index);

    int argumentCount();

    boolean isMinifying();

    SourceWriter getWriter();

    Properties getProperties();

    void writeEscaped(String str) throws IOException;

    void writeType(ValueType type) throws IOException;

    void writeExpr(Expr expr) throws IOException;
}
