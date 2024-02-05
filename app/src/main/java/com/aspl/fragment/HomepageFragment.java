package com.aspl.fragment;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspl.Adapter.DepartmentListAdapter;
import com.aspl.Adapter.HomePageListAdapter;
import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.Utils.DialogUtils;
import com.aspl.Utils.ObservableWebView;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbs.SplaceScreen;
import com.aspl.mbsmodel.ActiveStatusModel;
import com.aspl.mbsmodel.BannerModel;
import com.aspl.mbsmodel.DataFrontModel;
import com.aspl.mbsmodel.DataHomePageBlockModel;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.JackDepartmentModel;
import com.aspl.mbsmodel.LoyaltyInfo;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.TemplateModel;
import com.aspl.mbsmodel.UpdateCartQuantity;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskAddtoCart;
import com.aspl.task.TaskAllItems;
import com.aspl.task.TaskBannerItem;
import com.aspl.task.TaskBlockDataFront;
import com.aspl.task.TaskCart;
import com.aspl.task.TaskCartListItem;
import com.aspl.task.TaskCheckUSAePAYStatus;
import com.aspl.task.TaskDepartments;
import com.aspl.task.TaskHomePageBlockData;
import com.aspl.task.TaskHomePageItem;
import com.aspl.task.TaskLoyaltyInfo;
import com.aspl.task.TaskNewAdditionItem;
import com.aspl.task.TaskPramotionItem;
import com.aspl.task.TaskRecentViewedItem;
import com.aspl.task.TaskSpecialOffer;
import com.aspl.task.TaskStaffPicksItem;
import com.aspl.task.TaskTemplate;
import com.aspl.task.TaskUpdatetoCart;
import com.aspl.task.TaskViewAll;
import com.aspl.task.TaskWishListItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.santalu.autoviewpager.AutoViewPager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by new on 07/12/2017.
 */
public class HomepageFragment extends Fragment implements HomePageListAdapter.HomeListItemAdapterEvent,
        TaskHomePageItem.TaskHomeItemEvent,
        TaskNewAdditionItem.TaskNewAdditionEvent,
        TaskStaffPicksItem.TaskStaffPicksEvent,
        TaskTemplate.TaskTemplateEvent,
        TaskBlockDataFront.TaskBlockDataFrontEvent,
        TaskHomePageBlockData.TaskHomePageBlockDataEvent,
        TaskSpecialOffer.TaskSpecialOfferEvent,
        TaskDepartments.TaskDepartmentEvent,
        DepartmentListAdapter.DepartmentListAdapterEvent,
        TaskWishListItem.TaskWishListEvent,
        TaskCartListItem.TaskCartListEvent,
        TaskRecentViewedItem.TaskRecentViewedEvent,
        TaskBannerItem.TaskBannerItemEvent,
        TaskAllItems.TaskAllItemsEvent,
        TaskLoyaltyInfo.TaskLoyaltyInfoEvent,
        TaskPramotionItem.TaskPramotionItemEvent, TaskViewAll.TaskViewAllEvent , TaskAddtoCart.TaskAddToCartEvent ,
        TaskCart.TaskCardEvent , TaskCheckUSAePAYStatus.TaskCheckUSAePAYStatusEvent , TaskUpdatetoCart.TaskUpdateCart{

    private Boolean isBlock2;
    private CardView block2;
    private int count =0;

    public HomepageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().hidebackbutton();
        }

////        ************** Edited by Varun for backbutton *************
//
        else if (Constant.SCREEN_LAYOUT==1){
            MainActivity.getInstance().ll_backbutton.setVisibility(View.VISIBLE);
            MainActivity.getInstance().hidebackbutton();
            MainActivity.getInstance().clearBackStack();
            Constant.check=false;
        }
////        **************** END *****************

        //getActivity().invalidateOptionsMSiteInfoModelenu();
    }

    //    Edited by Varun for homepage addcart

    public static boolean isFromadpter_whenclickedonaddtocart = false;
    boolean iscomeFromUpdate = false;
    boolean isComeFomAddTocartBtn = false;
    int requestedQty = 1;
    String fromWhereClicked = "";
    UpdateCartQuantity addToCarttemp = null;
    HomeItemModel homeItemModel;
    String cartQtyOfItem = "";
    long cartId = 0;

//          END


    LinearLayout linHompage, linNewAddition, linStaffPick, linSpecialOffer, linDepartments, mainscrollList, linPRamotion,llstorename;
    LinearLayout linWishlist, linCart, linRecentviewed, linAllItems, linPRamotionBlock, /*linPRamotionBlock1, linPRamotionBlock2,*/
            lindiscountBlock, linAnnouncemnetBlock, lindiscountblock2 , lin , linTest ,linTest2,linTest3;
    NestedScrollView llhome_main;
    //    private List<Integer> imageIdList;
    public ExpandableListView dept_expList;
    //    TextView txtdepartment;
//    LinearLayout llMain;
    AutoViewPager viewPager;
    /*Data--------------*/
    public static List<HomeItemModel> HomeItemList = new ArrayList<HomeItemModel>();
    public static List<HomeItemModel> NewAdditionList = new ArrayList<HomeItemModel>();
    public static List<HomeItemModel> StaffPicksList = new ArrayList<HomeItemModel>();
    public static List<HomeItemModel> WishItemList = new ArrayList<HomeItemModel>();
    public static List<HomeItemModel> CartItemList = new ArrayList<HomeItemModel>();
    public static List<HomeItemModel> RecViewItemList = new ArrayList<HomeItemModel>();
    public static List<HomeItemModel> SpecialOfferList = new ArrayList<HomeItemModel>();
    public static List<HomeItemModel> AllItemsList = new ArrayList<HomeItemModel>();
    public static List<HomeItemModel> PramotionItemList = new ArrayList<HomeItemModel>();
    public static List<JackDepartmentModel> DepartmentList = new ArrayList<JackDepartmentModel>();
    public static List<BannerModel> BannerItemList = new ArrayList<BannerModel>();
    public static HomepageFragment filterFragment;

    public static HomepageFragment getInstance() {
        return filterFragment;
    }

    public static TemplateModel templateModel;
    List<DataFrontModel> BlockDataFrontList;

//    TESting
    List<DataHomePageBlockModel> dataHomePageBlockModels;
    private String imgUrl = Constant.IMG_BASE + "/WebStoreImages/BlockImages/" + Constant.STOREID + "/";


    //    END
    //    public List<HomeItemModel> promotionblock1List = new ArrayList<>();
//    public List<HomeItemModel> promotionblock2List = new ArrayList<>();
    public boolean isPromotionBlock1 = false, isPromotionBlock2 = false;
    public boolean isAnnouncementBlock1 = false, isAnnouncementBlock2 = false;
    public boolean isSpecialOfferBlock1 = false, isSpecialOfferBlock2 = false;
    public boolean isSpecialOfferBlock3 = false, isSpecialOfferBlock4 = false;
    public boolean isItemBlock1 = false, isItemBlock2 = false;
    public boolean  isnewadditionBlock1 = false, isstaffpickBlock2 = false , isfalse = false;

    String CustomerID = "";
    AutoScrollAdapter autoScrollAdapter;
    ObservableWebView Container;
    TextView BannerText, tvStoreNameText, tvStoreaddress, tvStoreDistance, tvStoreOpen, tvChangeLocation;
    CardView cvBannerContent, cvStorelocation;
    ProgressBar progressBar;
    public static String storeaddress;
//    double store_longi = 0.0;
//    double store_lat = 0.0;

    public LinearLayout   ll_Reward_main;
    public TextView tv_Reward_point_main ,tv_points_main , tv_rebate_point_main , tv_rebate_main;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        filterFragment = this;
        View rootView = inflater.inflate(R.layout.fragment_homepage, container, false);

        viewPager = (AutoViewPager) rootView.findViewById(R.id.view_pager);
        //  viewPager.setOffscreenPageLimit(1);


        if (isAdded()) {
            llhome_main = rootView.findViewById(R.id.llhome_main);
            llhome_main.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
            linHompage = rootView.findViewById(R.id.linHompage);
            linNewAddition = rootView.findViewById(R.id.linNewAddition);
            linStaffPick = rootView.findViewById(R.id.linStaffPick);
            linSpecialOffer = rootView.findViewById(R.id.linSpecialOffer);
            linDepartments = rootView.findViewById(R.id.linDepartments);
            linWishlist = rootView.findViewById(R.id.linWishlist);
            linCart = rootView.findViewById(R.id.linCart);
            linPRamotion = rootView.findViewById(R.id.linPRamotion);
            linAllItems = rootView.findViewById(R.id.linAllItems);
            linRecentviewed = rootView.findViewById(R.id.linRecentviewed);
            linPRamotionBlock = rootView.findViewById(R.id.linPRamotionBlock);
//            linPRamotionBlock1 = rootView.findViewById(R.id.linPRamotionBlock1);
//            linPRamotionBlock2 = rootView.findViewById(R.id.linPRamotionBlock2);
            lindiscountblock2 = rootView.findViewById(R.id.lindiscountblock2);
            lindiscountBlock = rootView.findViewById(R.id.lindiscountBlock);
            linAnnouncemnetBlock = rootView.findViewById(R.id.linAnnouncemnetBlock);
            lin = rootView.findViewById(R.id.lin);

            linTest = rootView.findViewById(R.id.linTest);
            linTest2 = rootView.findViewById(R.id.linTest2);
            linTest3 = rootView.findViewById(R.id.linTest3);

            mainscrollList = rootView.findViewById(R.id.mainscrollList);
            Container = rootView.findViewById(R.id.Container);
            cvBannerContent = rootView.findViewById(R.id.cvBannerContent);
            cvBannerContent.setVisibility(View.GONE);
            BannerText = rootView.findViewById(R.id.BannerText);
            BannerText.setText("Header");
            progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);

            ll_Reward_main = rootView.findViewById(R.id.ll_Reward_main);
            tv_points_main = rootView.findViewById(R.id.tv_points_main);
            tv_rebate_main = rootView.findViewById(R.id.tv_rebate_main);
            tv_rebate_point_main = rootView.findViewById(R.id.tv_rebate_point_main);
            tv_Reward_point_main = rootView.findViewById(R.id.tv_Reward_point_main);

            loadRewardWSData();

            //store location
            if (Constant.co_storeno_value != null && !Constant.co_storeno_value.isEmpty()) {

                if (Constant.contatInfo != null) {
                    cvStorelocation = rootView.findViewById(R.id.cvStorelocation);
                    cvStorelocation.setVisibility(View.VISIBLE);
                    tvStoreNameText = rootView.findViewById(R.id.tvStoreNameText);
                    tvStoreaddress = rootView.findViewById(R.id.tvStoreaddress);
                    llstorename = rootView.findViewById(R.id.llstorename);
                    tvStoreOpen = rootView.findViewById(R.id.tvStoreOpen);
                    tvStoreDistance = rootView.findViewById(R.id.tvStoreDistance);

                    llstorename.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Utils.changeLocationDialog(getActivity());
                        }
                    });

                    if (Constant.contatInfo.getName() != null && !Constant.contatInfo.getName().isEmpty()) {
                        tvStoreNameText.setText(Constant.contatInfo.getName());
                    }

                    String state_Zipvar = "";
                    String add = "";
                    if (Constant.contatInfo.getAddress() != null && !Constant.contatInfo.getAddress().isEmpty()) {
                        add = Constant.contatInfo.getAddress();
//                    add = "S.G.Highway";
                    }

                    if (Constant.contatInfo.getCity() != null && !Constant.contatInfo.getCity().isEmpty()) {
                        add = add + "\n" + Constant.contatInfo.getCity();
//                    add = add + "\n" + "Ahmedabad";
                    }

                    if (Constant.contatInfo.getState() != null && !Constant.contatInfo.getState().isEmpty()) {
                        state_Zipvar = Constant.contatInfo.getState();
//                    add = add + ", " + Constant.contatInfo.getState();
//                    add = add + ", " + "GJ";
                    }

                    if (Constant.contatInfo.getZip() != null && !Constant.contatInfo.getZip().isEmpty()) {
                        state_Zipvar = state_Zipvar + " " + Constant.contatInfo.getZip();
                        add = add + ", " + state_Zipvar;

//                    add = add + " " + Constant.contatInfo.getZip();
//                    add = add + " " + "38001";
                    }

                    tvStoreaddress.setText(add);
                    storeaddress = add;

                    if (Constant.liOnlyStoreHour != null && Constant.liOnlyStoreHour.size() > 0) {
                        String today = Utils.getCurrentDay();
                        boolean isStoreClosedtoday = false;

                        int pos = 0;
//                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

                        for (int i = 0; i < Constant.liOnlyStoreHour.size(); i++) {
                            if (today.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                                pos = i;
                                if (Constant.liOnlyStoreHour.get(i).getClosed()) {
                                    isStoreClosedtoday = true;
                                }
                                break;
                            }
                        }

                        String closeTime = "";

                        if (isStoreClosedtoday) {
                            int j = 1;
                            String tomorrowDay = Utils.getNextDay(j);
                            String nextDayOpenTime = "";

                            for (int i = 0; i < Constant.liOnlyStoreHour.size(); i++) {
                                if (tomorrowDay.equals(Constant.liOnlyStoreHour.get(i).getStoreDay())) {
                                    if (Constant.liOnlyStoreHour.get(i).getClosed()) {
                                        j++;
                                        tomorrowDay = Utils.getNextDay(j);
                                    } else {
                                        nextDayOpenTime = Constant.liOnlyStoreHour.get(i).getOpenTime();
                                    }
                                }
                            }

                            tvStoreOpen.setText("Reopens " + nextDayOpenTime + " " + tomorrowDay);

                        } else {
                            closeTime = Constant.liOnlyStoreHour.get(pos).getCloseTime();
                            tvStoreOpen.setText("Open until " + closeTime);
                        }
//                      String openTime = Constant.liOnlyStoreHour.get(pos).getOpenTime();
//                        tvStoreOpen.setText("Open until " + closeTime);
                    }

                    if (Constant.SCREEN_LAYOUT == 1) {

                        if (MainActivity.getInstance().miles != 0.0) {
                            String milesStr = String.format("%.1f", MainActivity.getInstance().miles);
                            tvStoreDistance.setText(milesStr + " Miles");
                        }

                    } else if (Constant.SCREEN_LAYOUT == 2) {

                        if (MainActivityDup.getInstance().miles != 0.0) {
                            String milesStr = String.format("%.1f", MainActivityDup.getInstance().miles);
                            tvStoreDistance.setText(milesStr + " Miles");
                        }
                    }

//                getLatandLongFromAddress(tvStoreaddress.getText().toString());

                    tvChangeLocation = rootView.findViewById(R.id.tvChangeLocation);
//                tvChangeLocation.setTextColor(Color.RED);
                    tvChangeLocation.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

                    tvChangeLocation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Utils.changeLocationDialog(getActivity());

//                        loadStoreLocationWSdata(getActivity());
                        }
                    });
                }
            }

