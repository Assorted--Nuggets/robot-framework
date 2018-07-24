package org.usfirst.frc.team1764.robot.custom.util;

import java.util.HashMap;

/**
 * Stores a list of Selectable objects and Strings to associate them with names to be selected by
 * Contains logic for selecting objects and avoiding selecting an object multiple times
 */
public class Selector<T extends Selectable> extends HashMap<String,T>
{
    private T selected; /* the current object that is selected */

    public Selector()
    {
        super();
    }

    public T getSelected()
    {
        return selected;
    }

    public boolean hasSelected()
    {
        return this.selected != null;
    }

    public boolean isSelected(String key)
    {
        return hasSelected() && this.selected.getName().equals(key);
    }

    public void addAll(T... elements)
    {
        for(T element : elements)
        {
            put(element.getName(), element);
        }
    }

    /**
     * Selects an object
     * @param key name of the object we wish to select
     * @return
     */
    public T select(String key)
    {
        /* If the desired name is already selected, don't bother searching for it again, just return the one that is already
        * selected */
        if(isSelected(key))
        {
            return selected;
        }

        /* When we select a new object, we want to deselect the one that we previously selected */
        if(hasSelected())
            this.selected.onDeselect();
        this.selected = get(key); /* Search hashmap for the object we wish to select and assign it to what we have selected */
        /* Check if we selected successfully */
        if(hasSelected())
            this.selected.onSelect(); /* Do whatever the selected object wants to do when it is selected */
        return selected;
    }

    public void selectNone()
    {
        select("");
    }
}
