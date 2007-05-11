package com.gerbildrop.config.dynamicReloading;

import java.beans.PropertyChangeListener;
import java.util.Map.Entry;
import java.util.Set;

public interface Configuration {
    Object getProperty(String propertyName);

    Set<Entry<String, Object>> getAllProperties();

    void addPropertyChangeListener(PropertyChangeListener listener);

    boolean removePropertyChangeListener(PropertyChangeListener listener);
}
