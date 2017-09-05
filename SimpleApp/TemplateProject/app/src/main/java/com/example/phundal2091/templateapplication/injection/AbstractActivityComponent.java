package com.example.phundal2091.templateapplication.injection;

import android.app.Activity;

import com.example.phundal2091.templateapplication.injection.annotations.PerActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.  Activity-level components
 * should extend this component.
 */
@PerActivity // Subtypes of AbstractActivityComponent should be decorated with @PerActivity.
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, DataModule.class})
public interface AbstractActivityComponent {
    Activity activity(); // Expose the activity to sub-graphs.
}