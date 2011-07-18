/*
 * Copyright (C) 2011, FuseSource Corp.  All rights reserved.
 * http://fusesource.com
 *
 * The software in this package is published under the terms of the
 * CDDL license a copy of which has been included with this distribution
 * in the license.txt file.
 */

package org.fusesource.fabric.fab.osgi.url.internal;

import org.fusesource.fabric.fab.osgi.url.ServiceConstants;
import org.ops4j.lang.NullArgumentException;
import org.ops4j.util.property.PropertyResolver;
import org.ops4j.util.property.PropertyStore;

public class Configuration extends PropertyStore {
    protected static final boolean DEFAULT_INSTALL_PROVIDED_DEPENDENCIES = false;

    private PropertyResolver propertyResolver;

    public Configuration(PropertyResolver propertyResolver) {
        NullArgumentException.validateNotNull(propertyResolver, "PropertyResolver");
        this.propertyResolver = propertyResolver;
    }

    /**
     * Returns true if the certificate should be checked on SSL connection, false otherwise
     */
    public Boolean getCertificateCheck() {
        if (!contains(ServiceConstants.PROPERTY_CERTIFICATE_CHECK)) {
            return set(ServiceConstants.PROPERTY_CERTIFICATE_CHECK,
                    Boolean.valueOf(propertyResolver.get(ServiceConstants.PROPERTY_CERTIFICATE_CHECK))
            );
        }
        return get(ServiceConstants.PROPERTY_CERTIFICATE_CHECK);
    }

    /**
     * Returns whether or not the shared dependencies should be installed
     */
    public boolean isInstallMissingDependencies() {
        if (!contains(ServiceConstants.PROPERTY_INSTALL_PROVIDED_DEPENDENCIES)) {
            String value = propertyResolver.get(ServiceConstants.PROPERTY_INSTALL_PROVIDED_DEPENDENCIES);
            if (value != null) {
                Boolean aBoolean = Boolean.valueOf(value);
                return set(ServiceConstants.PROPERTY_INSTALL_PROVIDED_DEPENDENCIES,
                        aBoolean
                );
            }
        }
        Boolean answer = get(ServiceConstants.PROPERTY_INSTALL_PROVIDED_DEPENDENCIES);
        if (answer == null) {
            return DEFAULT_INSTALL_PROVIDED_DEPENDENCIES;
        } else {
            return answer.booleanValue();
        }
    }

    public String[] getMavenRepositories() {
        if (!contains(ServiceConstants.PROPERTY_MAVEN_REPOSITORIES)) {
            String text = propertyResolver.get(ServiceConstants.PROPERTY_MAVEN_REPOSITORIES);
            String[] repositories = toArray(text);
            return set(ServiceConstants.PROPERTY_MAVEN_REPOSITORIES, repositories);
        }
        return get(ServiceConstants.PROPERTY_MAVEN_REPOSITORIES);
    }

    public static String[] toArray(String text) {
        String[] answer = null;
        if (text != null) {
            answer = text.split(",");
        }
        return answer;
    }

}