package com.aspl.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.fragment.HomepageFragment;
import com.aspl.fragment.Login;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.DataFrontModel;
import com.aspl.mbsmodel.MbsDataModel;
import com.aspl.mbsmodel.UserModel;

import java.util.List;

/**
 * Created by new on 07/13/2017.
 */
public class FooterAdapter extends BaseAdapter {

    Context context;
    List<MbsDataModel> FooterList;
    //
    public static String FACEBOOK_URL = "https://www.facebook.com"; //https://www.facebook.com/pinaksoftware
    public static String FACEBOOK_PAGE_ID = ""; //pinaksoftware
    public static String TWITTER_PACKAGE = "com.twitter.android";
    public static String GOOGLE_MAP_PACKAGE = "com.google.android.apps.maps";
    public static String LINKIDN_PACKAGE = "com.linkedin.android";
    public static String LINKDIN_PAGE_URL = "https://www.linkedin.com";
    public static String INSTAGRAM_URL = "www.instagram.com";
    TextView listTitle = null;

    public FooterAdapter(Context context, List<MbsDataModel> FooterList) {
        this.context = context;
        this.FooterList = FooterList;
    }

    @Override
    public int getCount() {
        return FooterList.size();
    }


    @Override
    public Object getItem(int i) {
        return getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            if (FooterList.get(position).status) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.list_group, null);
            }
        }

        MbsDataModel expandedListText = null;
        expandedListText = FooterList.get(position);
        if (view != null) {
            listTitle = view.findViewById(R.id.listTitle);
        }
        if (Constant.SCREEN_LAYOUT == 1) {
//            if (expandedListText.PageTitle.equalsIgnoreCase("Mobile View")) {
//                listTitle.setVisibility(View.GONE);
//            }

//            if (expandedListText.PageTitle.equalsIgnoreCase("RecentViewed Items") && UserModel.Cust_mst_ID == null) {
//                listTitle.setVisibility(View.GONE);
//            } else {
//                listTitle.setVisibility(View.VISIBLE);
//            }
//
            //Edited by janvi 12th dec

            if (expandedListText.PageTitle.equalsIgnoreCase("New Additions")
                    || expandedListText.PageTitle.equalsIgnoreCase("Staff Picks")
                    || expandedListText.PageTitle.equalsIgnoreCase("Home Page Items")
                    || expandedListText.PageTitle.equalsIgnoreCase("Items On Promotion")
                    || expandedListText.PageTitle.equalsIgnoreCase("Special Offers")
                    || expandedListText.PageTitle.equalsIgnoreCase("Promotion 1")
                    || expandedListText.PageTitle.equalsIgnoreCase("Promotion 2")
                    || expandedListText.PageTitle.equalsIgnoreCase("SpecialOffer 3")
                    || expandedListText.PageTitle.equalsIgnoreCase("SpecialOffer 1")
                    || expandedListText.PageTitle.equalsIgnoreCase("Announcement 1")
                    || expandedListText.PageTitle.equalsIgnoreCase("SpecialOffer 2")
                    || expandedListText.PageTitle.equalsIgnoreCase("Announcement 2")
                    || expandedListText.PageTitle.equalsIgnoreCase("SpecialOffer 4")) {

                if (expandedListText.inv_count > 0) {

                    listTitle.setText(expandedListText.PageName);

                } else {
                    listTitle.setVisibility(View.GONE);
                }
            } else {

                if (expandedListText.PageTitle.equalsIgnoreCase("RecentViewed Items") && UserModel.Cust_mst_ID == null) {
                    listTitle.setVisibility(View.GONE);
                } else {
                    listTitle.setVisibility(View.VISIBLE);
                }

                listTitle.setText(expandedListText.PageName);

                if (expandedListText.PageTitle.equalsIgnoreCase("Mobile View") || expandedListText.PageTitle.equalsIgnoreCase("RecentViewed Items")) {
                    listTitle.setVisibility(View.GONE);
                }

                if (expandedListText.PageTitle.equalsIgnoreCase("HelpWanted")){
                    listTitle.setVisibility(View.GONE);
                }
            }

            //end*******

            if (expandedListText.status) {
                if (expandedListText.PageName.equalsIgnoreCase("Facebook")
                        || expandedListText.PageName.equalsIgnoreCase("Linkedln")
                        || expandedListText.PageName.equalsIgnoreCase("instagram")
                        ) {
                    listTitle.setText(expandedListText.PageName);
                } else {
                    listTitle.setText(expandedListText.PageName);
                }
            }

        } else if (Constant.SCREEN_LAYOUT == 2) {
            listTitle.setText(expandedListText.PageName);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                MbsDataModel footerModel = FooterList.get(position);
                String SessionUD = MainActivity.getUserId().equalsIgnoreCase("0") ? DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011" : "0";
                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().mDrawer.closeDrawers();
                    MainActivity.getInstance().llsortandfilter.setVisibility(View.GONE);
                    //MainActivity.getInstance().mContainer.removeAllViews();
//                    if (Build.VERSION.SDK_INT < 18) {
//                        MainActivity.getInstance().mContainer.clearView();
//                    } else {
//                        MainActivity.getInstance().mContainer.loadUrl("about:blank");
//                    }

                    String page = footerModel.PageTitle;
                    String slideURL = null;
                    if (page.equals("About Us")){

                        if (Constant.SCREEN_LAYOUT == 1) {
                            MainActivity.getInstance().loadAboutusFragment();
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            MainActivityDup.getInstance().loadAboutusFragment();
                        }

                        //about us webpage
                        //slideURL = Constant.URL_PAGE_ABOUT_US + Constant.STOREID;

                    } else if (page.equals("Blog"))
                        slideURL = Constant.URL_PAGE_BLOG + Constant.STOREID;
                    else if (page.equals("FAQ"))
                        slideURL = Constant.URL_PAGE_FAQ + Constant.STOREID;
                    else if (page.equals("legal") || page.equals("Legal"))
                        slideURL = Constant.URL_PAGE_LEGAL + Constant.STOREID;
                    else if (page.equals("pay_and_refund") || page.equals("Payments & Refunds"))
                        slideURL = Constant.URL_PAGE_PAYMENTS_REFUNDS + Constant.STOREID;
                    else if (page.equals("Privacy"))
                        slideURL = Constant.URL_PAGE_PRIVACY + Constant.STOREID;
                        //***************Edited by Varun *******************
                    else if (page.equals("AccessibilityPolicy"))
                        slideURL = Constant.URL_PAGE_ACCESSIBILITY + Constant.STOREID;
                        // ***************** END *****************
                    else if (page.equals("Shipping"))
                        slideURL = Constant.URL_PAGE_SHIPPING + Constant.STOREID;
                    else if (page.equals("Support & Customer Service"))
                        slideURL = Constant.URL_PAGE_SUPPORT + Constant.STOREID;
                    else if (page.equals("Terms"))
                        slideURL = Constant.URL_PAGE_TERMS + Constant.STOREID;
                    else if (page.equals("Help"))
                        slideURL = Constant.URL_PAGE_HELP + Constant.STOREID;
                    else if (page.equalsIgnoreCase(/*"Store Hours"*/ "Store/Delivery Hours")) {
//                        slideURL = Constant.URL_PAGE_STORE_HOURS_DELIVERY_HOURS;

                        if (Constant.SCREEN_LAYOUT == 1) {
                            MainActivity.getInstance().loadStoreandDeliveryFragment("Store/Delivery Hours");
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            MainActivityDup.getInstance().loadStoreandDeliveryFragment("Store/Delivery Hours");
                        }

                    }else if (page.equalsIgnoreCase("Event Calendar") || page.equalsIgnoreCase("DisplayEventCalender"))
                        slideURL = Constant.URL_PAGE_EVENTS + Constant.STOREID;
                    else if (page.equalsIgnoreCase(/*"Store Hours"*/ "Store Hours")) {
//                        slideURL = Constant.URL_PAGE_STORE_HOURS + Constant.STOREID;

                        if (Constant.SCREEN_LAYOUT == 1) {
                            MainActivity.getInstance().loadStoreandDeliveryFragment("Store Hours");
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            MainActivityDup.getInstance().loadStoreandDeliveryFragment("Store Hours");
                        }
                    }
                    else if (page.equals("Contact Us")) {

                        if (Constant.SCREEN_LAYOUT == 1) {
                            MainActivity.getInstance().loadContactUsFragment();
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            MainActivityDup.getInstance().loadContactUsFragment();
                        }
                        //contact us webpage
//                        slideURL = Constant.URL_PAGE_CONTACT_US + Constant.STOREID;

                    } else if (page.equals("Event Calendar") || page.equals("DisplayEventCalender")) {
                        slideURL = Constant.URL_PAGE_EVENTS + Constant.STOREID;
                    } else if (page.equals("GiftCard")) {
//                        slideURL = Constant.URL_PAGE_GIFTCARD + Constant.STOREID;

                        if (Constant.SCREEN_LAYOUT == 1) {
                            MainActivity.getInstance().loadGiftCardFragment();
                        } else if (Constant.SCREEN_LAYOUT == 2) {
                            MainActivityDup.getInstance().loadGiftCardFragment();
                        }

                    } else if (page.equals("HelpWanted")) {
                        slideURL = Constant.URL_PAGE_JOBAPPLICATION + Constant.STOREID;
                    } else if (page.equals("Rewards")) {
                        slideURL = Constant.URL_PAGE_REWARDS + Constant.STOREID;
                    } else if (page.equals("New Additions")) {
//                        slideURL = Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + MainActivity.getUserId()
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + "newddition";
//
                        DataFrontModel dataFrontModel = ReturnDataFrontModel("New Additions");
                        HomepageFragment.getInstance().callWSForViewall(dataFrontModel);


                    } else if (page.equals("RecentViewed Items")) {
//                        slideURL = Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + MainActivity.getUserId()
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + "RecentViewed";

                        DataFrontModel dataFrontModel = ReturnDataFrontModel("RecentViewed Items");
                        HomepageFragment.getInstance().callWSForViewall(dataFrontModel);

                    } else if (page.equals("Staff Picks")) {
//                        slideURL = Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + MainActivity.getUserId()
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + "staffpick";
                        DataFrontModel dataFrontModel = ReturnDataFrontModel("Staff Picks");
                        HomepageFragment.getInstance().callWSForViewall(dataFrontModel);

                    } else if (page.equals("Home Page Items")) {

//                        slideURL = Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + MainActivity.getUserId()
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + "homepageitem";

                        DataFrontModel dataFrontModel = ReturnDataFrontModel("Home Page Items");
                        HomepageFragment.getInstance().callWSForViewall(dataFrontModel);

                    } else if (page.equals("Items On Promotion")) {
//                        slideURL = Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + MainActivity.getUserId()
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + "promotion";

                        DataFrontModel dataFrontModel = ReturnDataFrontModel("Items On Promotion");
                        HomepageFragment.getInstance().callWSForViewall(dataFrontModel);

                    } else if (page.equals("Special Offers")) {
//                        slideURL = Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + MainActivity.getUserId()
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + "specialoffer";

                        DataFrontModel dataFrontModel = ReturnDataFrontModel("Special Offers");
                        HomepageFragment.getInstance().callWSForViewall(dataFrontModel);

                    } else if (page.equals("All Items")) {
//                        slideURL = Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + MainActivity.getUserId()
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + "allItem";

                        DataFrontModel dataFrontModel = ReturnDataFrontModel("All Items");
                        HomepageFragment.getInstance().callWSForViewall(dataFrontModel);

                    } else {
                        DataFrontModel dataPramotion1 = ReturnDataFrontModel("Promotion 1");
                        DataFrontModel dataPramotion2 = ReturnDataFrontModel("Promotion 2");
                        DataFrontModel dataPramotion3 = ReturnDataFrontModel("SpecialOffer 1");
                        DataFrontModel dataPramotion4 = ReturnDataFrontModel("SpecialOffer 2");
                        DataFrontModel dataPramotion5 = ReturnDataFrontModel("Announcement 1");
                        DataFrontModel dataPramotion6 = ReturnDataFrontModel("Announcement 2");
                        DataFrontModel dataPramotion7 = ReturnDataFrontModel("SpecialOffer 3");
                        DataFrontModel dataPramotion8 = ReturnDataFrontModel("SpecialOffer 4");
                        
                        if (page.equals("Promotion 1")) {
//                            setBlocktoPage(dataPramotion1);
                            HomepageFragment.getInstance().callWSForViewall(dataPramotion1);

                        } else if (page.equals("Promotion 2")) {
//                            setBlocktoPage(dataPramotion2);
                            HomepageFragment.getInstance().callWSForViewall(dataPramotion2);

                        } else if (page.equals("SpecialOffer 1")) {
//                            setBlocktoPage(dataPramotion3);
                            HomepageFragment.getInstance().callWSForViewall(dataPramotion3);

                        } else if (page.equals("SpecialOffer 2")) {
//                            setBlocktoPage(dataPramotion4);
                            HomepageFragment.getInstance().callWSForViewall(dataPramotion4);

                        } else if (page.equals("Announcement 1")) {
//                            setBlocktoPage(dataPramotion5);
                            HomepageFragment.getInstance().callWSForViewall(dataPramotion5);

                        } else if (page.equals("Announcement 2")) {
//                            setBlocktoPage(dataPramotion6);
                            HomepageFragment.getInstance().callWSForViewall(dataPramotion6);

                        } else if (page.equals("SpecialOffer 3")) {
//                            setBlocktoPage(dataPramotion7);
                            HomepageFragment.getInstance().callWSForViewall(dataPramotion7);

                        } else if (page.equals("SpecialOffer 4")) {
//                            setBlocktoPage(dataPramotion8);
                            HomepageFragment.getInstance().callWSForViewall(dataPramotion8);

                        }
                    }
                    if (slideURL != null) {
                        MainActivity.getInstance().LoadWebVew(slideURL);
                    }
                    //Edited by Janvi 22th Nov ******
                    else if (page.contains("facebook")) {
//                        else if (page.equals("Facebook")) {
                        MainActivity.getInstance().loadHomeWebPage();
                        FACEBOOK_URL = footerModel.PageTitle;
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        String facebookUrl = getFacebookPageURL(MainActivity.getInstance());
                        facebookIntent.setData(Uri.parse(facebookUrl));
                        MainActivity.getInstance().startActivity(facebookIntent);
                    }
//                    else if (page.equals("Linkedln")) {
                    //LINKDIN_PAGE_URL = footerModel.PageTitle;
                   /* else if (page.contains("linkedin")) {
                        //end *************
                        MainActivity.getInstance().loadHomeWebPage();
                        Intent intent = null;
                        try {
                            MainActivity.getInstance().getPackageManager().getPackageInfo("com.linkedin.android", 0);
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://profile/")); //linkedin://profile/mehultpatel
                        } catch (Exception e) {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINKDIN_PAGE_URL)); //https://www.linkedin.com/in/olankimehuln
                        } finally {
                            MainActivity.getInstance().startActivity(intent);
                        }
                    } else if (page.contains("instagram")) {
                        Uri uri = Uri.parse("http://instagram.com"); //http://instagram.com/_u/solankimehuln
                        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
                        insta.setPackage("com.instagram.android");

                        if (isIntentAvailable(MainActivity.getInstance(), insta)) {
                            MainActivity.getInstance().startActivity(insta);
                        } else {
                            MainActivity.getInstance().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com"))); //http://instagram.com/solankimehuln
                        }
                    }

                    else if (page.contains("yelp")) {
                        String uriString = "https:\\/\\/yelp.com";
                        MainActivity.getInstance().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uriString)));
                    }

                    else if (page.contains("foursquare")) {
                        String uriString = "https:\\/\\/foursquare.com";
                        MainActivity.getInstance().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uriString)));
                    }*/
                    else if (page.contains("linkedin")) {
                        MainActivity.getInstance().loadHomeWebPage();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(page));
                        // Check if the LinkedIn app is installed
                        try {
                            MainActivity.getInstance().getPackageManager().getPackageInfo("com.linkedin.android", 0);
                            intent.setPackage("com.linkedin.android"); // Set the package name to open LinkedIn app specifically
                        } catch (Exception e) {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(page));
                        } finally {
                            MainActivity.getInstance().startActivity(intent);
                        }
                    } else if (page.contains("instagram")) {
                        MainActivity.getInstance().loadHomeWebPage();
                        Uri uri = Uri.parse(page);
                        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
                        insta.setPackage("com.instagram.android"); // Set the package name to open Instagram app specifically
                        // Check if the Instagram app is installed
                        if (isIntentAvailable(MainActivity.getInstance(), insta)) {
                            MainActivity.getInstance().startActivity(insta);
                        } else {
                            // Instagram app is not installed, open the Instagram profile in the default web browser
                            MainActivity.getInstance().startActivity(new Intent(Intent.ACTION_VIEW, uri));
                        }
                    }
                    else if (page.contains("yelp")) {
                        MainActivity.getInstance().loadHomeWebPage();
                        Uri uri = Uri.parse(page);
                        Intent yelpIntent = new Intent(Intent.ACTION_VIEW, uri);
                        // Check if the Yelp app is installed
                        if (isIntentAvailable(MainActivity.getInstance(), yelpIntent)) {
                            MainActivity.getInstance().startActivity(yelpIntent);
                        } else {
                            // Yelp app is not installed, open the Yelp profile in the default web browser
                            MainActivity.getInstance().startActivity(new Intent(Intent.ACTION_VIEW, uri));
                        }
                    }
                    else if (page.contains("foursquare")) {
                        MainActivity.getInstance().loadHomeWebPage();
                        Uri uri = Uri.parse(page);
                        Intent foursquareIntent = new Intent(Intent.ACTION_VIEW, uri);
                        // Check if the Foursquare app is installed
                        if (isIntentAvailable(MainActivity.getInstance(), foursquareIntent)) {
                            MainActivity.getInstance().startActivity(foursquareIntent);
                        } else {
                            // Foursquare app is not installed, open the Foursquare profile in the default web browser
                            MainActivity.getInstance().startActivity(new Intent(Intent.ACTION_VIEW, uri));
                        }
                    }

                    else if (page.equals("Rate App")) {
                        //MainActivity.getInstance().LoadWebVew(Constant.URL_TEST_HOME);
                        MainActivity.getInstance().launchMarket();
                    } else if (page.equals("Share App")) {
                        MainActivity.getInstance().shareApp();
                    }
                } else if (Constant.SCREEN_LAYOUT == 2) {

//                    //Edited by Janvi 23thOct
                    String page = footerModel.PageName;
                    if (page.equals("Wish List")) {
                        MainActivityDup.getInstance().loadWishListFragment();
                    } else if (page.equals("Log Off")) {
                        Login.onLogOff();
                    } else if (page.equalsIgnoreCase("Order History")) {
//                        MainActivityDup.getInstance().showHomePage();
                        if (footerModel.PageContent != null)
//                            MainActivityDup.getInstance().LoadWebVew(footerModel.PageContent + "?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID + "&type=online");
//
                        MainActivityDup.getInstance().loadOrderHistoryFragment("Online Purchases");

                    }else if(page.equalsIgnoreCase("Card On File")) {

//                            if (Constant.SCREEN_LAYOUT == 1) {
//                                MainActivity.getInstance().loadCardOnFileFragment();
//                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                MainActivityDup.getInstance().loadCardOnFileFragment();
//                            }

                    }else if(page.equalsIgnoreCase("Rewards")){
//                            if (Constant.SCREEN_LAYOUT == 1) {
//                                MainActivity.getInstance().loadRewardFragment();
//                            } else if (Constant.SCREEN_LAYOUT == 2) {
                                MainActivityDup.getInstance().loadRewardFragment();
//                            }
                    }else if(page.equalsIgnoreCase("Change Password")) {

//                            if (Constant.SCREEN_LAYOUT == 1) {
//                                MainActivity.getInstance().loadChangePasswordFragment();
//
//                            }else if(Constant.SCREEN_LAYOUT==2) {
//                                MainActivityDup.getInstance().showHomePage();
//                                MainActivityDup.getInstance().LoadWebVew(expandedListText.PageContent + "?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID);
//                            }

                            MainActivityDup.getInstance().loadChangePasswordFragment();

                    }else if(page.equalsIgnoreCase("Manage Account")) {

                        MainActivityDup.getInstance().loadManageAccountFragment();

                    }
                    else if(page.equalsIgnoreCase("Shipping Addresses")) {

                        MainActivityDup.getInstance().loadShippingAddressFragment();

                    }else if(page.equalsIgnoreCase("Delete My Online Account")){
                        MainActivityDup.getInstance().loadDeleteAccountWS();
                    }
                    else{
//Event Calendar
                        MainActivityDup.getInstance().showHomePage();
                        if (footerModel.PageContent != null)
                            MainActivityDup.getInstance().LoadWebVew(footerModel.PageContent + "?customerid=" + UserModel.Cust_mst_ID + "&storeno=" + Constant.STOREID);
                    }
                    //end ***********
                }
            }
        });
        return view;
    }

