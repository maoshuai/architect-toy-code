package com.architect.week3;

/**
 * The basic component with type and name.
 * also it comes with a default draw method to draw itself.
 */
public abstract class AbstractComponent implements Component{
    private String type;
    private String name;


    public AbstractComponent(String type, String name){
        this.type = type;
        this.name = name;
    }

    /**
     * The default draw method simply print its type and name.
     */
    @Override
    public void draw() {
        System.out.println("print " + type + "(" + name + ")");
    }
}
