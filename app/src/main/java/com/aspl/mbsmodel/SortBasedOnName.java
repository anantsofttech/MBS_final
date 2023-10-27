package com.aspl.mbsmodel;

import java.util.Comparator;

public class SortBasedOnName implements Comparator {

    public int compare(Object o1, Object o2)
    {

        MbsDataModel dd1 = (MbsDataModel)o1;// where FBFriends_Obj is your object class
        MbsDataModel dd2 = (MbsDataModel)o2;
        return dd1.PageName.compareToIgnoreCase(dd2.PageName);//where uname is field name
    }

}
