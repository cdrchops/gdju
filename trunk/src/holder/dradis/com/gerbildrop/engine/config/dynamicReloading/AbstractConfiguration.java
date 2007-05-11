package com.gerbildrop.engine.config.dynamicReloading;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/** @author Heinz Kabutz */
public abstract class AbstractConfiguration implements Configuration {
    /** A map of all the properties for the configuration */
    private final Map<String, Object> properties =
            new HashMap<String, Object>();

    private final Collection<PropertyChangeListener> listeners =
            new CopyOnWriteArrayList<PropertyChangeListener>();

    /** Make a daemon timer to check for changes. */
    private final Timer timer = new Timer(true);

    /**
     * This class has a timer to check periodically if the configuration has changed.  If it has, it reloads the
     * properties.  This may cause the property change events to fire.
     *
     * @param period number of milliseconds between checking for property changes.
     */
    protected AbstractConfiguration(int period) {
        timer.schedule(new TimerTask() {
            public void run() {
                checkForPropertyChanges();
            }
        }, period, period);
    }

    /**
     * This method should be overridden to check whether the properties could maybe have changed, and if yes, to reload
     * them.
     */
    protected abstract void checkForPropertyChanges();

    public final Object getProperty(String propertyName) {
        synchronized (properties) {
            return properties.get(propertyName);
        }
    }

    public Set<Entry<String, Object>> getAllProperties() {
        synchronized (properties) {
            return properties.entrySet();
        }
    }

    /** Each time we set a property, we check whether it has changed and if it has, we let the listeners know. */
    protected final void setProperty(String propertyName, Object value) {
        synchronized (properties) {
            Object old = properties.get(propertyName);
            if ((value != null && !value.equals(old))
                || value == null && old != null) {
                properties.put(propertyName, value);
                PropertyChangeEvent event = new PropertyChangeEvent(this,
                                                                    propertyName, old, value);
                for (PropertyChangeListener listener : listeners) {
                    listener.propertyChange(event);
                }
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        listeners.add(l);
    }

    public boolean removePropertyChangeListener(PropertyChangeListener l) {
        return listeners.remove(l);
    }
}
