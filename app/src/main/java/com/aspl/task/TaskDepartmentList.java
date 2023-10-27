package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.FilterFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.DepartmentModel;
import com.aspl.mbsmodel.JackDepartmentModel;
import com.aspl.mbsmodel.SubDepartmentModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class TaskDepartmentList extends AsyncTask<String, Void, String> {

    private List<JackDepartmentModel> DepartmentList;
    int position;
    Boolean displaySubdept;
    ProgressDialog loading = null;
    Context context;

    TaskDepartmentListEvent taskDepartmentListevent;

    public TaskDepartmentList(Context context,TaskDepartmentListEvent taskDepartmentListevent, boolean displaySubdept) {
        this.context = context;
        this.taskDepartmentListevent = taskDepartmentListevent;
        this.displaySubdept = displaySubdept;
    }

    public interface TaskDepartmentListEvent {
        void onGetDepartmentListResult();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(context != null){
            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }

    }

    @Override
    protected String doInBackground(String... strings) {

        Log.i("web service--Cart", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();

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


            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            retry = true;
            count += 1;
        } while (count < 3 && retry);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        /*if (Constant.liCardModel != null){
            MainActivity.onUpdateCartItem(Constant.liCardModel);
        }*/

        if (taskDepartmentListevent != null) {
            taskDepartmentListevent.onGetDepartmentListResult();
        }

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }

    }
}