//                  Setting the Special Banner Message on Home Page Top
            if (Constant.themeModel.SBPageContent != null && !Constant.themeModel.SBPageContent.isEmpty()
                    && Constant.themeModel.SBActiveDisplay) {

                cvBannerContent.setVisibility(View.VISIBLE);

                // Displaying the Text
                BannerText.setText(Constant.themeModel.SBPageContent);

                // Set Typeface
                Typeface typeface = null;
                if (Constant.themeModel.SBfont_weight && Constant.themeModel.SBFontStyle) {
                    typeface = getResources().getFont(R.font.arial_mt_bold_italic);
                } else if (!Constant.themeModel.SBfont_weight && Constant.themeModel.SBFontStyle) {
                    typeface = getResources().getFont(R.font.arial_mt_italic);
                } else if (Constant.themeModel.SBfont_weight && !Constant.themeModel.SBFontStyle) {
                    typeface = getResources().getFont(R.font.arial_mt_bold);
                } else {
                    typeface = getResources().getFont(R.font.arial);
                }
                BannerText.setTypeface(typeface);

                // Underline for textview
                if (Constant.themeModel.SBtext_decoration) {
                    BannerText.setPaintFlags(BannerText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }

                // Set background color with rounded corners
                if (Constant.themeModel.SBBackColor != null && !Constant.themeModel.SBBackColor.isEmpty()) {
                    try {
                        Drawable roundDrawable = getResources().getDrawable(R.drawable.rounded_corner_all);
                        roundDrawable.setColorFilter(Color.parseColor(Constant.themeModel.SBBackColor), PorterDuff.Mode.SRC_ATOP);
                        cvBannerContent.setBackground(roundDrawable);
                    } catch (IllegalArgumentException e) {
                        // Handle the case when ThemeColor is not a valid color
                        e.printStackTrace();
                        // Provide a default color or alternative handling
                        cvBannerContent.setBackgroundColor(0x000000); // Note: Hexadecimal color value
                    }
                }

                try {
                    // Set Text Color
                    if (Constant.themeModel.SBFontColor != null && !Constant.themeModel.SBFontColor.isEmpty()) {
                        try {
                            BannerText.setTextColor(Color.parseColor(Constant.themeModel.SBFontColor));
                        } catch (IllegalArgumentException e) {
                            // Handle the case when SBFontColor is not a valid color
                            e.printStackTrace();
                            // Provide a default color or alternative handling
                            BannerText.setTextColor(0x000000); // Note: Hexadecimal color value
                        }
                    }

                    // Set Font Size
                    if (Constant.themeModel.SBFontSize > 0) {
                        BannerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, Constant.themeModel.SBFontSize);
                    }

                } catch (Resources.NotFoundException e) {
                    // Handle the case when the font resource is not found
                    e.printStackTrace();
                    // Provide a default typeface or alternative handling
                    BannerText.setTypeface(Typeface.DEFAULT);
                }

                // Set Text Alignment
                if (Constant.themeModel.SBText_Align != null && !Constant.themeModel.SBText_Align.isEmpty()) {
                    switch (Constant.themeModel.SBText_Align) {
                        case "center":
                            BannerText.setGravity(Gravity.CENTER);
                            break;
                        case "left":
                            BannerText.setGravity(Gravity.LEFT);
                            break;
                        case "right":
                            BannerText.setGravity(Gravity.RIGHT);
                            break;
                        case "justify":
                            BannerText.setGravity(Gravity.START); // or use Gravity.LEFT
                            BannerText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                            BannerText.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                            break;
                    }
                }
            } else {
                cvBannerContent.setVisibility(View.GONE);
            }
        }

        if (Constant.SCREEN_LAYOUT == 1) {
            CustomerID = MainActivity.getUserId();
            MainActivity.shortingCheckBoxPosition = 0;
            MainActivity.getInstance().isComeFromManageAccount = "home";
            MainActivity.getInstance().getCustomerData();

        } else if (Constant.SCREEN_LAYOUT == 2) {
            CustomerID = MainActivityDup.getUserId();
            MainActivityDup.shortingCheckBoxPosition = 0;
        }
        //MainActivityDup.getInstance().llsortandfilter.setVisibility(View.GONE);
        //Utils.collapse(MainActivityDup.getInstance().llsortandfilter, 500, 0);

        String Urlban = Constant.WS_BASE_URL + Constant.GET_BANNERDETAILS + Constant.STOREID + "/Mobile";
        TaskBannerItem taskBannerItem = new TaskBannerItem(this, getActivity());
        taskBannerItem.execute(Urlban);

//        loadRewardWSData();
        checkUSAePAYactivestatus();


        return rootView;
    }

    /**
     * Call : GET USAePAY ACTIVE STATUS Edited By Varun
     **/

    private void checkUSAePAYactivestatus() {

        Constant.isUSAePAY= false;

        String URL = "";

        URL = Constant.WS_BASE_URL + Constant.GET_USAEPAY_ACTIVE_STATUS + Constant.STOREID + "/" + Constant.ENCODE_TOKEN_ID;

        TaskCheckUSAePAYStatus taskCheckUSAePAYStatus = new TaskCheckUSAePAYStatus(this,getContext());
        taskCheckUSAePAYStatus.execute(URL);

    }

    @Override
    public void onTaskUASePAYStatus(ActiveStatusModel activeStatusModel) {
        Log.e("HOME ", "onTaskUASePAYStatus: "+activeStatusModel.getuSAePay() );
        if (activeStatusModel.getResult().equalsIgnoreCase("Success")){
            if (activeStatusModel.getuSAePay().equalsIgnoreCase("True")){
                Constant.isUSAePAY = true;
            }
        }

    }

//    END

    public void setMiles(double miles) {

        if (Constant.SCREEN_LAYOUT == 1) {

            if (tvStoreDistance != null) {
                if (miles != 0.0) {
                    String milesStr = String.format("%.1f", MainActivity.getInstance().miles);
                    tvStoreDistance.setText(milesStr + " Miles");
                }
            }
        }else if(Constant.SCREEN_LAYOUT == 2){

            if (tvStoreDistance != null) {
                if (miles != 0.0) {
                    String milesStr = String.format("%.1f", MainActivityDup.getInstance().miles);
                    tvStoreDistance.setText(milesStr + " Miles");
                }
            }
        }
    }

//    public void getLatandLongFromAddress(String address) {
//            Geocoder geoCoder = new Geocoder(getActivity());
//            if (address != null && !address.isEmpty()) {
//                try {
//                    List<Address> addressList = geoCoder.getFromLocationName(address, 1);
//                    if (addressList != null && addressList.size() > 0) {
//                        store_lat = addressList.get(0).getLatitude();
//                        store_longi = addressList.get(0).getLongitude();
//
//                        getDistanceOfStoreLoaction(store_lat,store_longi);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } // end catch
//            } // end if
//    } // end c
//
//    private void getDistanceOfStoreLoaction(double store_lat, double store_longi) {
//
//        Location loc1_currentloc = new Location("");
//        loc1_currentloc.setLatitude(MainActivity.getInstance().current_latitude);
//        loc1_currentloc.setLongitude(MainActivity.getInstance().current_longitude);
////        loc1_currentloc.setLatitude(MainActivity.getInstance().gpsTracker.getLatitude());
////        loc1_currentloc.setLongitude(MainActivity.getInstance().gpsTracker.getLongitude());
//
//        Location loc2_storeloc = new Location("");
//        loc2_storeloc.setLatitude(store_lat);
//        loc2_storeloc.setLongitude(store_longi);
//
//        float distanceInMeters = loc1_currentloc.distanceTo(loc2_storeloc);
//        double miles = distanceInMeters * 0.00062137119;
//        Log.e("miles:",""+miles);
//        tvChangeLocation.setText(miles + " Miles");
//        Toast.makeText(getActivity(),"miles:"+miles,Toast.LENGTH_LONG).show();
//    }

    public void setcommonScrollView(List<HomeItemModel> ItemList, LinearLayout datalayout, DataFrontModel dataFrontModel) {

        datalayout.removeAllViews();

        LinearLayout.LayoutParams lpp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        datalayout.setLayoutParams(lpp);
        LayoutInflater inflater1 = getLayoutInflater();
        View v1 = inflater1.inflate(R.layout.raw_home_recycle, null);
        RecyclerView rec_home = v1.findViewById(R.id.rec_home);
        TextView tabTitle = v1.findViewById(R.id.tabTitle);
        TextView ViewAll = v1.findViewById(R.id.ViewAll);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RelativeLayout relativeLayout = v1.findViewById(R.id.name_relative);
        if (dataFrontModel.getBlockDisplaytext()!= null && !dataFrontModel.getBlockDisplaytext().isEmpty()){
            tabTitle.setText(dataFrontModel.getBlockDisplaytext());
        }else {
//            relativeLayout.setVisibility(View.GONE);
            tabTitle.setVisibility(View.GONE);
            ViewAll.setVisibility(View.VISIBLE);
        }
        ViewAll.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("WishList Items")) {
                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().loadWishListFragment();
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().loadWishListFragment();
                    }
                } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Cart Items")) {
                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().loadCardFragment();
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().loadCardFragment();
                    }
                } else {

                    callWSForViewall(dataFrontModel);

//                   / String SessionUD = CustomerID.equalsIgnoreCase("0") ? DeviceInfo.getDeviceId(getActivity()) + "0011" : "0";

//                    if (Constant.SCREEN_LAYOUT == 1) {
//                        // http://192.168.172.211:888//inventory/inventoryapp?customerid=189055&sessionid=0&storeno=707&type=homepageitem
//                        MainActivity.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + CustomerID
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + Type
//                        );
//                    } else if (Constant.SCREEN_LAYOUT == 2) {
//                        MainActivityDup.getInstance().LoadWebVew(Constant.URL + "/inventory/inventoryapp"
//                                + "?customerid=" + CustomerID
//                                + "&sessionid=" + SessionUD
//                                + "&storeno=" + Constant.STOREID
//                                + "&type=" + "" + Type
//                        );
//                    }

                }
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rec_home.setLayoutManager(layoutManager);
        rec_home.setHasFixedSize(true);
        ImageView img_left = v1.findViewById(R.id.img_left);
        ImageView img_right = v1.findViewById(R.id.img_right);
        //Edited by Janvi 13th sep ******************
        if (ItemList.size() > 3) {
            img_left.setVisibility(View.VISIBLE);
            img_right.setVisibility(View.VISIBLE);
        } else {
            img_left.setVisibility(View.INVISIBLE);
            img_right.setVisibility(View.INVISIBLE);
        }
//**************************************

        img_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positionView = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();
                if (positionView > 0) {
                    rec_home.smoothScrollToPosition(positionView - 1);
                } else {
                    rec_home.smoothScrollToPosition(0);
                }

                //  Edited by Janvi 13th sep ******

                int lastVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findLastVisibleItemPosition();

                if (lastVisiblePos == -1) {
                    img_right.setVisibility(View.INVISIBLE);
                } else {
                    img_right.setVisibility(View.VISIBLE);
                }

                int firstVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();

                if (firstVisiblePos == 1) {
                    img_left.setVisibility(View.INVISIBLE);
                } else {
                    img_left.setVisibility(View.VISIBLE);
                }
                //end **********************************

            }
        });
        img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int positionView = ((LinearLayoutManager) rec_home.getLayoutManager()).findLastVisibleItemPosition();
                rec_home.smoothScrollToPosition(positionView + 1);

                //Edited by Janvi 13thSep

                int firstVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();

                if (firstVisiblePos == -1) {
                    img_left.setVisibility(View.INVISIBLE);
                } else {
                    img_left.setVisibility(View.VISIBLE);
                }

                int lastVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findLastVisibleItemPosition();
                if ((ItemList.size() - 1) == (lastVisiblePos)) {
                    img_right.setVisibility(View.INVISIBLE);
                } else {
                    img_right.setVisibility(View.VISIBLE);
                }


                //*****************
            }
        });

        HomePageListAdapter homePageListAdapter = new HomePageListAdapter(getActivity(), this, ItemList);
        rec_home.setAdapter(homePageListAdapter);
        rec_home.setNestedScrollingEnabled(false);
        datalayout.addView(v1);


        rec_home.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (ItemList.size() > 3) {
                    img_left.setVisibility(View.VISIBLE);
                    img_right.setVisibility(View.VISIBLE);
                } else {
                    img_left.setVisibility(View.INVISIBLE);
                    img_right.setVisibility(View.INVISIBLE);
                }
                int lastVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int firstVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                if ((ItemList.size() - 1) == lastVisiblePos) {
                    img_right.setVisibility(View.INVISIBLE);
                } else {
                    img_right.setVisibility(View.VISIBLE);
                }
                if (firstVisiblePos == 0) {
                    img_left.setVisibility(View.INVISIBLE);
                } else {
                    img_left.setVisibility(View.VISIBLE);
                }

                Log.e("Log", "firstVisiblePos=" + firstVisiblePos + "=lastVisiblePos=" + lastVisiblePos);

            }
        });

        //Edited by Janvi 13thSep

        int firstVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();

        if (firstVisiblePos == -1) {
            img_left.setVisibility(View.INVISIBLE);
        } else {
            img_left.setVisibility(View.VISIBLE);
        }
       /* img_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positionView = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();
                if (positionView > 0) {
                    rec_home.smoothScrollToPosition(positionView - 1);
                } else {
                    rec_home.smoothScrollToPosition(0);
                }

            }
        });
        img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positionView = ((LinearLayoutManager)rec_home.getLayoutManager()).findLastVisibleItemPosition();
                rec_home.smoothScrollToPosition(positionView + 1);
            }
        });*/
        /*HomePageListAdapter homePageListAdapter = new HomePageListAdapter(getActivity(), this, ItemList);
        rec_home.setAdapter(homePageListAdapter);
        rec_home.setNestedScrollingEnabled(false);
        datalayout.addView(v1);*/
    }


    public void callWSForViewall(DataFrontModel dataFrontModel) {

        MainActivity.iscomeFromHomeViewall = true;

        String Type = "";
        String blockDisplayedText = "";
        if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Home Page Items")) {
            Type = "homepageitem";
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("New Additions")) {
            Type = "newddition";
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Staff Picks")) {
            Type = "staffpick";
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Special Offers")) {
            Type = "specialoffer";
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("RecentViewed Items")) {
            Type = "RecentViewed";
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Items On Promotion")) {
            Type = "promotion";
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("All Items")) {
            Type = "allItem";
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Promotion 1")) {
            Type = "Promotion 1";
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Promotion 2")) {
            Type = dataFrontModel.getBlockActualtext();
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("SpecialOffer 1")) {
            Type = dataFrontModel.getBlockActualtext();
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("SpecialOffer 2")) {
            Type = dataFrontModel.getBlockActualtext();
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Announcement 1")) {
            Type = dataFrontModel.getBlockActualtext();
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("Announcement 2")) {
            Type = dataFrontModel.getBlockActualtext();
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("SpecialOffer 3")) {
            Type = dataFrontModel.getBlockActualtext();
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        } else if (dataFrontModel.getBlockActualtext().trim().equalsIgnoreCase("SpecialOffer 4")) {
            Type = dataFrontModel.getBlockActualtext();
            blockDisplayedText = dataFrontModel.getBlockDisplaytext();
        }

        String startprice, endprice, blockdisountGroup;

        if (dataFrontModel.getBlockStratprice() != null && !dataFrontModel.getBlockStratprice().isEmpty()) {
            startprice = dataFrontModel.getBlockStratprice();
        } else {
            startprice = "0";
        }

        if (dataFrontModel.getBlockEndprice() != null && !dataFrontModel.getBlockEndprice().isEmpty()) {
            endprice = dataFrontModel.getBlockEndprice();
        } else {
            endprice = "0";
        }

        if (dataFrontModel.getBlockSpecialoffer() != null && !dataFrontModel.getBlockSpecialoffer().isEmpty()) {
            blockdisountGroup = dataFrontModel.getBlockSpecialoffer();
        } else {
            blockdisountGroup = "0";
        }

        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.getInstance().loadViewAllFragment(Type, "0", "0", startprice, endprice, blockdisountGroup, blockDisplayedText, "", "");
        } else if (Constant.SCREEN_LAYOUT == 2) {
            MainActivityDup.getInstance().loadViewAllFragment(Type, "0", "0", startprice, endprice, blockdisountGroup, blockDisplayedText, "", "");
        }
    }

    public DataFrontModel ReturnDataFrontModel(String s) {
        DataFrontModel dataFrontModel = null;
        for (int i = 0; i < BlockDataFrontList.size(); i++) {
            if (BlockDataFrontList.get(i).getBlockActualtext().trim().equalsIgnoreCase(s)) {
                    Log.e(String.valueOf(getContext()), "ReturnDataFrontModel: "+BlockDataFrontList.get(i).getT() );
                    Log.e(String.valueOf(getContext()), "ReturnDataFrontModel1: "+BlockDataFrontList.size() );
//                  Edited by Varun for block and side-by-side
//                    if (BlockDataFrontList.get(i).getT()!=null && BlockDataFrontList.get(i).getT().equalsIgnoreCase("Home")){
//
//                    }else {
//                        dataFrontModel = BlockDataFrontList.get(i);
//                        break;
//                    }
//                    END

                dataFrontModel = BlockDataFrontList.get(i);
                break;
            }
        }

        return dataFrontModel;
    }

    @Override
    public void onGetHomeItemResult(List<HomeItemModel> HomeItemList) {
        View v1 = null;
        if (isAdded()) {
            if (HomeItemList != null && HomeItemList.size() > 0) {
                this.HomeItemList = HomeItemList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("Home Page Items");
                setcommonScrollView(HomeItemList, linHompage, dataFrontModel);
            }
        }

    }

    @Override
    public void onGetNewAdditionResult(List<HomeItemModel> NewAdditionList) {
        if (isAdded()) {
            if (NewAdditionList != null && NewAdditionList.size() > 0) {
                String type = "newddition";
                this.NewAdditionList = NewAdditionList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("New Additions");
//                setcommonScrollView(NewAdditionList, linNewAddition, dataFrontModel);
                if (dataFrontModel.getHomeDisplayFormat().equalsIgnoreCase("Block")){
                    CheckBlocks(NewAdditionList, type);
                }else {
                    setcommonScrollView(NewAdditionList, linNewAddition, dataFrontModel);
                }
            }
        }
    }

    @Override
    public void onGetStaffPicksResult(List<HomeItemModel> StaffPicksList) {
        if (isAdded()) {
            if (StaffPicksList != null && StaffPicksList.size() > 0) {
                String type = "staffpick";
                this.StaffPicksList = StaffPicksList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("Staff Picks");
                if (dataFrontModel.getHomeDisplayFormat().equalsIgnoreCase("Block")){
                    CheckBlocks(StaffPicksList, type);
                }else {
                    setcommonScrollView(StaffPicksList, linStaffPick, dataFrontModel);
                }
            }
        }
    }



    @Override
    public void onGetWishListResult(List<HomeItemModel> WishItemList) {
        if (isAdded()) {
            if (WishItemList != null && WishItemList.size() > 0 && WishItemList.get(0).getCartID() != 0) {
                this.WishItemList = WishItemList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("WishList Items");
                setcommonScrollView(WishItemList, linWishlist, dataFrontModel);
            }
        }
    }

    @Override
    public void onGetCartListResult(List<HomeItemModel> CartItemList) {
        if (isAdded()) {
            if (CartItemList != null && CartItemList.size() > 0 && CartItemList.get(0).getCartID() != 0) {
                this.CartItemList = CartItemList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("Cart Items");
                setcommonScrollView(CartItemList, linCart, dataFrontModel);
            }
        }
    }

    @Override
    public void onGetAllItemsResult(List<HomeItemModel> allItemsList) {
        if (isAdded()) {
            if (allItemsList != null && allItemsList.size() > 0) {
                this.AllItemsList = allItemsList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("All Items");
                setcommonScrollView(AllItemsList, linAllItems, dataFrontModel);
            }
        }
    }

    @Override
    public void onGetPramotionItemResult(List<HomeItemModel> pramotionItemList) {

        Constant.ItemOnPromotionList = pramotionItemList;
        if (isAdded()) {
            if (pramotionItemList != null && pramotionItemList.size() > 0) {
                String type= "promotion";
                this.PramotionItemList = pramotionItemList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("Items On Promotion");
                if (dataFrontModel.getHomeDisplayFormat().equalsIgnoreCase("Block")){
                    CheckBlocks(PramotionItemList, type);
                }else {
                    setcommonScrollView(PramotionItemList, linPRamotion, dataFrontModel);
                }
            }
        }
    }

    @Override
    public void onGetRecentViewedResult(List<HomeItemModel> RecViewItemList) {
        if (isAdded()) {
            if (RecViewItemList != null && RecViewItemList.size() > 0) {
                this.RecViewItemList = RecViewItemList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("RecentViewed Items");
                setcommonScrollView(RecViewItemList, linRecentviewed, dataFrontModel);
            }
        }
    }

    @Override
    public void onTemplateResponse(TemplateModel templateModel) {
        this.templateModel = templateModel;
//        if (Constant.BLOCKDATAFRONTLIST != null)
            onGetBlockDataFrontResult(Constant.BLOCKDATAFRONTLIST);
             onGetHomePageBlockDataResult(Constant.DATAHOMEPAGEBLOCK);

        //String Url1 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBLOCK_DATAFORFRONT + Constant.STOREID;
        /*TaskBlockDataFront taskBlockDataFront = new TaskBlockDataFront(this);
        taskBlockDataFront.execute(Url1);*/
    }
