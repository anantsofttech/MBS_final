package com.aspl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.MbsDataModel;

import java.util.List;

/**
 * Created by admin on 7/11/2018.
 */

public class FooterRecAdapter extends RecyclerView.Adapter<FooterRecAdapter.MyViewHolder> {

    private List<MbsDataModel> footerList;
    MainActivityDup MainActivityDup;

    public FooterRecAdapter(MainActivityDup MainActivityDupDup, List<MbsDataModel> footerList) {
        this.MainActivityDup= MainActivityDup;
        this.footerList = footerList;
    }
    public static String FACEBOOK_URL = "https://www.facebook.com"; //https://www.facebook.com/pinaksoftware
    public static String FACEBOOK_PAGE_ID = ""; //pinaksoftware
    public static String TWITTER_PACKAGE = "com.twitter.android";
    public static String GOOGLE_MAP_PACKAGE = "com.google.android.apps.maps";
    public static String LINKIDN_PACKAGE = "com.linkedin.android";
    public static String LINKDIN_PAGE_URL = "https://www.linkedin.com";
    public static String INSTAGRAM_URL = "www.instagram.com";
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtfooterName;

        //ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtfooterName = (TextView) itemView.findViewById(R.id.txtfooterName);
            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            // this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image);
            
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_rec_child, parent, false);

        //view.setOnClickListener(MainActivityDup.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.txtfooterName.setText(footerList.get(listPosition).PageName);

        Drawable myDrawable = MainActivityDup.getInstance().getResources().getDrawable(R.drawable.rounded_corner_all);
        myDrawable.setColorFilter(new LightingColorFilter(Color.BLACK, Color.parseColor(Constant.themeModel.ThemeColor)));
        holder.txtfooterName.setBackground(myDrawable);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        MainActivityDup.getInstance().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        int devicewidth = (displaymetrics.widthPixels)/ footerList.size();
        holder.txtfooterName.setWidth(devicewidth - 30);
        holder.txtfooterName.setEllipsize(TextUtils.TruncateAt.END);

        holder.txtfooterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MbsDataModel footerModel = footerList.get(listPosition);
                //MainActivityDup.getInstance().mContainer.removeAllViews();
                MainActivityDup.getInstance().mContent.setVisibility(View.GONE);
                MainActivityDup.getInstance().mContainer.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT < 18) {
                    MainActivityDup.getInstance().mContainer.clearView();
                } else {
                    MainActivityDup.getInstance().mContainer.loadUrl("about:blank");
                }
                //  MainActivityDup.getInstance().mContainer.loadDataWithBaseURL("", footerModel.PageContent, "text/html", "UTF-8", "");
                // MainActivityDup.getInstance().LoadWebVew(expandedListText.Footer_Url);

                String page = footerModel.PageName;
                if (page.equals("About Us"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_ABOUT_US + Constant.STOREID);
                else if (page.equals("Blog"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_BLOG + Constant.STOREID);
                else if (page.equals("Departments")) {
                    MainActivityDup.getInstance().isActiveSearchDept = false;
                    MainActivityDup.getInstance().callFilterFragment();
                } else if (page.equals("Notifications")) {
                    MainActivityDup.getInstance().isActiveSearchDept = false;
                    MainActivityDup.getInstance().loadHomeWebPage();
                    MainActivityDup.getInstance().callNotificationDialog(Constant.NOTIFICATION_LIST);
                } else if (page.equals("FAQ"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_FAQ + Constant.STOREID);
                else if (page.equals("legal") || page.equals("Legal"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_LEGAL + Constant.STOREID);
                else if (page.equals("pay_and_refund") || page.equals("Payments & Refunds"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_PAYMENTS_REFUNDS + Constant.STOREID);
                else if (page.equals("Privacy"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_PRIVACY + Constant.STOREID);
                else if (page.equals("Shipping"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_SHIPPING + Constant.STOREID);
                else if (page.equals("Support & Customer Service"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_SUPPORT + Constant.STOREID);
                else if (page.equals("Terms"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_TERMS + Constant.STOREID);
                else if (/*page.equals("WishList") ||*/ page.equals("Help"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_HELP + Constant.STOREID);
                else if (page.equals("Up To 25% Off"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Between 20-30% Off"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Special Offers"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Buy 2 @ 20% Off"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Buy 3 @ 35% Off"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Announcement 1"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("25-45 % Off"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Special Offer 3"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Special Offer 4"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Staff Picks"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Home"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("New Additions"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Staff Picks"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                else if (page.equals("Event Calendar") || page.equals("DisplayEventCalender"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_EVENTS + Constant.STOREID);
                else if (page.equals("Mobile View"))
                    Toast.makeText(MainActivityDup.getInstance(), "Coming Soon.", Toast.LENGTH_SHORT).show();
                    /* else if (page.equals(*//*"Store Hours"*//* "Store/Delivery Hours")||page.equals("DisplayStoreHours")||page.equals(*//*"Store Hours"*//* "Store Hours"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS+ Constant.STOREID);
*/
                else if (page.equalsIgnoreCase(/*"Store Hours"*/ "Store/Delivery Hours")){
//                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS_DELIVERY_HOURS);
                        MainActivityDup.getInstance().loadStoreandDeliveryFragment("");

            }else if (page.equalsIgnoreCase(/*"Store Hours"*/ "Store Hours"))
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_STORE_HOURS + Constant.STOREID);
                else if (page.equals("Facebook")) {

                    FACEBOOK_URL = footerModel.PageTitle;
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(MainActivityDup.getInstance());
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    MainActivityDup.getInstance().startActivity(facebookIntent);

                } else if (page.equals("Linkedln")) {
                    //LINKDIN_PAGE_URL = footerModel.PageTitle;
                    Intent intent = null;
                    try {
                        MainActivityDup.getInstance().getPackageManager().getPackageInfo("com.linkedin.android", 0);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://profile/")); //linkedin://profile/mehultpatel
                    } catch (Exception e) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINKDIN_PAGE_URL/*"https://www.linkedin.com"*/)); //https://www.linkedin.com/in/olankimehuln
                    } finally {
                        MainActivityDup.getInstance().startActivity(intent);
                    }
                } else if (page.equals("Contact Us")) {
                    INSTAGRAM_URL = footerModel.PageTitle;
                        /*Uri uri = Uri.parse("http://instagram.com"); //http://instagram.com/_u/solankimehuln
                        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
                        insta.setPackage("com.instagram.android");

                        if (isIntentAvailable(MainActivityDup.getInstance(), insta)) {
                            MainActivityDup.getInstance().startActivity(insta);
                        } else {
                            MainActivityDup.getInstance().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com"))); //http://instagram.com/solankimehuln
                        }*/
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_PAGE_CONTACT_US + Constant.STOREID);
                } else if (page.equals("Rate App")) {
                    //MainActivityDup.getInstance().LoadWebVew(Constant.URL_TEST_HOME);
                    MainActivityDup.getInstance().launchMarket();
                } else if (page.equals("Share App")) {
                    MainActivityDup.getInstance().shareApp();
                } else if (page.equals("Home test")) {
                    MainActivityDup.getInstance().LoadWebVew(Constant.URL_TEST_HOME);
                }

            }
        });
        /*TextView textViewName = holder.textViewName;
        // TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        //textViewVersion.setText(dataSet.get(listPosition).getVersion());
        imageView.setImageResource(dataSet.get(listPosition).getImage());*/
    }

    public String getFacebookPageURL(Context context) {
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

    @Override
    public int getItemCount() {
        return footerList.size();
    }
}