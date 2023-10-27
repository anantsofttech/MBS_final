package com.aspl.mbsmodel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 12/5/2017.
 */

public class PinModel {
    public String _result;
    public String _type;
    public String _address;
    public String _city;
    public String _email;
    public String _name;
    public String _phone;
    public String _state;
    public String _storeTagLine;
    public String _zip;

    public PinModel(JSONObject fotterObj) throws JSONException {
        this._result=fotterObj.getString("Result");
        this._type=fotterObj.getString("Type");
        this._address=fotterObj.getString("address");
        this._city=fotterObj.getString("city");
        this._email=fotterObj.getString("email");
        this._name=fotterObj.getString("name");
        this._phone=fotterObj.getString("phone");
        this._state=fotterObj.getString("state");
        this._storeTagLine=fotterObj.getString("storeTagLine");
        this._zip=fotterObj.getString("zip");
    }
}
