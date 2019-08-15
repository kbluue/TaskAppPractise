package com.example.kbluue_.unnamedtaskapp.Interfaces;

import java.util.List;

public interface Delegator {

    Object addMember(Object... initialVariables);

    int getConstructorType(Object... initialVariables);

    List deliver();
}
