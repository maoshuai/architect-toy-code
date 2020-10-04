package com.architect.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * A container component which may have its child component.
 * The container can add children to itself by addChild().
 * The container re-implements draw method by drawing all its children by order
 * as well as itself.
 */
public abstract class AbstractContainerComponent extends AbstractComponent{
    private List<Component> subComponent = new ArrayList<>();

    public AbstractContainerComponent(String type, String name) {
        super(type, name);
    }

    public void addChild(Component component){
        subComponent.add(component);
    }

    @Override
    public void draw() {
        super.draw();
        for(Component component: subComponent){
            component.draw();
        }

    }
}
