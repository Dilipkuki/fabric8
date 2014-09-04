/**
 *  Copyright 2005-2014 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.api;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A builder for an attributable object.
 *
 * @author thomas.diesler@jboss.com
 * @since 18-Apr-2014
 */
public abstract class AbstractAttributableBuilder<B extends AttributableBuilder<B>> extends AbstractBuilder<B> implements AttributableBuilder<B> {

    private Map<String, String> attributes = new LinkedHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public B addAttribute(String key, String value) {
        attributes.put(key, value);
        return (B) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public B setAttributes(Map<String, String> atts) {
        attributes = new LinkedHashMap<>(atts);
        return (B) this;
    }

    protected Map<String, String> getAttributes() {
        return attributes;
    }
}
