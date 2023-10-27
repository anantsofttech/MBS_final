package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.InStoreOrderDetailModel;
import com.aspl.mbsmodel.InstorePurchaseDetailModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class TaskInstorePurchaseDetail extends AsyncTask<String, Void, String> {

    List<InstorePurchaseDetailModel> liInstorePurchaseModelList = new ArrayList<>();
    String response = "";
    ProgressDialog loading = null;
    Context context;
    List<InStoreOrderDetailModel> orderDetailListInstore = new ArrayList<>();
    int positi = 0;

    TaskInstorePurchaseDetailEvent taskInstorePurchaseDetailEvent;

    public interface TaskInstorePurchaseDetailEvent {
        void onInstorePurchaseDetailResult(List<InstorePurchaseDetailModel> liInstorePurchaseModel, int positi);
    }

    public TaskInstorePurchaseDetail(Context context, TaskInstorePurchaseDetailEvent taskInstorePurchaseDetailEvent, int position) {
        this.taskInstorePurchaseDetailEvent = taskInstorePurchaseDetailEvent;
        this.context = context;
        this.positi = position;
    }


    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        loading = new ProgressDialog(context, R.style.MyprogressDTheme);
        loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();

    }

    @Override
    protected String doInBackground(String... strings) {
        Log.i("web service", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet_WithTimeout(strings[0], responseStrBuilder);
                response = responseStrBuilder.toString();
                Log.i("web service", "response : " + response);

                try {
                    JSONArray jsonarray = new JSONArray(response);
//                    for (int i = 0; i < jsonarray.length(); i++) {

                        InstorePurchaseDetailModel instorePurchaseDetailModel = new InstorePurchaseDetailModel();

                        JSONObject jsonobject = jsonarray.getJSONObject(0);
                        if (jsonobject.getString("InvoiceNo") != null) {
                            instorePurchaseDetailModel.setInvoiceNo(jsonobject.getString("InvoiceNo"));
                        }
                        if (jsonobject.getDouble("sub") != 0) {
                            instorePurchaseDetailModel.setSub(jsonobject.getDouble("sub"));
                        }
                        if (jsonobject.getString("pay1") != null) {
                            instorePurchaseDetailModel.setPay1(jsonobject.getString("pay1"));
                        }

                        if (jsonobject.getString("pay2") != null) {
                            instorePurchaseDetailModel.setPay2(jsonobject.getString("pay2"));
                        }

                        if (jsonobject.getString("pay3") != null) {
                            instorePurchaseDetailModel.setPay3(jsonobject.getString("pay3"));
                        }

                        if (jsonobject.getDouble("total") != 0.0) {
                            instorePurchaseDetailModel.setTotal(jsonobject.getDouble("total"));
                        }

                        if (jsonobject.getDouble("tip") != 0.0) {
                            instorePurchaseDetailModel.setTip(jsonobject.getDouble("tip"));
                        }

                        if (jsonobject.getString("sta") != null) {
                            instorePurchaseDetailModel.setSta(jsonobject.getString("sta"));
                        }

                        if (jsonobject.getString("sto") != null) {
                            instorePurchaseDetailModel.setSto(jsonobject.getString("sto"));
                        }

                        if (jsonobject.getString("OrderDate") != null) {
                            instorePurchaseDetailModel.setOrderDate(jsonobject.getString("OrderDate"));
                        }

                        if (jsonobject.getString("InvTime") != null) {
                            instorePurchaseDetailModel.setInvTime(jsonobject.getString("InvTime"));
                        }

                        if (jsonobject.getString("transid") != null) {
                            instorePurchaseDetailModel.setTransid(jsonobject.getString("transid"));
                        }

                        if (jsonobject.getString("company_name") != null) {
                            instorePurchaseDetailModel.setCompanyName(jsonobject.getString("company_name"));
                        }
                        if (jsonobject.getString("cname") != null) {
                            instorePurchaseDetailModel.setCname(jsonobject.getString("cname"));
                        }

                        if (jsonobject.getString("address1") != null) {
                            instorePurchaseDetailModel.setAddress1(jsonobject.getString("address1"));
                        }

                        if (jsonobject.getString("address2") != null) {
                            instorePurchaseDetailModel.setAddress2(jsonobject.getString("address2"));
                        }

                        if (jsonobject.getString("city") != null) {
                            instorePurchaseDetailModel.setCity(jsonobject.getString("city"));
                        }
                        if (jsonobject.getString("state") != null) {
                            instorePurchaseDetailModel.setState(jsonobject.getString("state"));
                        }
                        if (jsonobject.getString("zip") != null) {
                            instorePurchaseDetailModel.setZip(jsonobject.getString("zip"));
                        }

                        if (jsonobject.getString("TaxBit") != null) {
                            instorePurchaseDetailModel.setTaxBit(Boolean.valueOf(jsonobject.getString("TaxBit")));
                        }

                        if (jsonobject.getString("Tax2Bit") != null) {
                            instorePurchaseDetailModel.setTax2Bit(Boolean.valueOf(jsonobject.getString("Tax2Bit")));
                        }

                        if (jsonobject.getString("Tax3Bit") != null) {
                            instorePurchaseDetailModel.setTax3Bit(Boolean.valueOf(jsonobject.getString("Tax3Bit")));
                        }

                        if (jsonobject.getString("FlatTaxBit") != null) {
                            instorePurchaseDetailModel.setFlatTaxBit(Boolean.valueOf(jsonobject.getString("FlatTaxBit")));
                        }

                        if (jsonobject.getString("TaxName") != null) {
                            instorePurchaseDetailModel.setTaxName(jsonobject.getString("TaxName"));
                        }

                        if (jsonobject.getString("Tax2Name") != null) {
                            instorePurchaseDetailModel.setTax2Name(jsonobject.getString("Tax2Name"));
                        }

                        if (jsonobject.getString("Tax3Name") != null) {
                            instorePurchaseDetailModel.setTax3Name(jsonobject.getString("Tax3Name"));
                        }

                        if (jsonobject.getString("FlatName") != null) {
                            instorePurchaseDetailModel.setFlatName(jsonobject.getString("FlatName"));
                        }

                        if (jsonobject.getDouble("TaxPerc") != 0.0) {
                            instorePurchaseDetailModel.setTaxPerc(Double.valueOf(jsonobject.getString("TaxPerc")));
                        }

                        if (jsonobject.getDouble("Tax2Perc") != 0.0) {
                            instorePurchaseDetailModel.setTax2Perc(Double.valueOf(jsonobject.getString("Tax2Perc")));
                        }

                        if (jsonobject.getDouble("Tax2Perc") != 0.0) {
                            instorePurchaseDetailModel.setTax2Perc(Double.valueOf(jsonobject.getString("Tax2Perc")));
                        }

                        if (jsonobject.getDouble("Tax3Perc") != 0.0) {
                            instorePurchaseDetailModel.setTax3Perc(Double.valueOf(jsonobject.getString("Tax3Perc")));
                        }

                        if (jsonobject.getDouble("FlatTaxPerc") != 0.0) {
                            instorePurchaseDetailModel.setFlatTaxPerc(Double.valueOf(jsonobject.getString("FlatTaxPerc")));
                        }

                        if (jsonobject.getString("NoSigRequired") != null) {
                            instorePurchaseDetailModel.setNoSigRequired(jsonobject.getString("NoSigRequired"));
                        }
                        if (jsonobject.getString("FlatName") != null) {
                            instorePurchaseDetailModel.setFlatName(jsonobject.getString("FlatName"));
                        }

                        if (Double.valueOf(jsonobject.getString("Deposit")) != 0.0) {
                            instorePurchaseDetailModel.setDeposit(Double.valueOf(jsonobject.getString("Deposit")));
                        }

                        if (jsonobject.getString("Memo_Desc") != null) {
                            instorePurchaseDetailModel.setMemoDesc(jsonobject.getString("Memo_Desc"));
                        }

                        if (jsonobject.getString("CustEmailId") != null) {
                            instorePurchaseDetailModel.setCustEmailId(jsonobject.getString("CustEmailId"));
                        }

                        if (jsonobject.getString("shopaddress") != null) {
                            instorePurchaseDetailModel.setAddress1(jsonobject.getString("shopaddress"));
                        }

                        if (jsonobject.getString("shopcity") != null) {
                            instorePurchaseDetailModel.setShopcity(jsonobject.getString("shopcity"));
                        }

                        if (jsonobject.getString("shopst") != null) {
                            instorePurchaseDetailModel.setShopst(jsonobject.getString("shopst"));
                        }

                        if (jsonobject.getString("shopzip") != null) {
                            instorePurchaseDetailModel.setShopzip(jsonobject.getString("shopzip"));
                        }

                        if (jsonobject.getString("shopname") != null) {
                            instorePurchaseDetailModel.setShopname(jsonobject.getString("shopname"));
                        }

                        if (jsonobject.getString("shopphone") != null) {
                            instorePurchaseDetailModel.setShopphone(jsonobject.getString("shopphone"));
                        }

                        if (jsonobject.getString("OrderStatus") != null) {
                            instorePurchaseDetailModel.setOrderStatus(jsonobject.getString("OrderStatus"));
                        }

                        if (jsonobject.getDouble("points") != 0.0) {
                            instorePurchaseDetailModel.setPoints(Double.valueOf(jsonobject.getString("points")));
                        }

                        if (jsonobject.getDouble("SizeFlag") != 0.0) {
                            instorePurchaseDetailModel.setSizeFlag(Double.valueOf(jsonobject.getString("SizeFlag")));
                        }

                        JSONObject orderdetailJsonDetailObj = jsonarray.getJSONObject(1);

                        JSONArray orderdetailJsonDetailArray = orderdetailJsonDetailObj.getJSONArray("OrderDetails");


                        for (int j = 0; j < orderdetailJsonDetailArray.length(); j++) {

                            JSONObject orderdetailJsonobj = orderdetailJsonDetailArray.getJSONObject(j);

                            try {

                                InStoreOrderDetailModel inStoreOrderDetailModel = new InStoreOrderDetailModel();

                                inStoreOrderDetailModel.setStation(orderdetailJsonobj.getString("station"));
                                inStoreOrderDetailModel.setSku(orderdetailJsonobj.getString("sku"));
                                inStoreOrderDetailModel.setDiscount(orderdetailJsonobj.getDouble("discount"));
                                inStoreOrderDetailModel.setExtprice(orderdetailJsonobj.getDouble("extprice"));
                                inStoreOrderDetailModel.setQty(orderdetailJsonobj.getString("Qty"));
                                inStoreOrderDetailModel.setSize(orderdetailJsonobj.getString("size"));
                                inStoreOrderDetailModel.setSizename(orderdetailJsonobj.getString("sizename"));
                                inStoreOrderDetailModel.setItemName(orderdetailJsonobj.getString("ItemName"));
                                inStoreOrderDetailModel.setPrice(orderdetailJsonobj.getDouble("Price"));
                                inStoreOrderDetailModel.setInvoiceID(orderdetailJsonobj.getString("InvoiceID"));
                                inStoreOrderDetailModel.setSizeFlag(orderdetailJsonobj.getInt("SizeFlag"));
                                inStoreOrderDetailModel.setPoints(orderdetailJsonobj.getDouble("points"));
                                inStoreOrderDetailModel.setBottledeposit(orderdetailJsonobj.getDouble("bottledeposit"));
                                inStoreOrderDetailModel.setTxtDataValue(orderdetailJsonobj.getString("txtDataValue"));

                                orderDetailListInstore.add(j,inStoreOrderDetailModel);

                            } catch (NullPointerException e) {
                                Log.d("excep", "" + e);
                            } catch (Exception e) {
                                Log.d("excep", "" + e);
                            }
                        }

                        instorePurchaseDetailModel.setOrderDetailList(orderDetailListInstore);

                        liInstorePurchaseModelList.add(instorePurchaseDetailModel);
//                    }
                    return response;

                } catch (Exception e) {
                    Log.d("exc", "" + e);
                }

//                try{
////                    ObjectMapper objectMapper = new ObjectMapper();
////                    TypeFactory typeFactory = objectMapper.getTypeFactory();
////                    CollectionType collectionType = typeFactory.constructCollectionType(
////                            List.class, InstorePurchaseDetailModel.class);
////
////                    liInstorePurchaseModelList = objectMapper.readValue(response, collectionType);
////
////                }catch (Exception e){
////                    Log.d("exc","" +e);
////                }

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

        if (liInstorePurchaseModelList != null) {
            taskInstorePurchaseDetailEvent.onInstorePurchaseDetailResult(liInstorePurchaseModelList,positi);
        }

        if (loading != null) {
            loading.dismiss();
        }

    }

}
