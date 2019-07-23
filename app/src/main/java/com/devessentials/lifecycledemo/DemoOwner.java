package com.devessentials.lifecycledemo;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.annotation.NonNull;

// SOS: fragments/activities are already LifecycleOwners. To do the same for our custom class, DemoOwner,
// we have to define the registry and implement the getLifecycle method.
public class DemoOwner implements LifecycleOwner {

    private final LifecycleRegistry mLifecycleRegistry;

    public DemoOwner() {
        mLifecycleRegistry = new LifecycleRegistry(this);
        getLifecycle().addObserver(new DemoObserver());
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    // SOS: DemoOwner must signal each state he enters, for observers to get the event
    public void startOwner() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    public void stopOwner() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }
}