//  Testing
    @Override
    public void onGetHomePageBlockDataResult(List<DataHomePageBlockModel> BlockDataFrontList) {
        this.dataHomePageBlockModels = BlockDataFrontList;

//        callblocks();

    }
//    END
    @Override
    public void onGetBlockDataFrontResult(List<DataFrontModel> BlockDataFrontList) {
        this.BlockDataFrontList = BlockDataFrontList;

//        Log.e(String.valueOf(getContext()), "onGetBlockDataFrontResult: "+ReturnDataFrontModel("Items On Promotion").getHomeDisplayFormat() );
//        Log.e(String.valueOf(getContext()), "onGetBlockDataFrontResult: "+ReturnDataFrontModel("Items On Promotion").getT());

        callblocks();

        if (templateModel.getHomePageData()) {
            if (Constant.PREVIOUS_STOREID.equals(Constant.STOREID) && HomeItemList.size() > 0) {
//            if (HomeItemList.size() > 0) {
                onGetHomeItemResult(HomeItemList);
            } else {
//                String Url1 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBY_HOMEPAGEDATA + Constant.STOREID;
                String Url1 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBY_HOMEPAGEDATA_NEW + Constant.STOREID;
                TaskHomePageItem taskHomePageItem = new TaskHomePageItem(this);
                taskHomePageItem.execute(Url1);
            }
        }

        if (templateModel.getNewAddition()) {
            if (Constant.PREVIOUS_STOREID.equals(Constant.STOREID) && NewAdditionList.size() > 0) {
//            if (NewAdditionList.size() > 0) {
                onGetNewAdditionResult(NewAdditionList);
            } else {
//                String Url2 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBY_NEWADDITION + Constant.STOREID;
                String Url2 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBY_NEWADDITION_NEW + Constant.STOREID;
                TaskNewAdditionItem taskNewAdditionItem = new TaskNewAdditionItem(this);
                taskNewAdditionItem.execute(Url2);
            }
        }

        if (templateModel.getStaffPick()) {
            if (Constant.PREVIOUS_STOREID.equals(Constant.STOREID) && StaffPicksList.size() > 0) {
//            if (StaffPicksList.size() > 0) {
                onGetStaffPicksResult(StaffPicksList);
            } else {
//                String Url3 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBY_STAFFPICKS + Constant.STOREID;
                String Url3 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBY_STAFFPICKS_NEW + Constant.STOREID;
                TaskStaffPicksItem taskStaffPicksItem = new TaskStaffPicksItem(this);
                taskStaffPicksItem.execute(Url3);
            }
        }

        if (templateModel.getSpecialOffer()) {
            if (Constant.PREVIOUS_STOREID.equals(Constant.STOREID) && SpecialOfferList.size() > 0) {
//            if (SpecialOfferList.size() > 0) {
                onGetSpecialOfferResult(SpecialOfferList);
            } else {
//                String Url4 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBY_SPECIALOFFER + Constant.STOREID;
                String Url4 = Constant.WS_BASE_URL + Constant.GET_INVENTORYBY_SPECIALOFFER_V1 + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
                TaskSpecialOffer taskSpecialOffer = new TaskSpecialOffer(this);
                taskSpecialOffer.execute(Url4);
            }
        }


        if (templateModel.getShopByDepartment()) {
                if (Constant.PREVIOUS_STOREID.equals(Constant.STOREID) && DepartmentList.size() > 0) {
//                if (DepartmentList.size() > 0) {
                    onGetDepartmentResult(DepartmentList);
//                    Log.e("homeItemList","homeItemList-has value");
                } else {
//                    Log.e("homeItemList","homeItemList-no value");
                    String Url5 = Constant.WS_BASE_URL + Constant.GET_DEPARTMETS + Constant.STOREID;
                    TaskDepartments taskDepartments = new TaskDepartments(this);
                    taskDepartments.execute(Url5);
                }

        }

        if (templateModel.getAllItem()) {
//            if (AllItemsList.size() > 0) {
            if (Constant.PREVIOUS_STOREID.equals(Constant.STOREID) && AllItemsList.size() > 0) {
                onGetAllItemsResult(AllItemsList);
            } else {
//                String Url4 = Constant.WS_BASE_URL + Constant.GET_ALLINVENTORY + Constant.STOREID;
                String Url4 = Constant.WS_BASE_URL + Constant.GET_ALLINVENTORY_V1 + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
                TaskAllItems taskAllItems = new TaskAllItems(this);
                taskAllItems.execute(Url4);
            }
        }
        if (templateModel.getItemsOnPromotion()) {

//            if (PramotionItemList.size() > 0) {
                if (Constant.PREVIOUS_STOREID.equals(Constant.STOREID) && PramotionItemList.size() > 0) {
                onGetPramotionItemResult(PramotionItemList);
            } else {
//                String Url4 = Constant.WS_BASE_URL + Constant.GET_INVENTORY_PRAMOTION + Constant.STOREID ;
                String Url4 = Constant.WS_BASE_URL + Constant.GET_INVENTORY_PRAMOTION_AUTH_NEW + Constant.STOREID + "/" + Constant.ENCODE_TOKEN_ID;
                TaskPramotionItem taskPramotionItem = new TaskPramotionItem(this);
                taskPramotionItem.execute(Url4);
            }
        }
        if (!CustomerID.trim().equalsIgnoreCase("0") && templateModel.getWishListItems()) {
           /* if (WishItemList.size() > 0) {
                onGetWishListResult(WishItemList);
            } else {*/
//            String Url6 = Constant.WS_BASE_URL + Constant.GET_CUSTOMERCARTDATA + CustomerID + "/Wishlist/" + Constant.STOREID;
            String Url6 = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + CustomerID + "/Wishlist/" + Constant.STOREID +Constant.ENCODE_TOKEN_ID;
            TaskWishListItem taskWishListItem = new TaskWishListItem(this);
            taskWishListItem.execute(Url6);
            // }

        }

        if (!CustomerID.trim().equalsIgnoreCase("0") && templateModel.getCartItems()) {
            /*if (CartItemList.size() > 0) {
                onGetCartListResult(CartItemList);
            } else {*/
//            String Url7 = Constant.WS_BASE_URL + Constant.GET_CUSTOMERCARTDATA + CustomerID + "/Cart/" + Constant.STOREID;
            String Url7 = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + CustomerID + "/Cart/" + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
            TaskCartListItem taskCartListItem = new TaskCartListItem(this);
            taskCartListItem.execute(Url7);
            // }
        }
        if (!CustomerID.trim().equalsIgnoreCase("0") && templateModel.getRecentViewedItems()) {
           /* if (RecViewItemList.size() > 0) {
                onGetRecentViewedResult(RecViewItemList);
            } else {*/
//            String Url8 = Constant.WS_BASE_URL + Constant.GET_RECENTLYVIEWEDITEMS + Constant.STOREID + "/" + CustomerID;
            String Url8 = Constant.WS_BASE_URL + Constant.GET_RECENTLYVIEWEDITEMS_V1 + Constant.STOREID + "/" + CustomerID +Constant.ENCODE_TOKEN_ID;
            TaskRecentViewedItem taskRecentViewedItem = new TaskRecentViewedItem(this);
            taskRecentViewedItem.execute(Url8);
            //}
        }


//        if (templateModel.getPromotion1() || templateModel.getPromotion2()) {
//            DataFrontModel dataPramotion1 = ReturnDataFrontModel("Promotion 1");
//            DataFrontModel dataPramotion2 = ReturnDataFrontModel("Promotion 2");
//            if (isAdded()) {
//                onAddBlock(linPRamotionBlock, dataPramotion1, dataPramotion2, templateModel.getPromotion1(), templateModel.getPromotion2());
//            }
//        }
//
//        if (templateModel.getSpecialOffer1() || templateModel.getSpecialOffer2()) {
//            DataFrontModel dataPramotion1 = ReturnDataFrontModel("SpecialOffer 1");
//            DataFrontModel dataPramotion2 = ReturnDataFrontModel("SpecialOffer 2");
//            if (isAdded()) {
//                onAddBlock(lindiscountBlock, dataPramotion1, dataPramotion2, templateModel.getSpecialOffer1(), templateModel.getSpecialOffer2());
//            }
//        }
//        if (templateModel.getAnnouncement1() || templateModel.getAnnouncement2()) {
//            DataFrontModel dataPramotion1 = ReturnDataFrontModel("Announcement 1");
//            DataFrontModel dataPramotion2 = ReturnDataFrontModel("Announcement 2");
//            if (isAdded()) {
//                onAddBlock(linAnnouncemnetBlock, dataPramotion1, dataPramotion2, templateModel.getAnnouncement1(), templateModel.getAnnouncement2());
//            }
//        }
//        if (templateModel.getSpecialOffer3() || templateModel.getSpecialOffer4()) {
//            DataFrontModel dataPramotion1 = ReturnDataFrontModel("SpecialOffer 3");
//            DataFrontModel dataPramotion2 = ReturnDataFrontModel("SpecialOffer 4");
//            if (isAdded()) {
//                onAddBlock(lindiscountblock2, dataPramotion1, dataPramotion2, templateModel.getSpecialOffer3(), templateModel.getSpecialOffer4());
//            }
//        }


        if (templateModel.getHomeContent()) {
            DataFrontModel webdatamodel = ReturnDataFrontModel("Home Content");
            Container.clearView();
//        Container.clearHistory();
//        if (isAdded()) {
//            CookieSyncManager.createInstance(getActivity());
//            CookieManager cookieManager = CookieManager.getInstance();
//            cookieManager.removeAllCookie();
//        }
            Container.setVisibility(View.VISIBLE);
            WebSettings settings = Container.getSettings();
            settings.setJavaScriptEnabled(true);
            Container.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            Container.setWebChromeClient(new WebChromeClient());
            Container.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            if (Build.VERSION.SDK_INT >= 19) {
                Container.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            } else {
                Container.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            if (webdatamodel != null && webdatamodel.getBlockDescription() != null) {
//                ?Edited by Varun for Html Text not Seen in live store 57
//                Container.loadData(webdatamodel.getBlockDescription(), null, null);
                Container.loadDataWithBaseURL("file:///android_asset/", webdatamodel.getBlockDescription(), "text/html", "utf-8", null);
                Container.getSettings().setAllowFileAccess(true);
//                END
            }
        }

    }//

    private void callblocks() {

//        Edited by Varun for block and side-by-side

        if (templateModel.getNewAddition()) {

            String s1 = templateModel.getNewAddition().toString();
            Log.e("New Additions 1","New Additions="+s1);
            String type = "New Additions";

//            callWSForBlockItemList(type);

        }
        if (templateModel.getStaffPick()) {

            String s1 = templateModel.getStaffPick().toString();
            Log.e("Staff Picks 1","Staff Picks="+s1);
            String type = "Staff Picks";

//            callWSForBlockItemList(type);

        }
        if (templateModel.getItemsOnPromotion()){
            String s1 = templateModel.getItemsOnPromotion().toString();
            Log.e("Item on promotion", "callblocks: "+s1 );
            String type = "Items On Promotion";

//            callWSForBlockItemList(type);
        }

//         END

        if (templateModel.getPromotion1()) {

            String s1 = templateModel.getPromotion1().toString();
            Log.e("Promotion 1","Promotion 1="+s1);
            String type = "Promotion 1";

            callWSForBlockItemList(type);

        } else {

            if (templateModel.getPromotion2()) {

                String type = "Promotion 2";

                callWSForBlockItemList(type);

//                DataFrontModel dataPramotion2 = ReturnDataFrontModel("Promotion 2");
//
//
//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                        + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataPramotion2.getBlockStratprice() + "/" + dataPramotion2.getBlockEndprice() + "/" + "0";
//                TaskViewAll taskPromotionBlock = new TaskViewAll(this,getActivity(),type);
//                taskPromotionBlock.execute(Url);
            }

        }


        if (templateModel.getSpecialOffer1()) {
            String type = "SpecialOffer 1";
            callWSForBlockItemList(type);

//            DataFrontModel dataSpecialOffer1 = ReturnDataFrontModel("SpecialOffer 1");
//
//            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                    + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataSpecialOffer1.getBlockStratprice() + "/" + dataSpecialOffer1.getBlockEndprice() + "/" + "0";
//            TaskViewAll taskSpecialBlock = new TaskViewAll(this,getActivity(),type);
//            taskSpecialBlock.execute(Url);

        } else {

            if (templateModel.getSpecialOffer2()) {

                String type = "SpecialOffer 2";
                callWSForBlockItemList(type);

//                DataFrontModel dataSpecialOffer2 = ReturnDataFrontModel("SpecialOffer 2");
//
//
//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                        + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataSpecialOffer2.getBlockStratprice() + "/" + dataSpecialOffer2.getBlockEndprice() + "/" + "0";
//                TaskViewAll taskSpecialBlock = new TaskViewAll(this,getActivity(),type);
//                taskSpecialBlock.execute(Url);
            }

        }


        if (templateModel.getAnnouncement1()) {
            String type = "Announcement 1";
            callWSForBlockItemList(type);

//            DataFrontModel dataAnnouncement1 = ReturnDataFrontModel("Announcement 1");
//
//            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                    + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataAnnouncement1.getBlockStratprice() + "/" + dataAnnouncement1.getBlockEndprice() + "/" + "0";
//            TaskViewAll taskAnnouncementBlock = new TaskViewAll(this,getActivity(),type);
//            taskAnnouncementBlock.execute(Url);

        } else {

            if (templateModel.getAnnouncement2()) {

                String type = "Announcement 2";
                callWSForBlockItemList(type);

//                DataFrontModel dataAnnouncement2 = ReturnDataFrontModel("Announcement 2");
//
//
//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                        + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataAnnouncement2.getBlockStratprice() + "/" + dataAnnouncement2.getBlockEndprice() + "/" + "0";
//                TaskViewAll taskAnnouncementBlock = new TaskViewAll(this,getActivity(),type);
//                taskAnnouncementBlock.execute(Url);
            }

        }

        if (templateModel.getSpecialOffer3()) {

            String type = "SpecialOffer 3";
            callWSForBlockItemList(type);

//            DataFrontModel dataSpecialOffer3 = ReturnDataFrontModel("SpecialOffer 3");
//
//            String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                    + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataSpecialOffer3.getBlockStratprice() + "/" + dataSpecialOffer3.getBlockEndprice() + "/" + "0";
//            TaskViewAll taskSpecial3Block = new TaskViewAll(this,getActivity(),type);
//            taskSpecial3Block.execute(Url);

        } else {

            if (templateModel.getSpecialOffer4()) {

                String type = "SpecialOffer 4";
                callWSForBlockItemList(type);

//                DataFrontModel dataSpecialOffer4 = ReturnDataFrontModel("SpecialOffer 4");
//
//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                        + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataSpecialOffer4.getBlockStratprice() + "/" + dataSpecialOffer4.getBlockEndprice() + "/" + "0";
//                TaskViewAll taskSpecial4Block = new TaskViewAll(this,getActivity(),type);
//                taskSpecial4Block.execute(Url);
            }

        }

//        if(templateModel.getStaffPick()){
//            String type = "StaffPick";
//            callWSForBlockItemList(type);
//        }


    }

    public void callWSForBlockItemList(String type) {

        DataFrontModel dataPramotion1 = ReturnDataFrontModel(type);

        Log.e("Promotion 11","Promotion 11="+type);

        String startprice = " ", endprice = " ", blockdisountGroup = "";

        if (dataPramotion1.getBlockStratprice() != "" && !dataPramotion1.getBlockStratprice().isEmpty()) {
            startprice = dataPramotion1.getBlockStratprice();
        } else {
            startprice = "0";
        }

        if (dataPramotion1.getBlockEndprice() != "" && !dataPramotion1.getBlockEndprice().isEmpty()) {
            endprice = dataPramotion1.getBlockEndprice();
        } else {
            endprice = "0";
        }

        if (dataPramotion1.getBlockSpecialoffer() != "" && !dataPramotion1.getBlockSpecialoffer().isEmpty()) {
            blockdisountGroup = dataPramotion1.getBlockSpecialoffer();
        } else {
            blockdisountGroup = "0";
        }

//       Edited by Varun for block and side-by-side

        if (type.equalsIgnoreCase("Staff Picks")){
            type="staffpick";
        }else if (type.equalsIgnoreCase("New Additions")){
            type="newddition";
        }else if (type.equalsIgnoreCase("Items On Promotion")){
            type="promotion";
        }

//        END

//        String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
        String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORY_LIST_BY_PAGING_NEW + "/" + Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
                + "/" + blockdisountGroup + "/" + "0;0" + "/" + "1" + "/" + "12" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + startprice + "/" + endprice + "/" + "0";

        Log.e("Url", "callWSForBlockItemList:12 "+Url );

        TaskViewAll taskPromotionBlock = new TaskViewAll(this, getActivity(), type);
        taskPromotionBlock.execute(Url);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onAddBlock(LinearLayout linPRamotionBlock, DataFrontModel dataPramotion1, DataFrontModel dataPramotion2, Boolean isBlock1, Boolean isBlock2) {
        LayoutInflater inflater1 = getLayoutInflater();
        View v1 = inflater1.inflate(R.layout.raw_block, null);
        TextView textoffername1 = v1.findViewById(R.id.textoffername1);
        TextView txtofferdesc1 = v1.findViewById(R.id.txtofferdesc1);

        TextView textoffername2 = v1.findViewById(R.id.textoffername2);
        TextView txtofferdesc2 = v1.findViewById(R.id.txtofferdesc2);
        ImageView img1 = v1.findViewById(R.id.img1);
        ImageView img2 = v1.findViewById(R.id.img2);

        TextView txtshop1 = v1.findViewById(R.id.txtshop1);
        TextView txtshop2 = v1.findViewById(R.id.txtshop2);

//        compare to web for text in block

        if (dataPramotion1.getBlockDisplaytext()!= null && !dataPramotion1.getBlockDisplaytext().isEmpty()){
            textoffername1.setText(dataPramotion1.getBlockDisplaytext());
            textoffername1.setVisibility(View.VISIBLE);
        }else{
            textoffername1.setVisibility(View.GONE);
        }
        if (dataPramotion1.getBlockDescription()!= null && !dataPramotion1.getBlockDescription().isEmpty()){
            txtofferdesc1.setText(dataPramotion1.getBlockDescription());
            txtofferdesc1.setVisibility(View.VISIBLE);
        }else{
            txtofferdesc1.setVisibility(View.GONE);
        }

        if (dataPramotion2.getBlockDescription()!= null && !dataPramotion2.getBlockDescription().isEmpty()){
            txtofferdesc2.setText(dataPramotion2.getBlockDescription());
            txtofferdesc2.setVisibility(View.VISIBLE);
        }else {
            txtofferdesc2.setVisibility(View.GONE);
        }

        if (dataPramotion2.getBlockDisplaytext()!=null && !dataPramotion2.getBlockDisplaytext().isEmpty()){
            textoffername2.setVisibility(View.VISIBLE);
            textoffername2.setText(dataPramotion2.getBlockDisplaytext());
        }else{
            textoffername2.setVisibility(View.GONE);
        }
//          END

//        textoffername1.setText(dataPramotion1.getBlockDisplaytext());
//        txtofferdesc1.setText(dataPramotion1.getBlockDescription());
//
//        textoffername2.setText(dataPramotion2.getBlockDisplaytext());
//        txtofferdesc2.setText(dataPramotion2.getBlockDescription());

        CardView cvBlock1 = v1.findViewById(R.id.cvBlock1);
        CardView cvBlock2 = v1.findViewById(R.id.cvBlock2);
        LinearLayout block1 = v1.findViewById(R.id.block1);
        LinearLayout block2 = v1.findViewById(R.id.block2);

            if (dataPramotion1.getImage()!=""){
                Glide.with(getActivity()).load(imgUrl + dataPramotion1.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(img1);



//                 For text size
//                textoffername1.setTextSize(Float.parseFloat(dataPramotion1.getFontSize()));
//                txtofferdesc1.setTextSize(Float.parseFloat(dataPramotion1.getFontSize()));
//                txtshop1.setTextSize(Float.parseFloat(dataPramotion1.getFontSize()));
//                 END


//                textoffername1.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

            }else{
                cvBlock1.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
                cvBlock2.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
            }
            if (dataPramotion2.getImage()!=""){
                Glide.with(getActivity()).load(imgUrl + dataPramotion2.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(img2);



//                For text size

//                textoffername2.setTextSize(Float.parseFloat(dataPramotion2.getFontSize()));
//                txtofferdesc2.setTextSize(Float.parseFloat(dataPramotion2.getFontSize()));
//                txtshop2.setTextSize(Float.parseFloat(dataPramotion2.getFontSize()));

            }else{
                cvBlock1.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
                cvBlock2.setBackgroundColor(Color.parseColor(Constant.themeModel.ThemeColor));
            }

//            For Style given by setup for block 1

        if (dataPramotion1.getFontStyle()!=null &&!dataPramotion1.getFontStyle().isEmpty()){
            if (dataPramotion1.getFontStyle().equalsIgnoreCase("Arial")) {
                Typeface typeface = getResources().getFont(R.font.arial);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Verdana")){
                Typeface typeface = getResources().getFont(R.font.verdana);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Calibri")){
                Typeface typeface = getResources().getFont(R.font.calibri);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Open Sans")){
                Typeface typeface = getResources().getFont(R.font.open_sans);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Cambria")){
                Typeface typeface = getResources().getFont(R.font.cambria);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Eras Light ITC")){
                Typeface typeface = getResources().getFont(R.font.eras_light_itc);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Tahoma")){
                Typeface typeface = getResources().getFont(R.font.tahoma);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Garamond")){
                Typeface typeface = getResources().getFont(R.font.garamond);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Georgia")){
                Typeface typeface = getResources().getFont(R.font.georgia);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Courier New")){
                Typeface typeface = getResources().getFont(R.font.courier_new);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Brush Script MT")){
                Typeface typeface = getResources().getFont(R.font.brush_script_mt);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }else if (dataPramotion1.getFontStyle().equalsIgnoreCase("Times New Roman")){
                Typeface typeface = getResources().getFont(R.font.times_new_roman);
                textoffername1.setTypeface(typeface);
                txtofferdesc1.setTypeface(typeface);
                txtshop1.setTypeface(typeface);
            }
        }

//         END


//        For Style given by setup for block 2

        if (dataPramotion2.getFontStyle()!=null &&!dataPramotion2.getFontStyle().isEmpty()){
            if (dataPramotion2.getFontStyle().equalsIgnoreCase("Arial")) {
                Typeface typeface = getResources().getFont(R.font.times_new_roman);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Verdana")){
                Typeface typeface = getResources().getFont(R.font.verdana);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Calibri")){
                Typeface typeface = getResources().getFont(R.font.calibri);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Open Sans")){
                Typeface typeface = getResources().getFont(R.font.open_sans);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Cambria")){
                Typeface typeface = getResources().getFont(R.font.cambria);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Eras Light ITC")){
                Typeface typeface = getResources().getFont(R.font.eras_light_itc);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Tahoma")){
                Typeface typeface = getResources().getFont(R.font.tahoma);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Garamond")){
                Typeface typeface = getResources().getFont(R.font.garamond);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Georgia")){
                Typeface typeface = getResources().getFont(R.font.georgia);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Courier New")){
                Typeface typeface = getResources().getFont(R.font.courier_new);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Brush Script MT")){
                Typeface typeface = getResources().getFont(R.font.brush_script_mt);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }else if (dataPramotion2.getFontStyle().equalsIgnoreCase("Times New Roman")){
                Typeface typeface = getResources().getFont(R.font.times_new_roman);
                textoffername2.setTypeface(typeface);
                txtofferdesc2.setTypeface(typeface);
                txtshop2.setTypeface(typeface);
            }
        }

//        END

//        For Block 1 gravity in home screen view

        if (dataPramotion1.getBlockActualtext().equalsIgnoreCase("New Additions") || dataPramotion2.getBlockActualtext().equalsIgnoreCase("New Additions")
            || dataPramotion1.getBlockActualtext().equalsIgnoreCase("Items On Promotion") || dataPramotion2.getBlockActualtext().equalsIgnoreCase("Items On Promotion")
            || dataPramotion1.getBlockActualtext().equalsIgnoreCase("Staff Picks") || dataPramotion2.getBlockActualtext().equalsIgnoreCase("Staff Picks")
            || dataPramotion1.getBlockActualtext().equalsIgnoreCase("SpecialOffer 1") || dataPramotion2.getBlockActualtext().equalsIgnoreCase("SpecialOffer 1")
            || dataPramotion1.getBlockActualtext().equalsIgnoreCase("SpecialOffer 2") || dataPramotion2.getBlockActualtext().equalsIgnoreCase("SpecialOffer 2")) {

            if (dataPramotion1.getBlockImgText().equalsIgnoreCase("MR")) {
                textoffername1.setGravity(Gravity.CENTER | Gravity.RIGHT);
                txtofferdesc1.setGravity(Gravity.CENTER | Gravity.RIGHT);
                txtshop1.setGravity(Gravity.CENTER | Gravity.RIGHT);
                block1.setGravity(Gravity.CENTER | Gravity.RIGHT);
            } else if (dataPramotion1.getBlockImgText().equalsIgnoreCase("TR")) {
                textoffername1.setGravity(Gravity.TOP | Gravity.RIGHT);
                txtofferdesc1.setGravity(Gravity.TOP | Gravity.RIGHT);
                txtshop1.setGravity(Gravity.TOP | Gravity.RIGHT);
                block1.setGravity(Gravity.TOP | Gravity.RIGHT);
            } else if (dataPramotion1.getBlockImgText().equalsIgnoreCase("BR")) {
                textoffername1.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                txtofferdesc1.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                txtshop1.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                block1.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
            } else if (dataPramotion1.getBlockImgText().equalsIgnoreCase("ML")) {
                textoffername1.setGravity(Gravity.CENTER | Gravity.LEFT);
                txtofferdesc1.setGravity(Gravity.CENTER | Gravity.LEFT);
                txtshop1.setGravity(Gravity.CENTER | Gravity.LEFT);
                block1.setGravity(Gravity.CENTER | Gravity.LEFT);
            } else if (dataPramotion1.getBlockImgText().equalsIgnoreCase("TL")) {
                textoffername1.setGravity(Gravity.TOP | Gravity.LEFT);
                txtofferdesc1.setGravity(Gravity.TOP | Gravity.LEFT);
                txtshop1.setGravity(Gravity.TOP | Gravity.LEFT);
                block1.setGravity(Gravity.TOP | Gravity.LEFT);
            } else if (dataPramotion1.getBlockImgText().equalsIgnoreCase("BL")) {
                textoffername1.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                txtofferdesc1.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                txtshop1.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                block1.setGravity(Gravity.BOTTOM | Gravity.LEFT);
            } else if (dataPramotion1.getBlockImgText().equalsIgnoreCase("c")) {
                textoffername1.setGravity(Gravity.CENTER);
                txtofferdesc1.setGravity(Gravity.CENTER);
                txtshop1.setGravity(Gravity.CENTER);
                block1.setGravity(Gravity.CENTER);
            }

//        END

//                 For Block 2 gravity in home screen view

            if (dataPramotion2.getBlockImgText().equalsIgnoreCase("MR")) {
                textoffername2.setGravity(Gravity.CENTER | Gravity.RIGHT);
                txtofferdesc2.setGravity(Gravity.CENTER | Gravity.RIGHT);
                txtshop2.setGravity(Gravity.CENTER | Gravity.RIGHT);
                block2.setGravity(Gravity.CENTER | Gravity.RIGHT);
            } else if (dataPramotion2.getBlockImgText().equalsIgnoreCase("TR")) {
                textoffername2.setGravity(Gravity.TOP | Gravity.RIGHT);
                txtofferdesc2.setGravity(Gravity.TOP | Gravity.RIGHT);
                txtshop2.setGravity(Gravity.TOP | Gravity.RIGHT);
                block2.setGravity(Gravity.TOP | Gravity.RIGHT);
            } else if (dataPramotion2.getBlockImgText().equalsIgnoreCase("BR")) {
                textoffername2.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                txtofferdesc2.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                txtshop2.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                block2.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
            } else if (dataPramotion2.getBlockImgText().equalsIgnoreCase("ML")) {
                textoffername2.setGravity(Gravity.CENTER | Gravity.LEFT);
                txtofferdesc2.setGravity(Gravity.CENTER | Gravity.LEFT);
                txtshop2.setGravity(Gravity.CENTER | Gravity.LEFT);
                block2.setGravity(Gravity.CENTER | Gravity.LEFT);
            } else if (dataPramotion2.getBlockImgText().equalsIgnoreCase("TL")) {
                textoffername2.setGravity(Gravity.TOP | Gravity.LEFT);
                txtofferdesc2.setGravity(Gravity.TOP | Gravity.LEFT);
                txtshop2.setGravity(Gravity.TOP | Gravity.LEFT);
                block2.setGravity(Gravity.TOP | Gravity.LEFT);
            } else if (dataPramotion2.getBlockImgText().equalsIgnoreCase("BL")) {
                textoffername2.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                txtofferdesc2.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                txtshop2.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                block2.setGravity(Gravity.BOTTOM | Gravity.LEFT);
            } else if (dataPramotion2.getBlockImgText().equalsIgnoreCase("c")) {
                textoffername2.setGravity(Gravity.CENTER);
                txtofferdesc2.setGravity(Gravity.CENTER);
                txtshop2.setGravity(Gravity.CENTER);
                block2.setGravity(Gravity.CENTER);
            }

//            For text color Block 1

            if (dataPramotion1.getFontColor().equals("")|| dataPramotion1.getFontColor()==null){

            }else{

                textoffername1.setTextColor(Color.parseColor(dataPramotion1.getFontColor()));
                txtofferdesc1.setTextColor(Color.parseColor(dataPramotion1.getFontColor()));
                txtshop1.setTextColor(Color.parseColor(dataPramotion1.getFontColor()));
            }

//            For text color Block 2
            if (dataPramotion2.getFontColor().equals("")|| dataPramotion2.getFontColor()==null){

            }else {
                textoffername2.setTextColor(Color.parseColor(dataPramotion2.getFontColor()));
                txtofferdesc2.setTextColor(Color.parseColor(dataPramotion2.getFontColor()));
                txtshop2.setTextColor(Color.parseColor(dataPramotion2.getFontColor()));
            }
//                 END


        }
//                 END

        block1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String startprice = "", endprice = "", blockdisountGroup = "";

                if (dataPramotion1.getBlockStratprice() != null && !dataPramotion1.getBlockStratprice().isEmpty()) {
                    startprice = dataPramotion1.getBlockStratprice();
                } else {
                    startprice = "0";
                }

                if (dataPramotion1.getBlockEndprice() != null && !dataPramotion1.getBlockEndprice().isEmpty()) {
                    endprice = dataPramotion1.getBlockEndprice();
                } else {
                    endprice = "0";
                }

                if (dataPramotion1.getBlockSpecialoffer() != null && !dataPramotion1.getBlockSpecialoffer().isEmpty()) {
                    blockdisountGroup = dataPramotion1.getBlockSpecialoffer();
                } else {
                    blockdisountGroup = "0";
                }

//                Edited by Varun for block and side by side for onclick url

                String type = dataPramotion1.getBlockActualtext();

                if (type.equalsIgnoreCase("Items On Promotion")){
                    type = "promotion";
                }else if (type.equalsIgnoreCase("Staff Picks")){
                    type="staffpick";
                }else if (type.equalsIgnoreCase("New Additions")){
                    type="newddition";
                }
//                  END
                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadViewAllFragment(type, "0", "0", startprice, endprice, blockdisountGroup, dataPramotion1.getBlockDisplaytext(), "", "");
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().loadViewAllFragment(type, "0", "0", startprice, endprice, blockdisountGroup, dataPramotion1.getBlockDisplaytext(), "", "");
                }

//                setBlocktoPage(dataPramotion1);
                //http://192.168.172.211:888//inventory/inventoryapp?customerid=189055&
                // sessionid=0&storeno=707&type=SpecialOffer&value1=1353&value2=0
            }
        });
        block2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setBlocktoPage(dataPramotion2);

                String startprice = "", endprice = "", blockdisountGroup = "";

                if (dataPramotion2.getBlockStratprice() != null && !dataPramotion2.getBlockStratprice().isEmpty()) {
                    startprice = dataPramotion2.getBlockStratprice();
                } else {
                    startprice = "0";
                }

                if (dataPramotion2.getBlockEndprice() != null && !dataPramotion2.getBlockEndprice().isEmpty()) {
                    endprice = dataPramotion2.getBlockEndprice();
                } else {
                    endprice = "0";
                }

                if (dataPramotion2.getBlockSpecialoffer() != null && !dataPramotion2.getBlockSpecialoffer().isEmpty()) {
                    blockdisountGroup = dataPramotion2.getBlockSpecialoffer();
                } else {
                    blockdisountGroup = "0";

                }
//                Edited by Varun for block and side by side for onclick url

                String type = dataPramotion2.getBlockActualtext();

                if (type.equalsIgnoreCase("Items On Promotion")){
                    type = "promotion";
                }else if (type.equalsIgnoreCase("Staff Picks")){
                    type="staffpick";
                }else if (type.equalsIgnoreCase("New Additions")){
                    type="newddition";
                }
//                 END

                if (Constant.SCREEN_LAYOUT == 1) {
                    MainActivity.getInstance().loadViewAllFragment(type, "0", "0", startprice, endprice, blockdisountGroup, dataPramotion2.getBlockDisplaytext(), "", "");
                } else if (Constant.SCREEN_LAYOUT == 2) {
                    MainActivityDup.getInstance().loadViewAllFragment(type, "0", "0", startprice, startprice, blockdisountGroup, dataPramotion2.getBlockDisplaytext(), "", "");
                }

            }
        });
        setBlockVisibility(isBlock1, cvBlock1);
        setBlockVisibility2(isBlock2, cvBlock2);
        linPRamotionBlock.addView(v1);
        linPRamotionBlock.setVisibility(View.VISIBLE);
    }

    public void setBlocktoPage(DataFrontModel model) {


//        if (Constant.SCREEN_LAYOUT == 1) {
//            MainActivity.getInstance().loadViewAllFragment("department",0,0);
//        } else if (Constant.SCREEN_LAYOUT == 2) {
//            MainActivityDup.getInstance().loadViewAllFragment("department",0,0);
//        }

        String URL_BP = "";
        String SessionUD = CustomerID.equalsIgnoreCase("0") ? DeviceInfo.getDeviceId(getActivity()) + "0011" : "0";
        if (!model.getBlockSpecialoffer().isEmpty()) {
            URL_BP = Constant.URL + "/inventory/inventoryapp"
                    + "?customerid=" + CustomerID
                    + "&sessionid=" + /*"0"*/SessionUD
                    + "&storeno=" + Constant.STOREID
                    + "&type=" + "specialoffer"
                    + "&value1=" + "" + model.getBlockSpecialoffer() + "&value2=" + "0";
        } else {
            URL_BP = Constant.URL + "/inventory/inventoryapp"
                    + "?customerid=" + CustomerID
                    + "&sessionid=" + SessionUD
                    + "&storeno=" + Constant.STOREID
                    + "&type=" + "promotion"
                    + "&value1=" + "" + model.getBlockStratprice() + "&value2=" + model.getBlockEndprice();
        }

        Log.d("Log", "URL_BP=" + URL_BP);
        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.getInstance().mContainer.removeAllViews();
            MainActivity.getInstance().LoadWebVew(URL_BP);
        } else if (Constant.SCREEN_LAYOUT == 2) {
            MainActivityDup.getInstance().mContainer.removeAllViews();
            MainActivityDup.getInstance().LoadWebVew(URL_BP);
        }
    }

    public void setBlockVisibility(Boolean isBlock1, CardView block1) {
        if (isBlock1) {
            block1.setVisibility(View.VISIBLE);
        } else {
            block1.setVisibility(View.GONE);
        }
    }
    public void setBlockVisibility2(Boolean isBlock2, CardView block2) {
        this.isBlock2 = isBlock2;
        this.block2 = block2;
        if (isBlock2) {
            block2.setVisibility(View.VISIBLE);
        } else {
            block2.setVisibility(View.GONE);
        }
    }

    public void removeAllView() {
       /* linHompage.
        linNewAddition.removeAllViews();
        linStaffPick.removeAllViews();
        linSpecialOffer.removeAllViews();
        linDepartments.removeAllViews();
        linWishlist.removeAllViews();
        linCart.removeAllViews();
        linRecentviewed .removeAllViews();*/
    }

    @Override
    public void onGetSpecialOfferResult(List<HomeItemModel> SpecialOfferList) {
        if (isAdded()) {
            if (SpecialOfferList != null && SpecialOfferList.size() > 0) {
                this.SpecialOfferList = SpecialOfferList;
                DataFrontModel dataFrontModel = ReturnDataFrontModel("Special Offers");
                setcommonScrollView(SpecialOfferList, linSpecialOffer, dataFrontModel);
            }
        }
    }

    @Override
    public void onGetDepartmentResult(List<JackDepartmentModel> DepartmentList) {
        if (isAdded()) {
            this.DepartmentList = DepartmentList;
            DataFrontModel dataFrontModel = ReturnDataFrontModel("Shop By Departments");
            LayoutInflater inflater1 = getLayoutInflater();
            View v1 = inflater1.inflate(R.layout.raw_home_recycle, null);
            RecyclerView rec_home = v1.findViewById(R.id.rec_home);
            TextView tabTitle = v1.findViewById(R.id.tabTitle);
            TextView ViewAll = v1.findViewById(R.id.ViewAll);
            tabTitle.setText(dataFrontModel.getBlockDisplaytext());
            ViewAll.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));

            ViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Constant.SCREEN_LAYOUT == 1) {
                        MainActivity.getInstance().txtdepartment.callOnClick();
                    } else if (Constant.SCREEN_LAYOUT == 2) {
                        MainActivityDup.getInstance().callFilterFragment();
                        //MainActivityDup.getInstance().callNotificationDialogWs();
                    }
                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            rec_home.setLayoutManager(layoutManager);
            rec_home.setHasFixedSize(true);
            ImageView img_left = v1.findViewById(R.id.img_left);
            ImageView img_right = v1.findViewById(R.id.img_right);
            if (DepartmentList.size() > 3) {
                img_left.setVisibility(View.VISIBLE);
                img_right.setVisibility(View.VISIBLE);
            } else {
                img_left.setVisibility(View.INVISIBLE);
                img_right.setVisibility(View.INVISIBLE);
            }
