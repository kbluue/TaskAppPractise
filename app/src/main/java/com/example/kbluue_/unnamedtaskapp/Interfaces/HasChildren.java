package com.example.kbluue_.unnamedtaskapp.Interfaces;

import com.example.kbluue_.unnamedtaskapp.Models.Memo;

public interface HasChildren {

    <T extends Memo> T[] getChildren();

    <T extends Memo> T getChild(int index);

}
