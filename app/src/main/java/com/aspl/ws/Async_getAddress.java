package com.aspl.ws;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.Utils.XML_JSONParser;
import com.aspl.fragment.DeliveryOptionsFragment;
import com.aspl.fragment.EditShippingAddressFragment;
import com.aspl.fragment.ManageAccountFragment;
import com.aspl.fragment.PaymentFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.PinModel;
import com.aspl.mbsmodel.PlaceModel;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mic on 1/15/2018.
 */

public class Async_getAddress extends AsyncTask<String, Void, Void> {

    List<NameValuePair> nameValuePairList;
    String response;
    private Context mContext;
    XML_JSONParser parser;
    String strURL;
    int status = 0;

    public Async_getAddress(Context context, String url, int status) {
        this.mContext = context;
        this.strURL = url;
        this.status = status;
    }


    @Override
    protected Void doInBackground(String... strings) {
        nameValuePairList = new ArrayList<NameValuePair>();
        parser = new XML_JSONParser();

        Log.i("web service", "Request Url : " + strURL);
        response = parser.callJSonWebService(strURL);
        Log.i("web service", "Response : " + response);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (response == null) {
            Toast.makeText(mContext, "It seems to network is not available.", Toast.LENGTH_SHORT).show();
            //Utils.isNetworkConnectionAvailable(mContext)
        } else {
            if (strURL.contains(Constant.INPUT)) {

                Log.i("web service", "Response : " + response);
                try {
                    JSONObject placeObj = new JSONObject(response);
                    JSONArray PlaceArray = placeObj.getJSONArray("predictions");
                    Constant.PlaceArr = new PlaceModel[PlaceArray.length()];
                    String Desc[] = new String[PlaceArray.length()];
                    final String Placeid[] = new String[PlaceArray.length()];
                    for (int i = 0; i < PlaceArray.length(); i++) {
                        JSONObject plcObj = PlaceArray.getJSONObject(i);
                        PlaceModel pm = new PlaceModel();
                        pm.description = plcObj.getString("description");
                        pm.place_id = plcObj.getString("place_id");
                        Constant.PlaceArr[i] = pm;
                        Desc[i] = pm.description;
                        Placeid[i] = pm.place_id;
                    }

                    if (status == 1) {
                    /*Login.autotxtAddress1*/
                        DeliveryOptionsFragment.etAddressOneDO.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                DeliveryOptionsFragment.isdhowdropdown = false;
                                DeliveryOptionsFragment.etAddressOneDO.dismissDropDown();
                                DeliveryOptionsFragment.etAddressOneDO.removeTextChangedListener(DeliveryOptionsFragment.myWatcher);
                                new Async_getAddress(mContext, Constant.MAP_API_URL1 + Constant.PLACE_ID + Placeid[i] + "&key=" + mContext.getString(R.string.Place_API_key), status).execute();
                            }
                        });
                        Log.i("web service", "Desc Size : " + Desc.length);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_dropdown_item_1line, Desc);
                        DeliveryOptionsFragment.etAddressOneDO.setAdapter(adapter);
                        DeliveryOptionsFragment.etAddressOneDO.setThreshold(1);
                        DeliveryOptionsFragment.etAddressOneDO.showDropDown();
                    }

