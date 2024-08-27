package com.aspl.mbs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl._scanner.OcrCaptureActivity;
import com.aspl.fragment.CardFragment;
import com.aspl.fragment.DeliveryOptionsFragment;
import com.aspl.fragment.Login;
import com.aspl.fragment.OrderSummaryFragment;
import com.aspl.fragment.PaymentFragment;
import com.aspl.mbsmodel.UserModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by admin on 6/29/2018.
 */

public class MicActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener, View.OnClickListener {

    private static final int REQ_CODE_SPEECH_INPUT1 = 200;
    private static final String TAG = "Log";
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech myTTS;
    private SpeechRecognizer mSpeechRecognizer;
    public static int RECORD_AUDIO_PERMITION = 101;
    Intent intent;
    LinearLayout llBottomParent, llMic, llSearch;
    ImageButton imgClose, imgMic, imgSearch;
    ImageView imgGif;
    TextView txtMic, txtSearch, txtHeader, txtsearchinst;

    private Drawable placeholder;

    public void loadImage(ImageView imageView) {
        if (Constant.SCREEN_LAYOUT == 1) {
            placeholder = ContextCompat.getDrawable(MainActivity.getInstance(), R.drawable.progress_bar);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            placeholder = ContextCompat.getDrawable(MainActivityDup.getInstance(), R.drawable.progress_bar);
        }
        Glide.with(imageView.getContext())
                .load(R.drawable.mic_gif)
                .placeholder(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .fitCenter()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //getProgressBarIndeterminate() = drawable;
                    }
                });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymic);
        FirebaseCrash.setCrashCollectionEnabled(false);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        /*Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        startVoiceInput1();*/
        llBottomParent = findViewById(R.id.llBottomParent);
        txtHeader = findViewById(R.id.txtHeader);
        txtsearchinst = findViewById(R.id.txtsearchinst);

        llMic = findViewById(R.id.llMic);

        llSearch = findViewById(R.id.llSearch);

        imgGif = findViewById(R.id.imgGif);
        loadImage(imgGif);
        imgClose = findViewById(R.id.imgClose);
        imgClose.setOnClickListener(this);
        llMic.setOnClickListener(this);
        llSearch.setOnClickListener(this);

        imgMic = findViewById(R.id.imgMic);
        imgSearch = findViewById(R.id.imgSearch);
        txtMic = findViewById(R.id.txtMic);
        txtSearch = findViewById(R.id.txtSearch);

        imgMic.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
        txtMic.setOnClickListener(this);
        txtSearch.setOnClickListener(this);

        if (setAudio_Record_Permition() > 0) {
            startVoiceInput1();
        }
        startVoiceInput1();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpeechRecognizer.startListening(intent);
            }
        });

       /* new CountDownTimer(20000, 20000) {

            public void onTick(long millisUntilFinished) {
                //do nothing, just let it tick
                Log.e("Log","millisUntilFinished="+millisUntilFinished);
            }

            public void onFinish() {
                mSpeechRecognizer.stopListening();
            }
        }.start();*/
        //  getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80000000")));
    }

    private void startVoiceInput1() {
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "BoxSalt");
        intent.putExtra("android.speech.extra.DICTATION_MODE", true);//Last added
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, false);//Last added
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        SpeechRecognitionListener listener = new SpeechRecognitionListener();
        mSpeechRecognizer.setRecognitionListener(listener);

    }

    @Override
    public void onClick(View v) {
        if (v == llMic || v == imgMic || v == txtMic) {
            mSpeechRecognizer.startListening(intent);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imgGif.setVisibility(View.VISIBLE);
                    llBottomParent.setVisibility(View.INVISIBLE);
                }
            });
        }
        if (v == llSearch || v == imgSearch || v == txtSearch) {
//
            if (Constant.SCREEN_LAYOUT == 1) {
//
//                //Edited by janvi 27th dec *******
////                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
////                MainActivity.getInstance().mSearchedt.requestFocus();
////                MainActivity.getInstance().mSearchedt.setFocusableInTouchMode(true);
//
                Constant.isFromMic = true;
//
//                //end*************
//
            } else if (Constant.SCREEN_LAYOUT == 2) {
//
//                //Edited by janvi 27th dec *******
////                MainActivityDup.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
////                MainActivityDup.getInstance().mSearchedt.requestFocus();
////                MainActivityDup.getInstance().mSearchedt.setFocusableInTouchMode(true);
//
                Constant.isFromMicSeclayout = true;
//
//                //end*************
            }
            finish();
        }
        if (v == imgClose) {
            finish();
        }
    }

    protected class SpeechRecognitionListener implements RecognitionListener {

        @Override
        public void onBeginningOfSpeech() {
            //Log.d(TAG, "onBeginingOfSpeech");
        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {
            //Log.d(TAG, "onEndOfSpeech");
        }

        @Override
        public void onError(int error) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imgGif.setVisibility(View.INVISIBLE);
                    llBottomParent.setVisibility(View.VISIBLE);
                    txtHeader.setText("We didn't quite get that");
                    txtsearchinst.setText("Try again, such as....");
                }
            });

            //mSpeechRecognizer.startListening(intent);
            //Log.d(TAG, "error = " + error);
        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.d(TAG, "onReadyForSpeech"); //$NON-NLS-1$
        }

        @Override
        public void onResults(Bundle results) {
            Log.d(TAG, "onResults"); //$NON-NLS-1$
            ArrayList<String> matches = null;
            if (Constant.SCREEN_LAYOUT == 1) {
                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
                matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            } else if (Constant.SCREEN_LAYOUT == 2) {
                MainActivityDup.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
                matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            }
            // matches are the return values of speech recognition engine
            // Use these values for whatever you wish to do
            finish();
            callVoiceFunction(matches);
        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(MicActivity.this, "" + result.get(0), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case MY_DATA_CHECK_CODE: {
                if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                    //the user has the necessary data - create the TTS
                    myTTS = new TextToSpeech(this, this);
                } else {
                    //no data - install it now
                    Intent installTTSIntent = new Intent();
                    installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(installTTSIntent);
                }
                break;
            }
            case 1: {
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    // Log.i(TAG, "Place: " + place.getName());
                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    // TODO: Handle the error.
                    // Log.i(TAG, status.getStatusMessage());
                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
                break;
            }
            case REQ_CODE_SPEECH_INPUT1: {
                if (resultCode == RESULT_OK && null != data) {

                }
                break;
            }

        }
    }

    public void callVoiceFunction(ArrayList<String> result) {
        //ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        String sp = result.get(0);

        String CARTLIST = "shopping card,cartlist,cart,cart list,cardlist,card,card list,open cart,display cart,show cart,show me cart,opencart";
        String WISHLIST = "wishlist,wish,wish list,open wishlist,give me my wish,give me my wishlist,give me my wish list";
        String DEPTLIST = "department,department list,list department";
        String IMGREADLIST = "search barcode,search image,barcode,image, image read,barcode read,read barcode,read image,capture barcode,read barcode";
        String SIGNIN = "sign in,signin,sign in please,login,log in,logged in";
        String SIGNOUT = "sign out,signout,sign out please,logout,log out,logged out,logoff,log off";
        String CLOSEAPP = "close,exit,quit,finish,stop,bye,good bye,close app,exit from app,quit app,finish app,stop app";
        String ABOUTUS = "about,aboutus,about us";
        String CONTACTUS = "contact,contactus,contact us";
        String HOME = "home,dashboard,homepage,open home,open dashboard,open homepage";
        String BLOG = "blog,diary";
        String FAQ = "f a q,faq,frequent question,frequent";
        String LEGAL = "ratified,jural,sanctioned,judicial,juristic,statutory,sub judice,legitimate,lawful,licit,legality";
        String PRIVACY = "Privacy,covertness,confidentiality,privateness,secrecy,hiddenness,concealment,hiding";
        String SHIPPING = "shipping,shipment,ship,shipped";
        String SUPPORT = "Support,Support and Customer Service,service";
        String TERMS = "terms,term,policy,condition,aggreement";
        String HELP = "help,assist,help out";

        String STORE_HOUR = "store hour,storehour,store hours";
        String HAND_DELIVERY_HOUR = "delivery hour,deliveryhour,delivery hours,hand delivery,handdelivery";

        /*
        * Fragment Button functionality by speech.
        *
        *
        * */
        String NEXT = "next,Please next,move on";
        String CONT_SHOP = "Continue shopping,Continueshopping,Continue shop,Carry on,carryon";
        String place_order = "Place Order,Placeorder,place my order,place my orders";
        String Previouse = "Previouse,prev,back,take me back";
        String ret_to_home = "return home,take me home,load home,return to home,return me home";

        /*
        *
        * Delivery option selection
        * */

        String del_1="Pick up at Store,pick up store";
        String del_11="Pay at store,pay store";
        String del_12="pay with card,pay by card,pay card";
        String del_2="Hand delivery,deliver by hand";
        String del_3="select Ship";
        String del_31="Ship to a different address";

        String[] arrcart = CARTLIST.split(",");
        String[] arrWish = WISHLIST.split(",");
        String[] arrdept = DEPTLIST.split(",");
        String[] arrimgread = IMGREADLIST.split(",");
        String[] arrsignin = SIGNIN.split(",");
        String[] arrsignout = SIGNOUT.split(",");
        String[] arrclose = CLOSEAPP.split(",");
        String[] arrhome = HOME.split(",");
        String[] arraboutus = ABOUTUS.split(",");
        String[] arrcontactus = CONTACTUS.split(",");
        String[] arrblog = BLOG.split(",");
        String[] arrfaq = FAQ.split(",");
        String[] arrlegal = LEGAL.split(",");
        String[] arrprivacy = PRIVACY.split(",");
        String[] arrshipping = SHIPPING.split(",");
        String[] arrsupport = SUPPORT.split(",");
        String[] arrterms = TERMS.split(",");
        String[] arrhelp = HELP.split(",");
        String[] arrstore = STORE_HOUR.split(",");
        String[] arrcontinu_shop = CONT_SHOP.split(",");
        String[] arrNext = NEXT.split(",");
        String[] arrplace_order = place_order.split(",");
        String[] arrPreviouse = Previouse.split(",");
        String[] arrret_to_home = ret_to_home.split(",");
        String[] arrHandDelivery = HAND_DELIVERY_HOUR.split(",");

        String[] arrdel_1 = del_1.split(",");
        String[] arrdel_11 = del_11.split(",");
        String[] arrdel_12 = del_12.split(",");
        String[] arrdel_2 = del_2.split(",");
        String[] arrdel_3 = del_3.split(",");
        String[] arrdel_31 = del_31.split(",");


        List cartlist = Arrays.asList(arrcart);
        List wishlist = Arrays.asList(arrWish);
        List deptlist = Arrays.asList(arrdept);
        List imgreadlist = Arrays.asList(arrimgread);
        List signinList = Arrays.asList(arrsignin);
        List signoutList = Arrays.asList(arrsignout);
        List closeList = Arrays.asList(arrclose);
        List homeList = Arrays.asList(arrhome);
        List aboutus = Arrays.asList(arraboutus);
        List contactus = Arrays.asList(arrcontactus);
        List blogLis = Arrays.asList(arrblog);
        List faqLis = Arrays.asList(arrfaq);
        List legalLis = Arrays.asList(arrlegal);
        List privacyLis = Arrays.asList(arrprivacy);
        List termsLis = Arrays.asList(arrterms);
        List helpLis = Arrays.asList(arrhelp);
        List shippingList = Arrays.asList(arrshipping);
        List supportList = Arrays.asList(arrsupport);
        List storehourtList = Arrays.asList(arrstore);
        List continue_shopList = Arrays.asList(arrcontinu_shop);
        List NextList = Arrays.asList(arrNext);
        List place_orderList = Arrays.asList(arrplace_order);
        List arrPreviouseList = Arrays.asList(arrPreviouse);
        List arrret_to_homeList = Arrays.asList(arrret_to_home);
        List arr_handDelList=Arrays.asList(arrHandDelivery);

        List del_1List = Arrays.asList(arrdel_1);
        List del_11List = Arrays.asList(arrdel_11);
        List del_12List = Arrays.asList(arrdel_12);
        List del_2List = Arrays.asList(arrdel_2);
        List del_3List = Arrays.asList(arrdel_3);
        List del_31List = Arrays.asList(arrdel_31);


        String speechLower = sp.trim().toLowerCase();

        Fragment cardfragment = new CardFragment();
        View cardRootView = cardfragment.getView();
        if (CardFragment.getInstance() != null) {
            Log.e("Log", "Card Visible");
        } else {
            Log.e("Log", "Card not Visible");
        }
        Log.e("Log", "SpeechLower=" + speechLower);
        String CurrentFragmen = "";
        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.getInstance().llsortandfilter.setVisibility(View.INVISIBLE);
            CurrentFragmen = MainActivity.getInstance().getCurrentFragment();
            if (contain(speechLower, cartlist) &&!contain(speechLower, del_12List)) {
                MainActivity.getInstance().loadCardFragment();
            } else if (contain(speechLower, wishlist)) {
                MainActivity.getInstance().loadWishListFragment();
            } else if (contain(speechLower, deptlist)) {
                MainActivity.getInstance().callFilterFragment();
            } else if (contain(speechLower, imgreadlist)) {
                Intent intent = new Intent(this, OcrCaptureActivity.class);
                startActivity(intent);
            } else if (contain(speechLower, signinList)) {
                Login.StartLoginDialog("", getBaseContext());
            } else if (contain(speechLower, signoutList)) {
                Login.onLogOff();
            } else if (contain(speechLower, closeList)) {
                finish();
            } else if (contain(speechLower, homeList)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().loadHomeWebPage();
            } else if (contain(speechLower, aboutus)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_ABOUT_US + Constant.STOREID);
            } else if (contain(speechLower, contactus)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_CONTACT_US + Constant.STOREID);
            } else if (contain(speechLower, blogLis)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_BLOG + Constant.STOREID);
            } else if (contain(speechLower, faqLis)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_FAQ + Constant.STOREID);
            } else if (contain(speechLower, legalLis)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_LEGAL + Constant.STOREID);
            } else if (contain(speechLower, privacyLis)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_PRIVACY + Constant.STOREID);
            } else if (contain(speechLower, shippingList)&&!(contain(speechLower, del_3List)||contain(speechLower, del_31List))) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_SHIPPING + Constant.STOREID);
            } else if (contain(speechLower, supportList)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_SUPPORT + Constant.STOREID);
            } else if (contain(speechLower, termsLis)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_TERMS + Constant.STOREID);
            } else if (contain(speechLower, helpLis)) {
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_HELP + Constant.STOREID);
            } else if (contain(speechLower, storehourtList)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS + Constant.STOREID);
            } else if (contain(speechLower, arr_handDelList)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS_DELIVERY_HOURS);
            }else {
                if (contain(speechLower, continue_shopList) && (CardFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("CardFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                           // if(CardFragment.getInstance().btnContinueShoppingEmpty.isShown()) {
                                CardFragment.getInstance().btnContinueShoppingEmpty.callOnClick();
                            //}
                        }
                    };
                    mHandler.postDelayed(r, 100);

                } else if (contain(speechLower, NextList) && (CardFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("CardFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(CardFragment.getInstance().btnNext.isShown()) {
                                CardFragment.getInstance().btnNext.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                } else if (contain(speechLower, NextList) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().btnNext.isShown()) {
                                DeliveryOptionsFragment.getInstance().btnNext.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                } else if (contain(speechLower, del_1List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbPickUpAtStore.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbPickUpAtStore.setChecked(true);

                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_11List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbPickUpAtStore.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbPickUpAtStore.setChecked(true);
                                DeliveryOptionsFragment.getInstance().rbPayAtStore.setChecked(true);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_12List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbPickUpAtStore.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbPickUpAtStore.setChecked(true);
                                DeliveryOptionsFragment.getInstance().rbPayWithCart.setChecked(true);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_2List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbHandOnDelivery.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbHandOnDelivery.setChecked(true);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_3List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbShip.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbShip.setChecked(true);
                                DeliveryOptionsFragment.getInstance().cbxShip.setChecked(false);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_31List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbShip.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbShip.setChecked(true);
                                DeliveryOptionsFragment.getInstance().cbxShip.setChecked(true);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);
                }else if (contain(speechLower, arrPreviouseList) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().btnPrev.isShown()) {
                                DeliveryOptionsFragment.getInstance().btnPrev.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);
                } else if (contain(speechLower, place_orderList) && (PaymentFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("PaymentFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(PaymentFragment.getInstance().btnPlaceOrder.isShown()) {
                                PaymentFragment.getInstance().btnPlaceOrder.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                } else if (contain(speechLower, arrPreviouseList) && (PaymentFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("PaymentFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(PaymentFragment.getInstance().btnPrev.isShown()) {
                                PaymentFragment.getInstance().btnPrev.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);
//
                } else if (contain(speechLower, arrret_to_homeList) && (OrderSummaryFragment.getInstence() != null) && CurrentFragmen.equalsIgnoreCase("OrderSummaryFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(OrderSummaryFragment.getInstence().btnReturnToHome.isShown()) {
                                OrderSummaryFragment.getInstence().btnReturnToHome.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);
                } else {
                    String customerId = "0";
                    if (UserModel.Cust_mst_ID != null && !UserModel.Cust_mst_ID.trim().equalsIgnoreCase("null") )
                        customerId = UserModel.Cust_mst_ID;
                    else
                        customerId = "0";

                    MainActivity.getInstance().mContainer.removeAllViews();
                    Constant.BAR_IMG_DISP = true;
                    MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                            + "?customerid=" + customerId
                            + "&sessionid=" + DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011"
                            + "&storeno=" + Constant.STOREID
                            + "&deptid=" + "0"
                            + "&subdeptid=" + "0"
                            + "&type=" + "allitem"
                            + "&filtertext=" + sp);
                }
            }
        } else {
            MainActivityDup.getInstance().llsortandfilter.setVisibility(View.INVISIBLE);
            CurrentFragmen = MainActivityDup.getInstance().getCurrentFragment();
            if (contain(speechLower, cartlist)&&!contain(speechLower, del_12List)) {
                MainActivityDup.getInstance().loadCardFragment();
            }
            //Edited by Janvi 22th oct
//            else if (contain(speechLower, wishlist)) {
//                MainActivityDup.getInstance().navigationView.setSelectedItemId(R.id.action_wishlist);
//                MainActivityDup.getInstance().loadWishListFragment();
//            }

            //end
            else if (contain(speechLower, deptlist)) {
                MainActivityDup.getInstance().callFilterFragment();
            } else if (contain(speechLower, imgreadlist)) {
                Intent intent = new Intent(this, OcrCaptureActivity.class);
                startActivity(intent);
            } else if (contain(speechLower, signinList)) {
                Login.StartLoginDialog("", getBaseContext());
            } else if (contain(speechLower, signoutList)) {
                Login.onLogOff();
            } else if (contain(speechLower, closeList)) {
                finish();
            } else if (contain(speechLower, homeList)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().loadHomeWebPage();
            } else if (contain(speechLower, aboutus)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_ABOUT_US + Constant.STOREID);
            } else if (contain(speechLower, contactus)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_CONTACT_US + Constant.STOREID);
            } else if (contain(speechLower, blogLis)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_BLOG + Constant.STOREID);
            } else if (contain(speechLower, faqLis)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_FAQ + Constant.STOREID);
            } else if (contain(speechLower, legalLis)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_LEGAL + Constant.STOREID);
            } else if (contain(speechLower, privacyLis)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_PRIVACY + Constant.STOREID);
            } else if (contain(speechLower, shippingList)&&!(contain(speechLower, del_3List)||contain(speechLower, del_31List))) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_SHIPPING + Constant.STOREID);
            } else if (contain(speechLower, supportList)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_SUPPORT + Constant.STOREID);
            } else if (contain(speechLower, termsLis)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_TERMS + Constant.STOREID);
            } else if (contain(speechLower, helpLis)) {
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_HELP + Constant.STOREID);
            } else if (contain(speechLower, storehourtList)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS + Constant.STOREID);
            } else if (contain(speechLower, arr_handDelList)) {
                MainActivity.getInstance().mContent.setVisibility(View.GONE);
                MainActivity.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS_DELIVERY_HOURS);
            } else {
                if (contain(speechLower, continue_shopList) && (CardFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("CardFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            Log.e("Log","Continue shoppin");
                            //if(CardFragment.getInstance().btnContinueShoppingEmpty.isShown()) {
                                CardFragment.getInstance().btnContinueShoppingEmpty.callOnClick();
                            //}
                        }
                    };
                    mHandler.postDelayed(r, 100);

                } else if (contain(speechLower, NextList) && (CardFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("CardFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(CardFragment.getInstance().btnNext.isShown()) {
                                CardFragment.getInstance().btnNext.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                } else if (contain(speechLower, NextList) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().btnNext.isShown()) {
                                DeliveryOptionsFragment.getInstance().btnNext.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);
                }else if (contain(speechLower, del_1List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbPickUpAtStore.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbPickUpAtStore.setChecked(true);

                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_11List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbPickUpAtStore.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbPickUpAtStore.setChecked(true);
                                DeliveryOptionsFragment.getInstance().rbPayAtStore.setChecked(true);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_12List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbPickUpAtStore.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbPickUpAtStore.setChecked(true);
                                DeliveryOptionsFragment.getInstance().rbPayWithCart.setChecked(true);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_2List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbHandOnDelivery.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbHandOnDelivery.setChecked(true);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_3List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbShip.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbShip.setChecked(true);
                                DeliveryOptionsFragment.getInstance().cbxShip.setChecked(false);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                }else if (contain(speechLower, del_31List) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().rbShip.isShown()) {
                                DeliveryOptionsFragment.getInstance().rbShip.setChecked(true);
                                DeliveryOptionsFragment.getInstance().cbxShip.setChecked(true);
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);
                } else if (contain(speechLower, arrPreviouseList) && (DeliveryOptionsFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("DeliveryOptionsFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(DeliveryOptionsFragment.getInstance().btnPrev.isShown()) {
                                DeliveryOptionsFragment.getInstance().btnPrev.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                } else if (contain(speechLower, place_orderList) && (PaymentFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("PaymentFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(PaymentFragment.getInstance().btnPlaceOrder.isShown()) {
                                PaymentFragment.getInstance().btnPlaceOrder.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);

                } else if (contain(speechLower, arrPreviouseList) && (PaymentFragment.getInstance() != null) && CurrentFragmen.equalsIgnoreCase("PaymentFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(PaymentFragment.getInstance().btnPrev.isShown()) {
                                PaymentFragment.getInstance().btnPrev.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);
//
                } else if (contain(speechLower, arrret_to_homeList) && (OrderSummaryFragment.getInstence() != null) && CurrentFragmen.equalsIgnoreCase("OrderSummaryFragment")) {
                    Handler mHandler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            if(OrderSummaryFragment.getInstence().btnReturnToHome.isShown()) {
                                OrderSummaryFragment.getInstence().btnReturnToHome.callOnClick();
                            }
                        }
                    };
                    mHandler.postDelayed(r, 100);
                } else {
                    String customerId = "0";
                    if (UserModel.Cust_mst_ID != null)
                        customerId = UserModel.Cust_mst_ID;
                    else
                        customerId = "0";
                    MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                    MainActivityDup.getInstance().mContainer.removeAllViews();
                    Constant.BAR_IMG_DISP = true;
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
                            + "?customerid=" + customerId
                            + "&sessionid=" + DeviceInfo.getDeviceId(MainActivityDup.getInstance()) + "0011"
                            + "&storeno=" + Constant.STOREID
                            + "&deptid=" + "0"
                            + "&subdeptid=" + "0"
                            + "&type=" + "allitem"
                            + "&filtertext=" + sp);
                }
            }
        }

    }

    public boolean contain(String speechLower, List wordlist) {
        boolean isMAtch = false;
        for (int i = 0; i < wordlist.size(); i++) {
            if (speechLower.contains(wordlist.get(i).toString().toLowerCase())) {
                isMAtch = true;
                break;
            }
        }
        return isMAtch;

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            myTTS.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onDone(String utteranceId) {
                    if (utteranceId.contains("@@@")) {
                        startVoiceInput1();
                    }
                }

                @Override
                public void onError(String utteranceId) {
                }

                @Override
                public void onStart(String utteranceId) {

                }
            });
            if (myTTS.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);

            List<TextToSpeech.EngineInfo> egs = myTTS.getEngines();

            for (int i = 0; i < egs.size(); i++) {
                Log.e("Log", "Engines=" + egs.get(i).name);
            }
            // myTTS.setEngineByPackageName("");
        } else if (status == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUtteranceCompleted(String s) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == RECORD_AUDIO_PERMITION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,
                        "Application will not have audio on record", Toast.LENGTH_SHORT).show();
            } else {
                startVoiceInput1();
            }
        }
    }

    public int setAudio_Record_Permition() {
        int val = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) ==
                    PackageManager.PERMISSION_GRANTED) {
                // put your code for Version>=Marshmallow
                val = 1;

            } else {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.RECORD_AUDIO)) {
                    Toast.makeText(this,
                            "App required access to audio", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{android.Manifest.permission.RECORD_AUDIO
                }, RECORD_AUDIO_PERMITION);
            }

        }
        return val;
    }

}
