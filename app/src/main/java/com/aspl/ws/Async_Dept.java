package com.aspl.ws;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.XML_JSONParser;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbsmodel.DepartmentModel;
import com.aspl.mbsmodel.SubDepartmentModel;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 11/20/2017.
 */
public class Async_Dept extends AsyncTask<String, Void, Void> {

    List<NameValuePair> nameValuePairList;
    String response;
    public ProgressDialog progressBar;
    String username, password;

    private Context mContext;
    public SharedPreferences preferences;
    XML_JSONParser parser;
    String strURL;
    DepartmentModel model;
    int position;
    Boolean displaySubdept;

    public Async_Dept(String url, Context mContext, Boolean displaySubdept) {

        strURL = url;
        this.mContext = mContext;
        this.displaySubdept = displaySubdept;
//        this.model=model;
//        this.position=listPosition;
    }

    @Override
    protected Void doInBackground(String... params) {
        nameValuePairList = new ArrayList<NameValuePair>();
        parser = new XML_JSONParser();

        Log.i("web service", "Request Url : " + strURL);
        response = parser.callJSonWebService(strURL);
        Log.i("web service", "Response : " + response);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // postexecute logic
        super.onPostExecute(result);

        if(response!=null){
            try {
                Log.e("Log","response="+response);

                JSONArray DepartmentArr = new JSONArray(response);
                        Constant.DepartmentList = new ArrayList<>();
                        for (int i = 0; i < DepartmentArr.length(); i++) {
                            JSONObject deptObj = DepartmentArr.getJSONObject(i);
                            DepartmentModel model = new DepartmentModel(deptObj);
                            Constant.DepartmentList.add(model);
                            position = i;

                            model.subDeptModelList.clear();

                            if(displaySubdept) {
                                for (int j = 0; j < Constant.SubDepartmentList.size(); j++) {
                                    SubDepartmentModel subDepartmentModel = Constant.SubDepartmentList.get(j);
                                    if (model.dept_id.equals(String.valueOf(subDepartmentModel.getDeptid()))) {
                                        model.subDeptModelList.add(subDepartmentModel);
                                    }
                                }
                            }
                            Constant.DepartmentList.get(i).subDeptModelList = model.subDeptModelList;
                        }

//                JSONArray arr= new JSONArray(response);
//                model.subDeptModelList.clear();
//                for(int i=0;i<arr.length();i++){
//                    JSONObject obj=arr.getJSONObject(i);
//                    SubDepartmentModel SDM=new SubDepartmentModel(obj);
//                    model.subDeptModelList.add(SDM);
//                }
//                Constant.DepartmentList.get(position).subDeptModelList = model.subDeptModelList;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            }
            if(position==(Constant.DepartmentList.size()-1)){
           if(Constant.SCREEN_LAYOUT==1){
               MainActivity.getInstance().callFilterDetailFragment();
           }else {
               MainActivityDup.getInstance().callFilterDetailFragment();
           }
        }
    }
}