                    if (status == 2) {
                    /*Login.autotxtAddress1*/
                        DeliveryOptionsFragment.etBAddressOneDO.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                DeliveryOptionsFragment.etBAddressOneDO.removeTextChangedListener(DeliveryOptionsFragment.myWatcher);
                                DeliveryOptionsFragment.isdhowdropdown = false;
                                DeliveryOptionsFragment.etBAddressOneDO.dismissDropDown();
                                new Async_getAddress(mContext, Constant.MAP_API_URL1 + Constant.PLACE_ID + Placeid[i] + "&key=" + mContext.getString(R.string.Place_API_key), status).execute();
                            }
                        });
                        Log.e("Log", "Desc size=" + Desc.length);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_dropdown_item_1line, Desc);
                        DeliveryOptionsFragment.etBAddressOneDO.setAdapter(adapter);
                        DeliveryOptionsFragment.etBAddressOneDO.setThreshold(1);
                        DeliveryOptionsFragment.etBAddressOneDO.showDropDown();
                    }

                    if (status == 11) {
                    /*Login.autotxtAddress1*/
                        PaymentFragment.etAddressOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                //PaymentFragment.isdhowdropdown = false;
                                PaymentFragment.etAddressOne.dismissDropDown();
                                PaymentFragment.isDhopDown = false;
                                PaymentFragment.etAddressOne.removeTextChangedListener(PaymentFragment.myWatcher);
                                new Async_getAddress(mContext, Constant.MAP_API_URL1 + Constant.PLACE_ID + Placeid[i] + "&key=" + mContext.getString(R.string.Place_API_key), status).execute();
                            }
                        });
                        Log.i("web service", "Desc Size : " + Desc.length);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_dropdown_item_1line, Desc);
                        PaymentFragment.etAddressOne.setAdapter(adapter);
                        PaymentFragment.etAddressOne.setThreshold(1);
                        PaymentFragment.etAddressOne.showDropDown();
                    }


                    if (status == 5) {
                        /*Login.autotxtAddress1*/
                        ManageAccountFragment.et_address_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                ManageAccountFragment.isdhowdropdown = false;
                                ManageAccountFragment.et_address_one.dismissDropDown();
                                ManageAccountFragment.et_address_one.removeTextChangedListener(DeliveryOptionsFragment.myWatcher);
                                new Async_getAddress(mContext, Constant.MAP_API_URL1 + Constant.PLACE_ID + Placeid[i] + "&key=" + mContext.getString(R.string.Place_API_key), status).execute();
                            }
                        });
                        Log.i("web service", "Desc Size : " + Desc.length);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_dropdown_item_1line, Desc);
                        ManageAccountFragment.et_address_one.setAdapter(adapter);
                        ManageAccountFragment.et_address_one.setThreshold(1);
                        ManageAccountFragment.et_address_one.showDropDown();
                    }

                    if (status == 6) {
                        /*Login.autotxtAddress1*/
                        EditShippingAddressFragment.et_address_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                EditShippingAddressFragment.isdhowdropdown = false;
                                EditShippingAddressFragment.et_address_one.dismissDropDown();
                                EditShippingAddressFragment.et_address_one.removeTextChangedListener(DeliveryOptionsFragment.myWatcher);
                                new Async_getAddress(mContext, Constant.MAP_API_URL1 + Constant.PLACE_ID + Placeid[i] + "&key=" + mContext.getString(R.string.Place_API_key), status).execute();
                            }
                        });
                        Log.i("web service", "Desc Size : " + Desc.length);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_dropdown_item_1line, Desc);
                        EditShippingAddressFragment.et_address_one.setAdapter(adapter);
                        EditShippingAddressFragment.et_address_one.setThreshold(1);
                        EditShippingAddressFragment.et_address_one.showDropDown();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (strURL.contains(Constant.PLACE_ID)) {
                Log.e("Log", "STRRURL=" + strURL);
                JSONObject placeObj = null;
                try {
                    placeObj = new JSONObject(response);
                    JSONObject resultarr = placeObj.getJSONObject("result");
                    JSONArray address = resultarr.getJSONArray("address_components");
                    if (status == 11) {
                        PaymentFragment.onFillAddress(address, status);
                    } else if(status == 5){
                        ManageAccountFragment.onFillAddress(address, status);
                    } else if(status == 6) {
                        EditShippingAddressFragment.onFillAddress(address,status);
                    } else {
                        DeliveryOptionsFragment.onFillAddress(address, status);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (strURL.contains(Constant.GET_ZIP_CODE)) {
                Log.i("web service", "Response : " + response);
                JSONObject userObj = null;
                try {
                    userObj = new JSONObject(response);
                    PinModel pinModel = new PinModel(userObj);
                    if (status == 12) {
                        PaymentFragment.onFillZipAddress(pinModel, status);
                    } else if((status == 5)){
                        ManageAccountFragment.onFillZipAddress(pinModel,status);
                    }else if((status == 6)) {
                        EditShippingAddressFragment.onFillZipAddress(pinModel,status);
                    }else{
                        DeliveryOptionsFragment.onFillZipAddress(pinModel, status);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
