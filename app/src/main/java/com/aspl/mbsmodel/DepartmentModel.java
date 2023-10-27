package com.aspl.mbsmodel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by new on 11/06/2017.
 */
public class DepartmentModel {
    public String InvCount;
    public String dept_desc;
    public String dept_id;
    public String dept_img;
    public String storeno;
    public String total_record;

    public ArrayList<SubDepartmentModel> subDeptModelList=new ArrayList<>();

    public DepartmentModel(JSONObject deptObj) throws JSONException {
        InvCount=deptObj.getString("InvCount");
        dept_desc=deptObj.getString("dept_desc");
        dept_id=deptObj.getString("dept_id");
        dept_img=deptObj.getString("dept_img");
        storeno=deptObj.getString("storeno");
        total_record=deptObj.getString("total_record");
    }
}
