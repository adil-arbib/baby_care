package com.groupe6.babycare.holders;

import com.groupe6.babycare.dtos.children.ChildDTO;

public class GlobalObjectsHolder {

    private static GlobalObjectsHolder instance;
    private ChildDTO currentChild;

    private GlobalObjectsHolder() {
    }

    public static GlobalObjectsHolder getInstance() {
        if(instance == null) instance = new GlobalObjectsHolder();
        return instance;
    }


    public ChildDTO getCurrentChild() {
        return currentChild;
    }

    public  void setCurrentChild(ChildDTO currentChild) {
        this.currentChild = currentChild;
    }


}
