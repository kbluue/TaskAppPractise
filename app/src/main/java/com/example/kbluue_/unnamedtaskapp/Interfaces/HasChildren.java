package com.example.kbluue_.unnamedtaskapp.Interfaces;

import com.example.kbluue_.unnamedtaskapp.Models.Memo;

public interface HasChildren {

    Memo[] getChildren();

    <T extends Memo> void setChildren(T[] children);

    Memo getChild(int index);

    <T extends Memo> void addChild(T child);

    <T extends Memo> void removeChild(T child);

}