//    public void setBlocktoPage(DataFrontModel model) {
//        String URL_BP = "";
//        String SessionUD = MainActivity.getUserId().equalsIgnoreCase("0") ? DeviceInfo.getDeviceId(MainActivity.getInstance()) + "0011" : "0";
//        if (!model.getBlockSpecialoffer().isEmpty()) {
//            URL_BP = Constant.URL + "/inventory/inventoryapp"
//                    + "?customerid=" + MainActivity.getUserId()
//                    + "&sessionid=" + /*"0"*/SessionUD
//                    + "&storeno=" + Constant.STOREID
//                    + "&type=" + "specialoffer"
//                    + "&value1=" + "" + model.getBlockSpecialoffer() + "&value2=" + "0";
//        } else {
//            URL_BP = Constant.URL + "/inventory/inventoryapp"
//                    + "?customerid=" + MainActivity.getUserId()
//                    + "&sessionid=" + SessionUD
//                    + "&storeno=" + Constant.STOREID
//                    + "&type=" + "promotion"
//                    + "&value1=" + "" + model.getBlockStratprice() + "&value2=" + model.getBlockEndprice();
//        }
//        Log.d("Log", "URL_BP=" + URL_BP);
//        if (Constant.SCREEN_LAYOUT == 1) {
//            MainActivity.getInstance().mContainer.removeAllViews();
//            MainActivity.getInstance().LoadWebVew(URL_BP);
//        } else if (Constant.SCREEN_LAYOUT == 2) {
//            MainActivityDup.getInstance().mContainer.removeAllViews();
//            MainActivityDup.getInstance().LoadWebVew(URL_BP);
//        }
//    }

    public DataFrontModel ReturnDataFrontModel(String s) {
        DataFrontModel dataFrontModel = null;
        for (int i = 0; i < Constant.BLOCKDATAFRONTLIST.size(); i++) {
            if (Constant.BLOCKDATAFRONTLIST.get(i).getBlockActualtext().trim().equalsIgnoreCase(s)) {
                dataFrontModel = Constant.BLOCKDATAFRONTLIST.get(i);
                break;
            }
        }
        return dataFrontModel;
    }

    private boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * Reference Link
     **/
    //https://stackoverflow.com/questions/34564211/open-facebook-page-in-facebook-app-if-installed-on-android/34564284


    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        Log.e("Log", "Facebook URL=" + FACEBOOK_URL);
        Log.e("Log", "Facebook URL= ID" + FACEBOOK_PAGE_ID);
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            boolean activated = packageManager.getApplicationInfo("com.facebook.katana", 0).enabled;
            if (activated) {
                if ((versionCode >= 3002850)) { //newer versions of fb app
                    return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
                } else { //older versions of fb app
                    return "fb://page/" + FACEBOOK_PAGE_ID;
                }
            } else {
                return FACEBOOK_URL; //normal web url
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    private static boolean appInstalledOrNot(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return false;
    }

}