//**************************************

            img_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positionView = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();
                    if (positionView > 0) {
                        rec_home.smoothScrollToPosition(positionView - 1);
                    } else {
                        rec_home.smoothScrollToPosition(0);
                    }

                    //  Edited by Janvi 13th sep ******

                    int lastVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findLastVisibleItemPosition();

                    if (lastVisiblePos == -1) {
                        img_right.setVisibility(View.INVISIBLE);
                    } else {
                        img_right.setVisibility(View.VISIBLE);
                    }

                    int firstVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();

                    if (firstVisiblePos == 1) {
                        img_left.setVisibility(View.INVISIBLE);
                    } else {
                        img_left.setVisibility(View.VISIBLE);
                    }
                    //end **********************************

                }
            });
            img_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int positionView = ((LinearLayoutManager) rec_home.getLayoutManager()).findLastVisibleItemPosition();
                    rec_home.smoothScrollToPosition(positionView + 1);

                    //Edited by Janvi 13thSep

                    int firstVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();

                    if (firstVisiblePos == -1) {
                        img_left.setVisibility(View.INVISIBLE);
                    } else {
                        img_left.setVisibility(View.VISIBLE);
                    }

                    int lastVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findLastVisibleItemPosition();
                    if ((DepartmentList.size() - 1) == (lastVisiblePos)) {
                        img_right.setVisibility(View.INVISIBLE);
                    } else {
                        img_right.setVisibility(View.VISIBLE);
                    }

                    //*****************
                }
            });

            DepartmentListAdapter departmentListAdapter = new DepartmentListAdapter(getActivity(), this, DepartmentList);
            rec_home.setAdapter(departmentListAdapter);
            rec_home.setNestedScrollingEnabled(false);
            linDepartments.addView(v1);


            rec_home.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    if (DepartmentList.size() > 3) {
                        img_left.setVisibility(View.VISIBLE);
                        img_right.setVisibility(View.VISIBLE);
                    } else {
                        img_left.setVisibility(View.INVISIBLE);
                        img_right.setVisibility(View.INVISIBLE);
                    }
                    int lastVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                    int firstVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstCompletelyVisibleItemPosition();

                    if ((DepartmentList.size() - 1) == lastVisiblePos) {
                        img_right.setVisibility(View.INVISIBLE);
                    } else {
                        img_right.setVisibility(View.VISIBLE);
                    }
                    if (firstVisiblePos == 0) {
                        img_left.setVisibility(View.INVISIBLE);
                    } else {
                        img_left.setVisibility(View.VISIBLE);
                    }
                }
            });


            //Edited by Janvi 13thSep

            int firstVisiblePos = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();

            if (firstVisiblePos == -1) {
                img_left.setVisibility(View.INVISIBLE);
            } else {
                img_left.setVisibility(View.VISIBLE);
            }
            /*img_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positionView = ((LinearLayoutManager) rec_home.getLayoutManager()).findFirstVisibleItemPosition();
                    *//*if (positionView > 0) {
                        rec_home.smoothScrollToPosition(positionView - 1);
                    } else {
                        rec_home.smoothScrollToPosition(0);
                    }*//*

                }
            });
            img_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // int positionView = ((LinearLayoutManager)rec_home.getLayoutManager()).findLastVisibleItemPosition();
                    // rec_home.smoothScrollToPosition(positionView + 1);
                }
            });
            DepartmentListAdapter departmentListAdapter = new DepartmentListAdapter(getActivity(), this, DepartmentList);
            rec_home.setAdapter(departmentListAdapter);
            rec_home.setNestedScrollingEnabled(false);
            linDepartments.addView(v1);*/


            if (Constant.SCREEN_LAYOUT == 1) {
                CustomerID = MainActivity.getUserId();
            } else if (Constant.SCREEN_LAYOUT == 2) {
                CustomerID = MainActivityDup.getUserId();
            }
            if (CartItemList.size() == 0 || WishItemList.size() == 0) {
                if (!CustomerID.trim().equalsIgnoreCase("0") && templateModel.getWishListItems()) {
                    if (WishItemList.size() > 0) {
                        onGetWishListResult(WishItemList);
                    } else {
//                        String Url6 = Constant.WS_BASE_URL + Constant.GET_CUSTOMERCARTDATA + CustomerID + "/Wishlist/" + Constant.STOREID;
                        String Url6 = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + CustomerID + "/Wishlist/" + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
                        TaskWishListItem taskWishListItem = new TaskWishListItem(this);
                        taskWishListItem.execute(Url6);
                    }
                }

                if (!CustomerID.trim().equalsIgnoreCase("0") && templateModel.getCartItems()) {
                    if (CartItemList.size() > 0) {
                        onGetCartListResult(CartItemList);
                    } else {
//                        String Url7 = Constant.WS_BASE_URL + Constant.GET_CUSTOMERCARTDATA + CustomerID + "/Cart/" + Constant.STOREID;
                        String Url7 = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + CustomerID + "/Cart/" + Constant.STOREID + Constant.ENCODE_TOKEN_ID;
                        TaskCartListItem taskCartListItem = new TaskCartListItem(this);
                        taskCartListItem.execute(Url7);
                    }
                }
                if (!CustomerID.trim().equalsIgnoreCase("0") && templateModel.getRecentViewedItems()) {
                    if (RecViewItemList.size() > 0) {
                        onGetRecentViewedResult(RecViewItemList);
                    } else {
//                        String Url8 = Constant.WS_BASE_URL + Constant.GET_RECENTLYVIEWEDITEMS + Constant.STOREID + "/" + CustomerID;
                        String Url8 = Constant.WS_BASE_URL + Constant.GET_RECENTLYVIEWEDITEMS_V1 + Constant.STOREID + "/" + CustomerID + Constant.ENCODE_TOKEN_ID;
                        TaskRecentViewedItem taskRecentViewedItem = new TaskRecentViewedItem(this);
                        taskRecentViewedItem.execute(Url8);
                    }
                }
            }
        }
    }

    @Override
    public void onGetBannerItemResult(List<BannerModel> bannerItemList) {
        BannerItemList.clear();
        this.BannerItemList = bannerItemList;
        // if (isAdded()) {
        //autoScrollAdapter.notifyDataSetChanged();
        if (isAdded()) {
            if (BannerItemList != null && BannerItemList.size() > 0) {
                viewPager.setVisibility(View.VISIBLE);
                if (viewPager != null && bannerItemList != null && bannerItemList.size() > 0) {
                    autoScrollAdapter = new AutoScrollAdapter(getChildFragmentManager());
                    viewPager.setAdapter(autoScrollAdapter);
                }
            } else {
                viewPager.setVisibility(View.GONE);
            }
        }
        //}
        //viewPager.setOffscreenPageLimit(BannerItemList.size());
        String Url1 = Constant.WS_BASE_URL + Constant.GET_TEMPLATE + Constant.STOREID + "/home";
        TaskTemplate taskTemplate = new TaskTemplate(this);
        taskTemplate.execute(Url1);
    }

    public void fromSavelocation() {

        if (Constant.SCREEN_LAYOUT == 1) {
            progressBar.setVisibility(View.VISIBLE);
            Constant.isFromChangeLocDialog = true;
            SplaceScreen.getInstance().callgetThemeWS();
//            SplaceScreen.getInstance().callcontactinfo();

        } else if (Constant.SCREEN_LAYOUT == 2) {
            Constant.isFromChangeLocDialog = true;
            progressBar.setVisibility(View.VISIBLE);
            SplaceScreen.getInstance().callgetThemeWS();
//            SplaceScreen.getInstance().callcontactinfo();

        }
    }


//    ***************** Edited by Varun for homepage Add cart ******************
    public void addTocartData(HomeItemModel listHomrItem, boolean b, int resquantity) {
        this.homeItemModel = listHomrItem;
        this.isComeFomAddTocartBtn = b;
        iscomeFromUpdate = false;
        requestedQty = resquantity;

        if (listHomrItem != null && !listHomrItem.getItemMstId().isEmpty()) {

            String sku = null;
            try {
                sku = URLEncoder.encode(listHomrItem.getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (UserModel.Cust_mst_ID != null) {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + sku + "/" + resquantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "add" + "/" + Constant.invType;

                TaskAddtoCart taskAddToCart = new TaskAddtoCart((TaskAddtoCart.TaskAddToCartEvent) this);
                taskAddToCart.execute(cartWSurl);
            } else {
                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + "0" + "/" + "Cart" + "/" + "0" +
                        "/" + sku + "/" + resquantity +
                        "/" + Constant.STOREID + "/" + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + "add" + "/" + Constant.invType;;

                TaskAddtoCart taskAddToCart = new TaskAddtoCart((TaskAddtoCart.TaskAddToCartEvent) this);
                taskAddToCart.execute(cartWSurl);
            }

        }
    }

    @Override
    public void addToCartEventResult(UpdateCartQuantity addToCart) {

        if (addToCart != null) {

            if (addToCart.getResult().equalsIgnoreCase("success")) {
                DialogUtils.showDialog("Added to cart!");
                Utils.vibrateDevice(getContext());
                onGetCartData("", addToCart);
                if(isFromadpter_whenclickedonaddtocart){
                    isFromadpter_whenclickedonaddtocart = false;
                }

            } else if (addToCart.getResult().equalsIgnoreCase("Already added")) {

                if(isFromadpter_whenclickedonaddtocart){

                    onGetCartData("Already added",addToCart);

                }

//                if (isComeFomAddTocartBtn) {
//                    if (cartQtyOfItem.isEmpty()) {
//
//                        DialogUtils.notEnoughQuantityDialog(getActivity(), addToCart, requestedQty, "viewall", addToCart.getQty());
//                    } else {
//                        DialogUtils.notEnoughQuantityDialog(getActivity(), addToCart, requestedQty, "viewall", cartQtyOfItem);
//                    }
//                    isComeFomAddTocartBtn = false;
//                } else {
//                }

            } else if (addToCart.getResult().equalsIgnoreCase(
                    "Not enough Stock")) {
//                DialogUtils.notEnoughQuantityDialog(getActivity(), addToCart, requestedQty, "NotenoughStock", cartQtyOfItem);

//                DialogUtils.notEnoughQuantityNewDialog(getActivity(), addToCart, requestedQty, "NotenoughStock", cartQtyOfItem);


                if(isFromadpter_whenclickedonaddtocart){

                    onGetCartData("Not enough Stock",addToCart);
                }

            } else {
//                Toast.makeText(getActivity(), getString(R.string.str_network_message), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onGetCartData(String fromwhere, UpdateCartQuantity addToCart) {

        String url = null;
        if (UserModel.Cust_mst_ID != null) {
//            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID;
            url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + UserModel.Cust_mst_ID + "/" + Constant.MY_CART + Constant.STOREID  + Constant.ENCODE_TOKEN_ID;
            TaskCart taskCart = new TaskCart((TaskCart.TaskCardEvent) this, "");
            taskCart.execute(url);
        } else {
            if (isAdded()) {
//                url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + Constant.SESSION + Constant.STOREID;
                url = Constant.WS_BASE_URL + Constant.GET_CUSTOMER_CARD_DATA_V1 + DeviceInfo.getDeviceId(getActivity()) + "0011" + "/" + Constant.SESSION + Constant.STOREID  + Constant.ENCODE_TOKEN_ID;
                TaskCart taskCart = new TaskCart((TaskCart.TaskCardEvent) this, "");
                taskCart.execute(url);
            }
        }

        fromWhereClicked = fromwhere;
        this.addToCarttemp = addToCart;
    }


    @Override
    public void onShoppingCardResult(List<ShoppingCardModel> liShoppingCard, String s) {

        if (liShoppingCard.size() > 0) {

            if (Constant.SCREEN_LAYOUT == 1) {
                if (liShoppingCard.get(0).getCartID() == 0) {
                    /** Clear Shopping Cart Icon Count **/
                    MainActivity.getInstance().updateShoppingCartItemCount(0);
                } else {
                    int quntity = 0;
                    for (int i = 0; i < liShoppingCard.size(); i++) {
                        quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());

                        if (!liShoppingCard.get(i).getItemMstId().isEmpty() && !homeItemModel.getItemMstId().isEmpty()) {

                            if (liShoppingCard.get(i).getItemMstId().equalsIgnoreCase(homeItemModel.getItemMstId())) {

                                cartQtyOfItem = liShoppingCard.get(i).getQty();
                                cartId = liShoppingCard.get(i).getCartID();
                            }
                        }
                    }

                    MainActivity.countMenu.setTitle(String.valueOf(quntity));
                    MainActivity.getInstance().onShoppingCardResult(liShoppingCard, s);
                }

                openPopupOnclcik(cartId);

            } else if (Constant.SCREEN_LAYOUT == 2) {
                if (liShoppingCard.get(0).getCartID() == 0) {
                    /** Clear Shopping Cart Icon Count **/
                    MainActivityDup.getInstance().updateShoppingCartItemCount(0);
                } else {
                    int quntity = 0;
                    for (int i = 0; i < liShoppingCard.size(); i++) {
                        quntity = quntity + Integer.parseInt(liShoppingCard.get(i).getQty());

                        if (!liShoppingCard.get(i).getItemMstId().isEmpty() && !homeItemModel.getItemMstId().isEmpty()) {

                            if (liShoppingCard.get(i).getItemMstId().equalsIgnoreCase(homeItemModel.getItemMstId())) {

                                cartQtyOfItem = liShoppingCard.get(i).getQty();
                                cartId = liShoppingCard.get(i).getCartID();
                            }
                        }
                    }
                    MainActivityDup.countMenu.setTitle(String.valueOf(quntity));
                    MainActivityDup.getInstance().onShoppingCardResult(liShoppingCard, s);
                }

                openPopupOnclcik(cartId);
            }
        }else{
            openPopupOnclcik(cartId);
        }
    }

    private void openPopupOnclcik(long cartId) {

        if(isFromadpter_whenclickedonaddtocart){

            if(fromWhereClicked.equalsIgnoreCase("Already added")){

                if (isComeFomAddTocartBtn) {
                    if (cartQtyOfItem.isEmpty() && addToCarttemp != null) {

                        DialogUtils.notEnoughQuantityDialog(getActivity(), addToCarttemp, requestedQty, "Mainactivity_buyItgain", addToCarttemp.getQty());
                    } else {
                        DialogUtils.notEnoughQuantityDialog(getActivity(), addToCarttemp, requestedQty, "Mainactivity_buyItgain", cartQtyOfItem);
                    }
                    isComeFomAddTocartBtn = false;
                } else {
                }

            }else if(fromWhereClicked.equalsIgnoreCase("Not enough Stock")){

                DialogUtils.notEnoughQuantityNewDialog(getActivity(), addToCarttemp, requestedQty, "NotenoughStock", cartQtyOfItem, "fromViewall");

            }

            isFromadpter_whenclickedonaddtocart = false;
        }

    }

    public void updateToCartData(int finalRequested_Quantity, UpdateCartQuantity updateCartQuantity) {

        String noteCartId = "0";

        if(updateCartQuantity != null && updateCartQuantity.getNote()!= null && !updateCartQuantity.getNote().trim().isEmpty()){
            noteCartId = updateCartQuantity.getNote();
        }else{
            noteCartId = String.valueOf(cartId);
        }

        if (homeItemModel != null && !homeItemModel.getItemMstId().isEmpty()) {

            String sku = null;
            try {
                sku = URLEncoder.encode(homeItemModel.getItemMstId().trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (UserModel.Cust_mst_ID != null) {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + UserModel.Cust_mst_ID +
                        "/" + sku + "/" + finalRequested_Quantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart" + "/" + Constant.invType;

                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
                taskUpdatetoCart.execute(cartWSurl);
            } else {

                String cartWSurl = Constant.WS_BASE_URL + Constant.DELETE_CART + noteCartId + "/" + "Cart" + "/" + "0" +
                        "/" + sku + "/" + finalRequested_Quantity +
                        "/" + Constant.STOREID + "/" + "0" + "/" + "Updatemoreincart"+ "/" + Constant.invType;


                TaskUpdatetoCart taskUpdatetoCart = new TaskUpdatetoCart(this);
                taskUpdatetoCart.execute(cartWSurl);
            }
        }
    }

    @Override
    public void updateCartResult(UpdateCartQuantity updateCart) {
        if (updateCart.getResult().equalsIgnoreCase("success")) {
            Utils.vibrateDevice(getContext());
            onGetCartData("buyitagain",updateCart);
        }
    }


//   ********************* END ********************


    static class AutoScrollAdapter extends FragmentStatePagerAdapter {

        AutoScrollAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("Loe", "banner size=" + BannerItemList.size());
            Log.e("Loe", position + "=banner size1=" + BannerItemList.get(position).getImage());
            return AutoScrollPagerFragment.newInstance(BannerItemList.get(position));
        }

        @Override
        public int getCount() {
            if(BannerItemList == null){
                return 0;
            }else if (BannerItemList.size() > 0) {
                return BannerItemList.size();
            } else {
                return 0;
            }
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        // stop auto scroll when onPause
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Constant.SCREEN_LAYOUT == 1) {
            MainActivity.getInstance().mSearchedt.setText("");
            MainActivity.getInstance().mSearchedt.clearFocus();
            Utils.hideKeyboard();
        } else if (Constant.SCREEN_LAYOUT == 2) {
            MainActivityDup.getInstance().mSearchedt.setText("");
            MainActivityDup.getInstance().mSearchedt.clearFocus();

            MainActivityDup.getInstance().llsearch.setVisibility(View.GONE);

            if(!Constant.isloadWebview){
                MainActivityDup.getInstance().hidebackbutton();
            }else{
                Constant.isloadWebview = false;
            }

            Utils.hideKeyboard();

        }
        // start auto scroll when onResume

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onGetViewallResult(List<HomeItemModel> itemListing, String type) {

        if (type.equals("Promotion 1")) {

            if (itemListing != null && itemListing.size() > 0) {
                isPromotionBlock1 = true;
            } else {
                isPromotionBlock1 = false;
            }

            if (templateModel.getPromotion2()) {

                type = "Promotion 2";
                callWSForBlockItemList(type);

//                DataFrontModel dataPramotion2 = ReturnDataFrontModel("Promotion 2");
//
//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                        + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataPramotion2.getBlockStratprice() + "/" + dataPramotion2.getBlockEndprice() + "/" + "0";
//                TaskViewAll taskPromotionBlock = new TaskViewAll(this,getActivity(),type);
//                taskPromotionBlock.execute(Url);
            } else {

                if (isPromotionBlock1 || isPromotionBlock2) {
                    DataFrontModel dataPramotion1 = ReturnDataFrontModel("Promotion 1");
                    DataFrontModel dataPramotion2 = ReturnDataFrontModel("Promotion 2");
                    if (isAdded()) {
                        onAddBlock(linPRamotionBlock, dataPramotion1, dataPramotion2, isPromotionBlock1, isPromotionBlock2);
                    }
                }
            }

        }
        else if (type.equals("Promotion 2")) {

            if (itemListing != null && itemListing.size() > 0) {
                isPromotionBlock2 = true;
            } else {
                isPromotionBlock2 = false;
            }

            if (isPromotionBlock1 || isPromotionBlock2) {
                DataFrontModel dataPramotion1 = ReturnDataFrontModel("Promotion 1");
                DataFrontModel dataPramotion2 = ReturnDataFrontModel("Promotion 2");
                if (isAdded()) {
                    onAddBlock(linPRamotionBlock, dataPramotion1, dataPramotion2, isPromotionBlock1, isPromotionBlock2);
                }
            }

        }

//        Edited by Varun for block and side-by-side

      /*  else if (type.equals("newddition")) {
            DataFrontModel dataNewAddition = ReturnDataFrontModel("New Additions");
            DataFrontModel dataStaffPick = ReturnDataFrontModel("Staff Picks");
            DataFrontModel dataPramotion1 = ReturnDataFrontModel("Items On Promotion");
            if (itemListing != null && itemListing.size() > 0) {
                isnewadditionBlock1 = true;
            } else {
                isnewadditionBlock1 = false;
            }

//            Checking if side and block is not coming then default side by side is shown
            if (dataNewAddition.getHomeDisplayFormat().equals("") || dataNewAddition.getHomeDisplayFormat() == null) {
                if (isnewadditionBlock1){
                    Log.e("", "onGetViewallResult: 81" );
                    setcommonScrollView(itemListing, linNewAddition, dataNewAddition);
                    isnewadditionBlock1 = false;
                    Log.e("New Addition", "New Addition  Side : 1" + dataNewAddition.getHomeDisplayFormat());
                }
            } else {
                if (templateModel.getStaffPick()) {
                    if (!dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                        if (isnewadditionBlock1 || isstaffpickBlock2) {
                            if (isAdded()) {
                                Log.e("newaddition", "onGetViewallResultNew:" + dataNewAddition.getHomeDisplayFormat());
                                if (!dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                    if(isnewadditionBlock1) {
                                        Log.e("", "onGetViewallResult: 82" );
                                        setcommonScrollView(itemListing, linNewAddition, dataNewAddition);
                                        isnewadditionBlock1 = false;
                                    }
                                } else {
                                    if (templateModel.getItemsOnPromotion()) {
                                        if (dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                            isItemBlock1 = true;
                                            onAddBlock(linTest3, dataNewAddition, dataPramotion1, isnewadditionBlock1, isItemBlock1);
                                            Log.i("newAddition1", "block " + count);
                                            count++;
                                        }
                                    } else {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                        Log.i("newAddition2", "block " + count);
                                        count++;
                                    }
                                    if (dataStaffPick.getIsStaffPickActive().equals("1") && !dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block") &&
                                            dataPramotion1.getIsItemsonpromotionsActive().equals("1") && !dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isfalse);
                                        Log.i("newAddition3", "block " + count);
                                        count++;
                                    }
                                }
                            }
                        }
                    } else if (dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                        type = "Staff Picks";
//                    callWSForBlockItemList(type);
                    }
                } else {
                    if (isnewadditionBlock1 || isstaffpickBlock2) {
                        if (isAdded()) {
                            Log.e("newaddition", "onGetViewallResultNew:" + dataNewAddition.getHomeDisplayFormat());
                            if (!dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("BLock")) {
                                if(isnewadditionBlock1) {
                                    Log.e("", "onGetViewallResult: 83" );
                                    setcommonScrollView(itemListing, linNewAddition, dataNewAddition);
                                    isnewadditionBlock1 = false;
                                }
                            } else {
                                if (templateModel.getItemsOnPromotion()) {
                                    if (dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                        isItemBlock1 = true;
                                        onAddBlock(linPRamotion, dataNewAddition, dataPramotion1, isnewadditionBlock1, isItemBlock1);
                                        Log.i("newAddition4", "block " + count);
                                        count++;
                                    } else {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isfalse);
                                        Log.i("newAddition5", "block " + count);
                                        count++;
                                    }
                                } else {
                                    onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isfalse);
                                    Log.i("newAddition6", "block " + count);
                                    count++;
                                }
                                if (dataStaffPick.getIsStaffPickActive().equals("1") && !dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block") &&
                                        dataPramotion1.getIsItemsonpromotionsActive().equals("1") && !dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                    onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isfalse);
                                    Log.i("newAddition7", "block " + count);
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }else if (type.equals("staffpick")) {
            DataFrontModel dataNewAddition = ReturnDataFrontModel("New Additions");
            DataFrontModel dataStaffPick = ReturnDataFrontModel("Staff Picks");
            DataFrontModel dataPramotion1 = ReturnDataFrontModel("Items On Promotion");
            if (itemListing != null && itemListing.size() > 0) {
                isstaffpickBlock2 = true;
            } else {
                isstaffpickBlock2 = false;
            }

//            Checking if side and block is not coming then default side by side is shown

            if (dataStaffPick.getHomeDisplayFormat().isEmpty() || dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("")
                || dataStaffPick.getHomeDisplayFormat()==null){
                if (isstaffpickBlock2) {
                    Log.e("", "onGetViewallResult: 84" );
                    setcommonScrollView(itemListing, linStaffPick, dataStaffPick);
                    isstaffpickBlock2 = false;
                }
            }else {

                if (isstaffpickBlock2 || isnewadditionBlock1) {
                    if (isAdded()) {
                        Log.e("staffpick", "onGetViewallResultstaff: " + dataStaffPick.getHomeDisplayFormat());
                        if (isnewadditionBlock1) {
                            if (!dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                if (isstaffpickBlock2) {
                                    Log.e("", "onGetViewallResult: 85" );
                                    setcommonScrollView(itemListing, linStaffPick, dataStaffPick);
                                    isstaffpickBlock2 = false;
                                }
                            } else {
                                if (!dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                    if (isnewadditionBlock1) {
                                        Log.e("", "onGetViewallResult: 86" );
                                        setcommonScrollView(itemListing, linNewAddition, dataNewAddition);
                                        isnewadditionBlock1 = false;
                                    }
                                    if (templateModel.getItemsOnPromotion()) {
                                        if (dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                            isItemBlock1 = true;
                                            onAddBlock(linTest3, dataStaffPick, dataPramotion1, isstaffpickBlock2, isItemBlock1);
                                            Log.i("staffpick1", "block " + count);
                                            count++;
                                        } else {
                                            onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                            Log.i("staffpick2", "block " + count);
                                            count++;
                                        }
                                    } else {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                        Log.i("staffpick3", "block " + count);
                                        count++;
                                    }
                                } else if (dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block") && dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                    onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                    Log.i("staffpick4", "block " + count);
                                    count++;
                                }
                            }
                        } else {
                            if (!dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                if (isstaffpickBlock2) {
                                    Log.e("", "onGetViewallResult: 87" );
                                    setcommonScrollView(itemListing, linStaffPick, dataStaffPick);
                                    isstaffpickBlock2 = false;
                                }
                            } else {
                                if (!isnewadditionBlock1) {
                                    if (templateModel.getItemsOnPromotion()) {
                                        if (dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                            isItemBlock1 = true;
                                            onAddBlock(linTest3, dataStaffPick, dataPramotion1, isstaffpickBlock2, isItemBlock1);
                                            Log.i("staffpick5", "block " + count);
                                            count++;
                                        } else {
                                            onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                            Log.i("staffpick6", "block " + count);
                                            count++;
                                        }
                                    } else {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                        Log.i("staffpick7", "block " + count);
                                        count++;
                                    }
                                } else {
                                    onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                    Log.i("staffpick8", "block " + count);
                                    count++;
                                }
//                        onAddBlock(linPRamotionBlock, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                            }
                        }
                    }
                }

            }
        }
        else if (type.equalsIgnoreCase("promotion")){
            DataFrontModel dataNewAddition = ReturnDataFrontModel("New Additions");
            DataFrontModel dataStaffPick = ReturnDataFrontModel("Staff Picks");
            DataFrontModel dataPramotion1 = ReturnDataFrontModel("Items On Promotion");
            if (itemListing != null && itemListing.size() > 0) {
                isItemBlock1 = true;
            } else {
                isItemBlock1 = false;
            }
//            Checking if siode and block is not coming then default side by side is shown
            if (dataPramotion1.getHomeDisplayFormat().equals("") || dataPramotion1.getHomeDisplayFormat() == null) {
                if (!dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")){
                    if (isItemBlock1){
                        Log.e("", "onGetViewallResult: 88" );
                        setcommonScrollView(itemListing, lin, dataPramotion1);
                        isItemBlock1 = false;
                    }
                }
            }else {
                if (isItemBlock1 || isItemBlock2) {
                    DataFrontModel dataPramotion2 = ReturnDataFrontModel("Items On Promotion");
                    if (isAdded()) {
                        if (!dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                            if(isItemBlock1) {
                                Log.e("", "onGetViewallResult: 89" );
                                setcommonScrollView(itemListing, linPRamotion, dataPramotion1);
                                isItemBlock1 = false;
                            }
                        } else {
                            if (!dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block") && !dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")
                                    || !templateModel.getStaffPick() && !templateModel.getNewAddition()
                                    || templateModel.getNewAddition() && dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block")
                                    && templateModel.getStaffPick() && dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")
                                    || !templateModel.getNewAddition() && dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Side")
                                    || !templateModel.getStaffPick() && dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Side")) {
                                onAddBlock(linTest3, dataPramotion1, dataPramotion2, isItemBlock1, isItemBlock2);
                                Log.i("promotion", "block " + count);
                                count++;
                            }
                        }
                    }
                }
            }
        }  */

//        End

        else if (type.equals("SpecialOffer 1")) {

            if (itemListing != null && itemListing.size() > 0) {
                isSpecialOfferBlock1 = true;
            } else {
                isSpecialOfferBlock1 = false;
            }

            if (templateModel.getSpecialOffer2()) {

                type = "SpecialOffer 2";
                callWSForBlockItemList(type);

//                DataFrontModel dataSpecialOffer2 = ReturnDataFrontModel("SpecialOffer 2");
//
//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                        + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataSpecialOffer2.getBlockStratprice() + "/" + dataSpecialOffer2.getBlockEndprice() + "/" + "0";
//                TaskViewAll taskSpecialOfferBlock = new TaskViewAll(this,getActivity(),type);
//                taskSpecialOfferBlock.execute(Url);

            } else {
                if (isSpecialOfferBlock1 || isSpecialOfferBlock2) {
                    DataFrontModel dataSpecialOffer1 = ReturnDataFrontModel("SpecialOffer 1");
                    DataFrontModel dataSpecialOffer2 = ReturnDataFrontModel("SpecialOffer 2");
                    if (isAdded()) {
                        onAddBlock(lindiscountBlock, dataSpecialOffer1, dataSpecialOffer2, isSpecialOfferBlock1, isSpecialOfferBlock2);
                    }
                }
            }
        } else if (type.equals("SpecialOffer 2")) {

            if (itemListing != null && itemListing.size() > 0) {
                isSpecialOfferBlock2 = true;
            } else {
                isSpecialOfferBlock2 = false;
            }

            if (isSpecialOfferBlock1 || isSpecialOfferBlock2) {
                DataFrontModel dataSpecialOffer1 = ReturnDataFrontModel("SpecialOffer 1");
                DataFrontModel dataSpecialOffer2 = ReturnDataFrontModel("SpecialOffer 2");
                if (isAdded()) {
                    onAddBlock(lindiscountBlock, dataSpecialOffer1, dataSpecialOffer2, isSpecialOfferBlock1, isSpecialOfferBlock2);
                }
            }
        } else if (type.equals("Announcement 1")) {

            if (itemListing != null && itemListing.size() > 0) {
                isAnnouncementBlock1 = true;
            } else {
                isAnnouncementBlock1 = false;
            }

            if (templateModel.getAnnouncement2()) {

                type = "Announcement 2";
                callWSForBlockItemList(type);

//                DataFrontModel dataAnnouncement2 = ReturnDataFrontModel("Announcement 2");
//
//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                        + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataAnnouncement2.getBlockStratprice() + "/" + dataAnnouncement2.getBlockEndprice() + "/" + "0";
//                TaskViewAll taskAnnouncementBlock = new TaskViewAll(this,getActivity(),type);
//                taskAnnouncementBlock.execute(Url);

            } else {
                if (isAnnouncementBlock1 || isAnnouncementBlock2) {
                    DataFrontModel dataAnnouncement1 = ReturnDataFrontModel("Announcement 1");
                    DataFrontModel dataAnnouncement2 = ReturnDataFrontModel("Announcement 2");
                    if (isAdded()) {
                        onAddBlock(linAnnouncemnetBlock, dataAnnouncement1, dataAnnouncement2, isAnnouncementBlock1, isAnnouncementBlock2);
                    }
                }
            }
        } else if (type.equals("Announcement 2")) {

            if (itemListing != null && itemListing.size() > 0) {
                isAnnouncementBlock2 = true;
            } else {
                isAnnouncementBlock2 = false;
            }

            if (isAnnouncementBlock1 || isAnnouncementBlock2) {
                DataFrontModel dataAnnouncement1 = ReturnDataFrontModel("Announcement 1");
                DataFrontModel dataAnnouncement2 = ReturnDataFrontModel("Announcement 2");
                if (isAdded()) {
                    onAddBlock(linAnnouncemnetBlock, dataAnnouncement1, dataAnnouncement2, isAnnouncementBlock1, isAnnouncementBlock2);
                }
            }
        } else if (type.equals("SpecialOffer 3")) {

            if (itemListing != null && itemListing.size() > 0) {
                isSpecialOfferBlock3 = true;
            } else {
                isSpecialOfferBlock3 = false;
            }

            if (templateModel.getSpecialOffer4()) {

                type = "SpecialOffer 4";
                callWSForBlockItemList(type);

//                DataFrontModel dataSpecialOffer4 = ReturnDataFrontModel("SpecialOffer 4");
//
//                String Url = Constant.WS_BASE_URL + Constant.GET_INVENTORYS + "/" +  Constant.STOREID + "/" + "0" + "/" + "0" + "/" + "0"
//                        + "/" + "0" + "/" + "0;0" + "/" + "1" + "/" + "10" + "/" + type + "/" + "Price" + "/" + "Asc" + "/" + dataSpecialOffer4.getBlockStratprice() + "/" + dataSpecialOffer4.getBlockEndprice() + "/" + "0";
//                TaskViewAll taskSpecialOfferBlock = new TaskViewAll(this,getActivity(),type);
//                taskSpecialOfferBlock.execute(Url);

            } else {
                if (isSpecialOfferBlock3 || isSpecialOfferBlock4) {
                    DataFrontModel dataSpecialOffer3 = ReturnDataFrontModel("SpecialOffer 3");
                    DataFrontModel dataSpecialOffer4 = ReturnDataFrontModel("SpecialOffer 4");
                    if (isAdded()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            onAddBlock(lindiscountblock2, dataSpecialOffer3, dataSpecialOffer4, isSpecialOfferBlock3, isSpecialOfferBlock4);
                        }
                    }
                }
            }
        } else if (type.equals("SpecialOffer 4")) {

            if (itemListing != null && itemListing.size() > 0) {
                isSpecialOfferBlock4 = true;
            } else {
                isSpecialOfferBlock4 = false;
            }

            if (isSpecialOfferBlock3 || isSpecialOfferBlock4) {
                DataFrontModel dataSpecialOffer3 = ReturnDataFrontModel("SpecialOffer 3");
                DataFrontModel dataSpecialOffer4 = ReturnDataFrontModel("SpecialOffer 4");
                if (isAdded()) {
                    onAddBlock(lindiscountblock2, dataSpecialOffer3, dataSpecialOffer4, isSpecialOfferBlock3, isSpecialOfferBlock4);
                }
            }
        }


//        if (templateModel.getAnnouncement1() || templateModel.getAnnouncement2()) {
//            DataFrontModel dataPramotion1 = ReturnDataFrontModel("Announcement 1");
//            DataFrontModel dataPramotion2 = ReturnDataFrontModel("Announcement 2");
//            if (isAdded()) {
//                onAddBlock(linAnnouncemnetBlock, dataPramotion1, dataPramotion2, templateModel.getAnnouncement1(), templateModel.getAnnouncement2());
//            }
//        }
//        if (templateModel.getSpecialOffer3() || templateModel.getSpecialOffer4()) {
//            DataFrontModel dataPramotion1 = ReturnDataFrontModel("SpecialOffer 3");
//            DataFrontModel dataPramotion2 = ReturnDataFrontModel("SpecialOffer 4");
//            if (isAdded()) {
//                onAddBlock(lindiscountblock2, dataPramotion1, dataPramotion2, templateModel.getSpecialOffer3(), templateModel.getSpecialOffer4());
//            }
//        }


    }

    private void CheckBlocks(List<HomeItemModel> itemListing, String type) {
        DataFrontModel dataNewAddition = ReturnDataFrontModel("New Additions");
        DataFrontModel dataStaffPick = ReturnDataFrontModel("Staff Picks");
        DataFrontModel dataPramotion1 = ReturnDataFrontModel("Items On Promotion");

          if (type.equals("newddition")) {

            if (itemListing != null && itemListing.size() > 0) {
                isnewadditionBlock1 = true;
            } else {
                isnewadditionBlock1 = false;
            }

//            Checking if side and block is not coming then default side by side is shown
            if (dataNewAddition.getHomeDisplayFormat().equals("") || dataNewAddition.getHomeDisplayFormat() == null) {
                if (isnewadditionBlock1){
                    Log.e("", "onGetViewallResult: 81" );
                    setcommonScrollView(itemListing, linNewAddition, dataNewAddition);
                    isnewadditionBlock1 = false;
                    Log.e("New Addition", "New Addition  Side : 1" + dataNewAddition.getHomeDisplayFormat());
                }
            } else {
                if (templateModel.getStaffPick()) {
                    if (!dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                        if (isnewadditionBlock1 || isstaffpickBlock2) {
                            if (isAdded()) {
                                Log.e("newaddition", "onGetViewallResultNew:" + dataNewAddition.getHomeDisplayFormat());
                                if (!dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                    if(isnewadditionBlock1) {
                                        Log.e("", "onGetViewallResult: 82" );
                                        setcommonScrollView(itemListing, linNewAddition, dataNewAddition);
                                        isnewadditionBlock1 = false;
                                    }
                                } else {
                                    if (templateModel.getItemsOnPromotion()) {
                                        if (dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                            isItemBlock1 = true;
                                            onAddBlock(linTest3, dataNewAddition, dataPramotion1, isnewadditionBlock1, isItemBlock1);
                                            Log.i("newAddition1", "block " + count);
                                            count++;
                                        }
                                    } else {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                        Log.i("newAddition2", "block " + count);
                                        count++;
                                    }
                                    if (dataStaffPick.getIsStaffPickActive().equals("1") && !dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block") &&
                                            dataPramotion1.getIsItemsonpromotionsActive().equals("1") && !dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isfalse);
                                        Log.i("newAddition3", "block " + count);
                                        count++;
                                    }
                                }
                            }
                        }
                    } else if (dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                        type = "Staff Picks";
//                    callWSForBlockItemList(type);
                    }
                } else {
                    if (isnewadditionBlock1 || isstaffpickBlock2) {
                        if (isAdded()) {
                            Log.e("newaddition", "onGetViewallResultNew:" + dataNewAddition.getHomeDisplayFormat());
                            if (!dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("BLock")) {
                                if(isnewadditionBlock1) {
                                    Log.e("", "onGetViewallResult: 83" );
                                    setcommonScrollView(itemListing, linNewAddition, dataNewAddition);
                                    isnewadditionBlock1 = false;
                                }
                            } else {
                                if (templateModel.getItemsOnPromotion()) {
                                    if (dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                        isItemBlock1 = true;
                                        onAddBlock(linPRamotion, dataNewAddition, dataPramotion1, isnewadditionBlock1, isItemBlock1);
                                        Log.i("newAddition4", "block " + count);
                                        count++;
                                    } else {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isfalse);
                                        Log.i("newAddition5", "block " + count);
                                        count++;
                                    }
                                } else {
                                    onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isfalse);
                                    Log.i("newAddition6", "block " + count);
                                    count++;
                                }
                                if (dataStaffPick.getIsStaffPickActive().equals("1") && !dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block") &&
                                        dataPramotion1.getIsItemsonpromotionsActive().equals("1") && !dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                    onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isfalse);
                                    Log.i("newAddition7", "block " + count);
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }else if (type.equals("staffpick")) {

            if (itemListing != null && itemListing.size() > 0) {
                isstaffpickBlock2 = true;
            } else {
                isstaffpickBlock2 = false;
            }

//            Checking if side and block is not coming then default side by side is shown

            if (dataStaffPick.getHomeDisplayFormat().isEmpty() || dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("")
                    || dataStaffPick.getHomeDisplayFormat()==null){
                if (isstaffpickBlock2) {
                    Log.e("", "onGetViewallResult: 84" );
                    setcommonScrollView(itemListing, linStaffPick, dataStaffPick);
                    isstaffpickBlock2 = false;
                }
            }else {

                if (isstaffpickBlock2 || isnewadditionBlock1) {
                    if (isAdded()) {
                        Log.e("staffpick", "onGetViewallResultstaff: " + dataStaffPick.getHomeDisplayFormat());
                        if (isnewadditionBlock1) {
                            if (!dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                if (isstaffpickBlock2) {
                                    Log.e("", "onGetViewallResult: 85" );
                                    setcommonScrollView(itemListing, linStaffPick, dataStaffPick);
                                    isstaffpickBlock2 = false;
                                }
                            } else {
                                if (!dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                    if (isnewadditionBlock1) {
                                        Log.e("", "onGetViewallResult: 86" );
                                        setcommonScrollView(itemListing, linNewAddition, dataNewAddition);
                                        isnewadditionBlock1 = false;
                                    }
                                    if (templateModel.getItemsOnPromotion()) {
                                        if (dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                            isItemBlock1 = true;
                                            onAddBlock(linTest3, dataStaffPick, dataPramotion1, isstaffpickBlock2, isItemBlock1);
                                            Log.i("staffpick1", "block " + count);
                                            count++;
                                        } else {
                                            onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                            Log.i("staffpick2", "block " + count);
                                            count++;
                                        }
                                    } else {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                        Log.i("staffpick3", "block " + count);
                                        count++;
                                    }
                                } else if (dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block") && dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                    onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                    Log.i("staffpick4", "block " + count);
                                    count++;
                                }
                            }
                        } else {
                            if (!dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                if (isstaffpickBlock2) {
                                    Log.e("", "onGetViewallResult: 87" );
                                    setcommonScrollView(itemListing, linStaffPick, dataStaffPick);
                                    isstaffpickBlock2 = false;
                                }
                            } else {
                                if (!isnewadditionBlock1) {
                                    if (templateModel.getItemsOnPromotion()) {
                                        if (dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                                            isItemBlock1 = true;
                                            onAddBlock(linTest3, dataStaffPick, dataPramotion1, isstaffpickBlock2, isItemBlock1);
                                            Log.i("staffpick5", "block " + count);
                                            count++;
                                        } else {
                                            onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                            Log.i("staffpick6", "block " + count);
                                            count++;
                                        }
                                    } else {
                                        onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                        Log.i("staffpick7", "block " + count);
                                        count++;
                                    }
                                } else {
                                    onAddBlock(linTest, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                                    Log.i("staffpick8", "block " + count);
                                    count++;
                                }
//                        onAddBlock(linPRamotionBlock, dataNewAddition, dataStaffPick, isnewadditionBlock1, isstaffpickBlock2);
                            }
                        }
                    }
                }

            }
        }
        else if (type.equalsIgnoreCase("promotion")){

            if (itemListing != null && itemListing.size() > 0) {
                isItemBlock1 = true;
            } else {
                isItemBlock1 = false;
            }
//            Checking if siode and block is not coming then default side by side is shown
            if (dataPramotion1.getHomeDisplayFormat().equals("") || dataPramotion1.getHomeDisplayFormat() == null) {
                if (!dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")){
                    if (isItemBlock1){
                        Log.e("", "onGetViewallResult: 88" );
                        setcommonScrollView(itemListing, lin, dataPramotion1);
                        isItemBlock1 = false;
                    }
                }
            }else {
                if (isItemBlock1 || isItemBlock2) {
                    DataFrontModel dataPramotion2 = ReturnDataFrontModel("Items On Promotion");
                    if (isAdded()) {
                        if (!dataPramotion1.getHomeDisplayFormat().equalsIgnoreCase("Block")) {
                            if(isItemBlock1) {
                                Log.e("", "onGetViewallResult: 89" );
                                setcommonScrollView(itemListing, linPRamotion, dataPramotion1);
                                isItemBlock1 = false;
                            }
                        } else {
                            if (!dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block") && !dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")
                                    || !templateModel.getStaffPick() && !templateModel.getNewAddition()
                                    || templateModel.getNewAddition() && dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Block")
                                    && templateModel.getStaffPick() && dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Block")
                                    || !templateModel.getNewAddition() && dataStaffPick.getHomeDisplayFormat().equalsIgnoreCase("Side")
                                    || !templateModel.getStaffPick() && dataNewAddition.getHomeDisplayFormat().equalsIgnoreCase("Side")) {
                                onAddBlock(linTest3, dataPramotion1, dataPramotion2, isItemBlock1, isItemBlock2);
                                Log.i("promotion", "block " + count);
                                count++;
                            }
                        }
                    }
                }
            }
        }


    }


//     Edited by Varun for reward point show below the search bar
    public void loadRewardWSData() {

        if (UserModel.Cust_mst_ID != null) {
            String url = Constant.WS_BASE_URL + Constant.GET_LOYALTY_INFO + UserModel.Cust_mst_ID + "/" + Constant.STOREID;
            TaskLoyaltyInfo loyaltyInfo = new TaskLoyaltyInfo(getActivity(),this);
            Log.d("", "getLoyaltyReward: " + url);
            loyaltyInfo.execute(url);
        }else {
            ll_Reward_main.setVisibility(View.GONE);
            Constant.isloyaltyreward=false;
        }
    }

    @Override
    public void onLoyaltyInfoResult(LoyaltyInfo l) {

        Log.d("", "onLoyaltyInfoResult: " + l);
        if (l != null) {
//            For Layout 1
            if (Constant.SCREEN_LAYOUT == 1) {
                if (l.getLoyaltyRewardStatus().equals("Y")) {
                    if (l.getLoyaltyType().equals("Internal")) {

                        if (!l.getLoyaltyCard().equalsIgnoreCase("") && l.getLoyaltyCard() != null) {

                           ll_Reward_main.setVisibility(View.VISIBLE);
                            Constant.isloyaltyreward=true;

                            if (l.getPoints() != null && !l.getPoints().equalsIgnoreCase("")) {
                                int points = Integer.parseInt(l.getPoints());
                                tv_points_main.setText("Reward Points: " + getFormatedAmount(points));
                            } else {
                                tv_points_main.setText("Reward Points: 0");
                            }


                            if (l.getRebate() != null && !l.getRebate().equalsIgnoreCase("") && !l.getRebate().equals("0.00")) {
//                            double rebate = 0.00;
//                            rebate = Double.parseDouble(l.getRebate());
                                tv_rebate_main.setText("Rebate Available: $" + l.getRebate());
                                tv_rebate_main.setVisibility(View.VISIBLE);

                            } else {
                                tv_rebate_main.setVisibility(View.GONE);
                            }
                        } else {
                           ll_Reward_main.setVisibility(View.GONE);
                            Constant.isloyaltyreward=false;
                        }
                    } else {
                       ll_Reward_main.setVisibility(View.GONE);
                        Constant.isloyaltyreward=false;
                    }
                }else{
                    ll_Reward_main.setVisibility(View.GONE);
                    Constant.isloyaltyreward=false;
                }
            }
//            For Layout 2
            else if(Constant.SCREEN_LAYOUT==2){
                if (l.getLoyaltyRewardStatus().equals("Y")) {
                    if (l.getLoyaltyType().equals("Internal")) {

                        if (!l.getLoyaltyCard().equalsIgnoreCase("") && l.getLoyaltyCard() != null) {

                            ll_Reward_main.setVisibility(View.VISIBLE);
                            Constant.isloyaltyreward=true;

                            if (l.getPoints() != null && !l.getPoints().equalsIgnoreCase("")) {
                                int points = Integer.parseInt(l.getPoints());
                                tv_points_main.setText("Reward Points: " + getFormatedAmount(points));
                            } else {
                               tv_points_main.setText("Reward Points: 0");
                            }


                            if (l.getRebate() != null && !l.getRebate().equalsIgnoreCase("") && !l.getRebate().equals("0.00")) {
//                            double rebate = 0.00;
//                            rebate = Double.parseDouble(l.getRebate());
                               tv_rebate_main.setText("Rebate Available: $" + l.getRebate());
                               tv_rebate_main.setVisibility(View.VISIBLE);
                            } else {
                               tv_rebate_main.setVisibility(View.GONE);
                            }
                        } else {
                            ll_Reward_main.setVisibility(View.GONE);
                            Constant.isloyaltyreward=false;
                        }
                    } else {
                        ll_Reward_main.setVisibility(View.GONE);
                        Constant.isloyaltyreward=false;
                    }
                }else{
                    ll_Reward_main.setVisibility(View.GONE);
                    Constant.isloyaltyreward=false;
                }
            }
        }
//        If webservice data is null then both will not seen
        else{
           ll_Reward_main.setVisibility(View.GONE);
//            MainActivityDup.getInstance().ll_Reward_main.setVisibility(View.GONE);
            Constant.isloyaltyreward=false;
        }

    }

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }


}
