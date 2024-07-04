package com.aspl.Utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.util.Base64;

import com.aspl.mbsmodel.CardModel;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.DataFrontModel;
import com.aspl.mbsmodel.DataHomePageBlockModel;
import com.aspl.mbsmodel.DeleteAccountModel;
import com.aspl.mbsmodel.DepartmentModel;
import com.aspl.mbsmodel.EditShippingData;
import com.aspl.mbsmodel.FilterModel;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.InstorePurchaseDetailModel;
import com.aspl.mbsmodel.ItemDescModel;
import com.aspl.mbsmodel.JackDepartmentModel;
import com.aspl.mbsmodel.MbsDataModel;
import com.aspl.mbsmodel.NotificationModel;

import com.aspl.mbsmodel.OrderSummary;
import com.aspl.mbsmodel.OtpModel;
import com.aspl.mbsmodel.PlaceModel;
import com.aspl.mbsmodel.ReOrderItemModel;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.StoreHour;
import com.aspl.mbsmodel.StoreLocationModel;
import com.aspl.mbsmodel.StoreclosesModel;
import com.aspl.mbsmodel.SubDepartmentModel;
import com.aspl.mbsmodel.ThemeModel;
import com.aspl.mbsmodel.TwentyOneYear;
import com.aspl.mbsmodel.UserModel;
import com.aspl.ws.Async_getSubDept;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by new on 06/26/2017.
 */
public class Constant {

    public static ProgressDialog pDialog;
    /**
     * IBM Local Server
     *
     *
     *
     *
     *
     **/
//////    local 707.
//      public static String IMG_BASE = "http://192.168.172.244:808/";
//      public static String URL = "http://192.168.172.244:888/";
//      public static String WS_BASE = "http://192.168.172.244:889";
//      public static String WS_BASE_POS_URL = "http://192.168.172.244:140/CsCode/WebStoreOnlineService.asmx/";
//      public static String WS_BASE_FOR_POS_LIGHTNING = "http://192.168.172.244:140/CsCode/WebStoreOnlineService.asmx/";

    /**
     * Test Sever
     **/
//////707,105,2401

//    public static String IMG_BASE = "http://testimages.lightningpos.com/";
//
    public static String IMG_BASE = "https://testimages.lightningpos.com/";
    public static String URL = "https://ecomtest.lightningpos.com/";
    public static String WS_BASE = "https://ecomtestWCF.lightningpos.com";
    public static String WS_BASE_POS_URL = "https://ecomtest.lightningpos.com/Home/";
    public static String WS_BASE_FOR_POS_LIGHTNING = "https://evo.lightningpos.com/CSCode/WebStoreOnlineService.asmx/";


    /**
     * Secure Server
     *
     */

//327,315,57,44,99,7365,90,3824,2105
//      public static String IMG_BASE = "https://images.lightningpos.com/";
//      public static String URL="https://ecomsecure.lightningpos.com/";
//      public static String WS_BASE="https://ecomsecureWCF.lightningpos.com";
//      public static String WS_BASE_POS_URL = "https://evo.lightningpos.com/CSCode/LightningOnlineService1.asmx/";
//      public static String WS_BASE_FOR_POS_LIGHTNING = "https://posservice.lightningpos.com/CSCode/WebStoreOnlineService.asmx/";


    //*****************
    public static String HOME = "Home/IndexApp";
    public static String MAP_API_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
//    public static String MAP_API_URL1 = "https://maps.googleapis.com/" +
//            "/api/place/details/json?";
    public static String MAP_API_URL1 = "https://maps.googleapis.com/maps/api/place/details/json?";
    public static String INPUT = "input=";
    public static String PLACE_ID = "place_id=";
    //public static String MAP_API_URL="https://maps.googleapis.com/maps/api/place/autocomplete/json?input=";
    /*for live*/

    //public static String WS_BASE="http://192.168.172.211:889";

    public static String WS_BASE_URL = WS_BASE + "/WebStoreRestService.svc/";
    public static String GETPAGES = "GetPages/";
    public static String GETPAGES_FOR_ANDROID = "GetPagesForAndroid/";
    public static String GETPAGES_STATUS = "GetPagesStatus/";
    public static String SEND_DRINK_RECIPES= "SendDrinkRecipes";


    /*
     *
     * Layout two menu
     * public static String GETPAGES = "GetPages/";
     * */
    public static String GETPAGES_DETAIL_BLOG = "GetPageDetail/blog/";
    public static String GETPAGES_DETAIL = "GetPageDetail";
    public static  String Pages = "information";

    public static String GETTHEME = "GetEcomTheam/";
    public static String GETDEPARTMENT = "GetDepartments/";
    public static String STOREID = "707";
    public static String SELECTED_LOCATION_STOREID = "707";
    public static String MainSTOREID = "";
    public static String PREVIOUS_STOREID = "";

    public static String CREAT_USER = "InsertNewUserFromCart/";
    public static String CHECK_PASSWORD = "CheckPassword/";
    public static String CHANGE_PASSWORD = "InsertPassword/";

    public static String GET_SITE_INFO = "GetSiteInfo";
    //store location
    public static String GET_CORPORATE_STORE_SUBSTORELIST= "GetCorporateStoreSubStoreListForEcom";
    public static String GET_CORPORATE_STORE_SUBSTORELIST_V1= "GetCorporateStoreSubStoreListForEcom_V1";
    public static String SAVE_CUS_DEFAULT_LOCATION= "SaveCustomerDefaultLocation";

    //this is for forgetPassword
    public static String GENERATE_OTP = "GenerateOTP/";
    public static String CHECK_OTP = "CheckOTP/";
    public static String GET_FILTER_INFO = "GetFilterInfo";
    //SubDepartment
    public static String GET_STYLE = "GetStyles/";

    //ZipCode Address
    //http://192.168.172.211:889/WebStoreRestService.svc/GetZipCode/11001
    public static String GET_ZIP_CODE = "GetZipCode/";

    //Card
    //http://192.168.172.211:889/WebStoreRestService.svc/GetCustomerCartData/188723/Cart/707
    public static String GET_CUSTOMER_CARD_DATA_PAYMENT = "GetCustomerCartDataPayment/";
    public static String MY_CART = "Cart/";
    public static String SESSION = "Session/";
    public static String WISH_LIST = "Wishlist/";
    public static String FILTER_URL = "GetFilterByDepartmentStyleSizePrice_v2/";
    public static String FILTER_HOME_PAGE = "GetHomePageFilter/";

    public static String IMG_BASE_URL = "/WebStoreImages/Inventory/";
    public static String IMG_NOTIFICATION_URL = "/img/Notification/";
    public static String IMG_BANNER_URL="WebStoreImages/banners/";

    public static String IMG_DEPARTMENT="/WebStoreImages/Department/";
    public static String IMG_NO_IMAGE = "/WebStoreImages/NoImage/";
    public static String GET_GLOBALSETTING = "GetGlobalSettings/";
    public static String DELETE_CART = "InsertCartData/";
    public static String GET_SHIPPING_DATA = "GetShippingAddress/";
    public static String GET_CUSTOMER_DATA = "GetCustomerData/";
    public static String GET_STORE_HOUR = "GetDeliveryHours/";
    public static String GET_LOYALTY_INFO = "GetLoyaltyInfo/";
    public static String GET_ABOUT_US = "GetPageData/AboutUs/";

    public static String SAVE_CONTACT_MAIL_INFO= "SaveContactMailInfo/";
    public static String SAND_EMAIL_FROM_CONTACTUS= "SendEmailFromContactUs/";
    public static String INSERT_TEMP_ORDER_DETAILS = "InsertTempOrderDetails/";
    public static String UPDATE_BILLING_TIP_VALUE = "UpdateBillingTipValue/";
    public static String UPDATE_BILLING_ADDRESS = "UpdateBillingAddress/";
    public static String UPDATE_BILLING_ADDRESS_POS = "updatebillingaddressPOS/";
    public static String ADD_CUSTOMER_SHIPPING_ADDRESS = "AddCustomerShippingAddress/";
    public static String UPDATE_SHIPPING_ADDRESS = "UpdateShippingAddress/";
    public static String GETSHIPPING_FLAT_RATES_BYSTORENO = "GetShippingFlatRatesByStoreNo/";

    public static String POS_UPDATE_BILLING_ADDRESS = "UpdateBillingAddress?";
    public static String POS_ADD_CUSTOMER_SHIPPING_ADDRESS = "AddCustomerShippingAddress?";
    public static String UPDATE_CART_DATA_OF_GIFT_WRAP = "UpdateCartDataForGiftWrap/";
    public static String GET_CREDIT_CARD_SETTING = "GetCreditCardSetting/";
    public static String DELETE_ORDER_FOR_BOXSALT_FOR_DEMO_STORE = "DeleteOrderFromBoxsaltForDemoStore/";
    public static String INSERT_ORDER_DETAIL = "InsertOrderDetails/";
    public static String INSERT_ORDER_DATA_BEFORE_PAYWARE = "InsertOrderDataBeforePayware/";
    public static String GET_VALUE_INFO_SETTING = "GetValuetecInfoSetting/";
    public static String GET_SKOOP_SETTING  ="GetScoopSetting/";
    public static String GET_ORDER_SUMMARY_DETAIL = "GetOrderSummaryData/";
    public static String SAVE_CARD = "InsertCustomerCreditCardsXML/";
    public static String USAEPAY_SAVECUSTOMERCARD = "USAPayUpdateCustomerCreditCards/";
    public static String GET_CARD_DETAIL = "GetCustCreditCard/";
    public static String USA_PAY_GET_CARD_DETAIL = "USAPayGetCustCreditCard/";
    public static String UPDATE_SESSION_TO_CART = "UpdateCartDataForBrowserId/";
    public static String GET_SEARCH_DATA = "Getsearchdatanew/";
//    public static String GET_SEARCH_DATA_OLD = "GetSearchData/";
    public static String GET_EXPIRE_CARD_DETAIL = "GetExpiredCreditCards/";
    public static String RE_ORDER_DATA = "ReOrder/";
    public static String RE_ORDER_DETAILS = "reorderdetails/";
    public static String WS_INSERT_REORDER = WS_BASE_URL +"InsertReorder";
    public static String WS_SAVE_RETURN_ORDER = WS_BASE_URL +"SaveReturnOrder";
    public static String WS_SAVE_CUSTOMER_NOTES = WS_BASE_URL +"SaveCustomerNotes";
    public static String RE_BUYITAGAIN_DATA = "BuyItAgain/";
    public static String GET_DELI_ZIP_CODES = "GetDeliveryZipCodes/";
    public static String SAVE_PLACE_ORDER = "GetpreAuthCustomerCard/";
    public static String WS_GET_PRE_AUTH_CUSTOMER_CARD_NEW = "GetpreAuthCustomerCardNew/";
    public static String WS_PAYWARE_PAYMENT_TRANSACTION_DETAILS = "PaywarePaymentTransactionDetails/";

//    for preauthorization task comment below line and uncomment USAePayPreAuthorization
//        public static String WS_USAPAY_PAYMENT_TRANSACTION_DETAILS = "UsaPayPaymentTransactionDetails/";
//    Note: If card is saved then Pass MerchantContractID = 0 and if user enter new card or clear form then Pass MerchantContractID = 2  12345
    public static String WS_USAPAY_PAYMENT_TRANSACTION_DETAILS = "USAePayPreAuthorization/";
    public static String CHECK_GIFTCARD_BALANCE = "CheckGiftCardBalance";
//   Edited by Varun for multi pack new API
//     home page API
    public static String GET_INVENTORYBY_HOMEPAGEDATA="GetinventoryByHomePageData/";
    public static String GET_INVENTORYBY_HOMEPAGEDATA_NEW="GetinventoryByHomePageDataNew/";
    public static String GET_INVENTORYBY_NEWADDITION="GetinventoryByNewAddition/";
    public static String GET_INVENTORYBY_NEWADDITION_NEW="GetinventoryByNewAdditionNew/";
    public static String GET_INVENTORYBY_STAFFPICKS="GetinventoryByStaffPick/";
    public static String GET_INVENTORYBY_STAFFPICKS_NEW="GetinventoryByStaffPickNew/";
    public static String GET_RECENTLYVIEWEDITEMS="GetRecentlyViewedItems/";
    public static String GET_RECENTLYVIEWEDITEMS_V1="GetRecentlyViewedItems_V1/";
    public static String GET_CUSTOMERCARTDATA="GetCustomerCartData/";
    public static String GET_CUSTOMERCARTDATA_V1="GetCustomerCartData_V1/";
    public static String GET_CUSTOMER_CARD_DATA = "GetCustomerCartData/";
    public static String GET_CUSTOMER_CARD_DATA_V1 = "GetCustomerCartData_V1/";
    public static String GET_INVENTORYBY_SPECIALOFFER="GetinventoryBySpecialOffer/";
    public static String GET_INVENTORYBY_SPECIALOFFER_V1="GetinventoryBySpecialOffer_V1/";
    public static String GET_INVENTORY_PRAMOTION="GetinventoryByPromotion/";
    public static String GET_INVENTORY_PRAMOTION_AUTH_NEW="GetinventoryByPromotionAuthNew/";
    public static String GET_ALLINVENTORY="GetAllInventory/";
    public static String GET_ALLINVENTORY_V1="GetAllInventory_V1/";
//    View All API
    public static String GET_INVENTORYS="GetInventorys";
    public static String GET_INVENTORY_LIST_BY_PAGING_NEW="GetInventoryListByPaging_New";
//    Search Bar
    public static String GET_INVENTORY_BY_FILTER="GetInventorysbyfilter";
    public static String GET_INVENTORY_BY_FILTER_NEW="GetInventorysbyfilterNew";
//    Item Description page API
    public static String GET_INVERNTORY_BY_ID="GetInverntoryByID";
    public static String GET_INVERNTORY_BY_ID_NEW="GetInventoryByIDNew";
//    END
    public static String GET_DEPARTMETS="GetDepartments/";
    public static String GET_STYLE_DEPARTMENT_LIST="GetStyleDepartmentList/";
    public static String GET_RECOMMANDEDITEMS="GetRecommendeditems";
    public static String GET_BANNERDETAILS="GetBannerDetail/";
    public static String GET_UPDATE_CUSTOMER_CREDITCARDS="UpdateCustomerCreditCards/";
    public static String GET_USAPAY_UPDATE_CUSTOMER_CREDITCARDS="USAPayUpdateCustomerCreditCards/";
    public static String GET_SHIPPING_SERVICE_BY_STORENO="GetShippingServicesByStoreNo/";

    //native order history
    public static String GET_CANCEL_CUST_CREDITCARD="CancelCustCreditCard/";
    public static String GET_USAPAY_CANCEL_CUST_CREDITCARD="USAPayCancelCustCreditCard/";
    public static String GET_CANCEL_ORDER="CancelOrder/";
    public static String VoidTranscation="VoidTranscation/";
    public static String GETORDER_DETAILS="GetOrderDetails/";
    public static String USAPAYVoidTranscation="USAPayVoidTranscation/";
    public static String RequestResponse="RequestResponse/";
    public static String GET_LIGTHTNING_ORDER_DETAILS="getlightningOrderDetails/";
    public static String GetLightningOrdersDetails_Json="GetLightningOrdersDetails_Json?";
    public static String GET_DELIVERY_HOURS="GetDeliveryHours";
    public static String GET_ORDERHISTORY_DATA="GetOrderHistoryData/";
    public static String GET_RETURNPROCESS="Getreturnprocess/";

//    Delete Account webservice
    public static String GET_DELETE_CUSTOMER_ACCOUNT_FROM_ECOM="DeleteCustomerAccountFromEcom/";

//    SignUp Customer For Loyalty Reward From Ecom
    public static String SIGN_UP_CUSTOMER_FOR_LOYALTY_REWARD_FROM_ECOM="SignUpCustomerForLoyaltyRewardFromEcom/";



    public static String GET_HOME_PAGE_BLOCK_DATA="GetHomePageBlocksData/";
    public static String GET_INVENTORYBLOCK_DATAFORFRONT="GetBlockDataForFront/";
    public static String GET_TEMPLATE="GetTemplate/";
    public static String GET_NOTIFICATION_CuSTERMER="GetNotificationByCustomer/";
    public static String GET_ADVANCE_PAYMENT_OPTIONS="GetAdvancePaymentOptions";
    public static String GET_DEL_MIN_DATA="GetDelMinFeeData";
    public static String GET_DRINK_RECIPES ="GetDrinkRecipes";
    public static String GET_CONTACT_INFO ="GetContactInfo";
    public static String SEND_EMAIL_A_FRIEND ="SendEmailAFriend";
    public static String GET_ORDER_ITEM_DETAIL_DATA ="GetOrderItemDetailData";
    public static String GET_POS_CUSTOMER_ID ="GetPOSCustomerID";
    public static String GET_LIGHTNING_ORDERS ="getlightningOrders";
    public static String GET_SHIPPING_ADDRESS_BY_ID ="GetShippingAddressByID";
    public static String CALCULATE_SHIPPING_RATES_USPS ="CalculateShippingRatesUSPS";
    public static String CALCULATE_SHIPPING_RATES_FEDEX ="CalculateShippingRatesFedEx";
    public static String CALCULATE_SHIPPING_RATES_UPS ="CalculateShippingRatesUPS";

//    Edited by Varun for new shipping charges
    public static String CALCULATE_SHIPPING_RATES_USPS_V1 ="CalculateShippingRatesUSPS_V1";
    public static String CALCULATE_SHIPPING_RATES_FEDEX_V1 ="CalculateShippingRatesFedEx_V1";
    public static String CALCULATE_SHIPPING_RATES_UPS_V1 ="CalculateShippingRatesUPS_V1";
//    END

    public static String INSERT_FCM_TOKEN = "Home/InsertDeviceDetails?storeno=";
    public static String URL_PAGE_CONTACT_US = URL + "Pages/AndroidContact?storename=";
    public static String URL_PAGE_ABOUT_US = URL + "Pages/AndroidAboutUs?storename=" ;
    public static String URL_PAGE_BLOG = URL + "Pages/AndroidBlog?storename=";
    public static String URL_PAGE_FAQ = URL + "Pages/AndroidFAQ?storename=";
    public static String URL_PAGE_LEGAL = URL + "Pages/AndroidLegal?storename=" ;
    public static String URL_PAGE_PRIVACY = URL + "Pages/AndroidPrivacy?storename=";
    public static String URL_PAGE_PAYMENTS_REFUNDS = URL + "Pages/AndroidPaymentsRefunds?storename=";
    public static String URL_PAGE_SHIPPING = URL + "Pages/AndroidShipping?storename=" ;
    public static String URL_PAGE_SUPPORT = URL + "Pages/AndroidSupport?storename=";
    public static String URL_PAGE_TERMS = URL + "Pages/AndroidTerms?storename=";
    public static String URL_PAGE_HELP = URL + "Pages/AndroidHelp?storename=";
    public static String URL_PAGE_EVENTS = URL + "Pages/AndroidEvents?storename=";
    public static String URL_PAGE_STORE_HOURS = URL + "pages/storehours?storeno=";
    public static String URL_PAGE_STORE_HOURS_DELIVERY_HOURS = URL + "pages/storehours?storeno=" + STOREID + "&type=delivery";
    public static String URL_PAGE_MANAGE_ACCOUNT = URL + "Home/AndroidManageAccount";
    public static String URL_PAGE_ORDER_HISTORY = URL + "home/AndroidOrderHistory";
    public static String URL_PAGE_SHIPPING_ADDRESS = URL + "home/AndroidShippingAddress";
    public static String URL_PAGE_CREDIT_CARD = URL + "Home/AndroidCreditCard";
    public static String URL_TEST_HOME = URL + "Home/IndexApp";
    public static String URL_PAGE_CHANGE_PASSWORD = URL + "Home/AndroidCFhangePassword";
    public static String URL_PAGE_WISHLIST = URL + "BoxSalt/WishList/";
    public static String URL_PAGE_GIFTCARD = URL + "Pages/AndroidGiftCard?storename=";
    public static String URL_PAGE_JOBAPPLICATION = URL + "Pages/JobApplication?storename=";
    public static String URL_PAGE_REWARDS = URL + "Pages/AndroidRewards?storename=";

//    demo credentials
//    user: mark@computerperfect.com
//    passwd: demoacct

    //public static String URL_PAGE_EVENTS = URL + "Pages/AndroidEvents";

    //http://192.168.172.211:889/WebStoreRestService.svc/GenerateOTP/nidhi.modi@anantsoftech.com/SignInNew/707

    //http://192.168.172.211:889/WebStoreRestService.svc/GenerateOTP/kajal@gmail.com/1/707

    //http://192.168.172.211:889/WebStoreRestService.svc?InsertNewUserFromCart/vasant009@gmail.com/

    //WebAPI
    //http://192.168.172.211:889/WebStoreRestService.svc/CheckPassword/nidhi.modi@anantsoftech.com/12345678/707

    // http://192.168.172.211:889/WebStoreRestService.svc/GetEcomTheam/{storeno}

    //imageBase URL
    //http://192.168.172.211:888/img/logo/707/636132761918877009.png

    //WebStoreImages/logo/

    //public static String IMG = "img/";
    public static String IMG = "WebStoreImages/";
    public static String LOGO = "logo/";
    public static String IMG_BASEURL = IMG_BASE + IMG;

    //public static String HOME="Home/StoreIndex";
    public static String CONTACTUS = "BoxSalt/ContactUs";
    public static String CART = "BoxSalt/Cart";
    public static String EVENT_CALENDER = "Pages/Events";

    /*Class Variable declaration*/
    public static LinkedHashMap<String, List<MbsDataModel>> LHSLIDER_LIST = new LinkedHashMap<String, List<MbsDataModel>>();
    public static List<MbsDataModel> SORTLIST = new ArrayList<MbsDataModel>();
    public static List<MbsDataModel> DEPARTMENTLIST = new ArrayList<MbsDataModel>();
    public static List<Async_getSubDept> subDeptList = new ArrayList<Async_getSubDept>();

    public static int GROUP_POSITION = 0;

    public static ArrayList<FilterModel> FILTERlIST = new ArrayList<>();
    public static int CURRENT_POSITION = 0;
    //Common Classes for Application
    public static ThemeModel themeModel = new ThemeModel();
    //  public static UserModel userModel = new UserModel();
    public static TwentyOneYear twentyOneYear = new TwentyOneYear();
    public static ContatInfo contatInfo = new ContatInfo();
    /* public static ArrayList<FooterModel> FooterList=new ArrayList<>();*/
    public static ArrayList<DepartmentModel> DepartmentList = new ArrayList<>();
    public static List<JackDepartmentModel> DepartmentList1;
    public static ArrayList<SubDepartmentModel> SubDepartmentList = new ArrayList<>();
    public static ArrayList<CardModel> cardArray = new ArrayList<>();
    public static List<StoreHour> liStoreHour = new ArrayList<>();
    public static List<StoreHour> liOnlyStoreHour = new ArrayList<>();
    public static List<StoreLocationModel> storeLocationList = new ArrayList<>();
//    Edited by Varun for lockdown feature
    public static List<StoreLocationModel> storeLocationList2 = new ArrayList<>();
    public static List<StoreLocationModel> storeLocationListfinal = new ArrayList<>();
    public static List<StoreLocationModel> storeLocationListtemp = new ArrayList<>();
    public static Boolean isSearchLocation  = false;
//    END
    public static List<StoreLocationModel> storeSearchLocationList = new ArrayList<>();
    public static List<DeleteAccountModel> deleteAccountModel = new ArrayList<>();

    public static PlaceModel[] PlaceArr = new PlaceModel[0];

    public static OtpModel otpModel;
    public static UserModel UserModeltest;
    public static SharedPreferences AppPref = null;
    public static String PrefName = "MBSPref";

    public static ArrayList<MbsDataModel> AccountList = new ArrayList<>();
    public static ArrayList<MbsDataModel> AccountList2 = new ArrayList<>();
    public static List<MbsDataModel> FooterList = new ArrayList<>();
    public static List<MbsDataModel> Otherlist = new ArrayList<>();
    public static List<MbsDataModel> FooterModelList = new ArrayList<>();
    public static List<ShoppingCardModel> liCardModel = new ArrayList<>();
    public static ShippingData customerData = new ShippingData();

    public static String LOGIN_TYPE = "";

    public static String Email = "";
    public static String Guest_Email = "";
    public static String Password = "";
    public static String Guest_storeno = "";
    public static String Guest_customer_id = "";
    public static String Guest_WD = "";
    public static Boolean ISguest  = false;
    public static Boolean ISguest_Signin  = false;
    public static Boolean ISguest_Continue  = false;
    //Signin
    //Signinnew
    public static boolean BAR_IMG_DISP=false;

    /*
     *
     * ScreenLayout
     * 1
     * 2
     * 3
     *
     *
     * */
    public static int SCREEN_LAYOUT=1;
    /*
     * Layout two
     * */
    public static List<MbsDataModel> TopHeaderMenuList = new ArrayList<>();
    public static List<NotificationModel> NOTIFICATION_LIST = new ArrayList<>();

    //https://ecomsecure.lightningpos.com/Pages/AndroidAboutUs?storename=707

    public static boolean ISDELIVERY_HOUR_DISP;
    public static boolean ISDISPLAY_STOREHOUR;
    public static boolean ISSHOW_EVENTCAL;
    public static String storeDeliveryHourTagline = "";
    public static boolean isNativePage=true;
    public static List<DataFrontModel> BLOCKDATAFRONTLIST=new ArrayList<>();
    public static  List<DataHomePageBlockModel> DATAHOMEPAGEBLOCK = new ArrayList<>();
    public static boolean Detail_webview_Url=false;//by janvi
    public static boolean isviewall_page = false;//by janvi
    public static boolean isCart=false;//by janvi
    public static List<HomeItemModel> ItemOnPromotionList = new ArrayList<HomeItemModel>();
    public static ArrayList<String> paymentOptionsList = new ArrayList<>();
    public static float finalSalesTax= 0.0f;
    public static boolean isFromMic = false;
    public static boolean isFromMicSeclayout = false;
    public static boolean isDialogShow = false;
    public static boolean isDialogShowSeclayout = false;

    public static Integer clickedDeptIdfromhome = 0;
    public static List<ReOrderItemModel> selectedReorderList = new ArrayList<>();
//    public static ArrayList<String> userNameList = new ArrayList<>();
    public static ShippingData liShippingData;
    public static boolean isbackFromPayment = false;
    public static boolean storeStatus = false;
    public static String Store_Delivery_Logo = "";
    public static boolean isBackFromDeparment = false;
    public static String invType = "";
    public static String co_storeno_value = "";

    public static ArrayList<StoreclosesModel> StoreCloseList = new ArrayList<com.aspl.mbsmodel.StoreclosesModel>();
    public static String favstorelocation = "";

    public static boolean isFromChangeLocDialog = false;
    public static OrderSummary orderSummaryTemp = null;
    public static List<InstorePurchaseDetailModel> instorePurchase_detailList = new ArrayList<>();
    public static String lastSelectedOrderDetailParent = "";
    public static String buttonclicked_InstoreDetail = "";
    public static float FinalTax1= 0.0f;
    public static float FinalTax2= 0.0f;
    public static float FinalTax3= 0.0f;
    public static float FinalTax4= 0.0f;

    public static boolean isclickedFavLocation = false;
    public static boolean isclickedwishlistFromViewall = false;
    public static boolean isclickedEditShipping = false;
    public static boolean isfromRealTime = false;
    public static boolean isloadWebview = false;
//    public static boolean isopenpopup = false;
    public static int selectedItemPos_UPSSpinner = 0;
    public static int selectedItemPos_FedexSpinner = 0;
    public static int selectedItemPos_USPSSpinner = 0;

    public static List<EditShippingData> liShippingEditData = new ArrayList<>();
    public static List<ShoppingCardModel> forRealtimeList = new ArrayList<>();

    //**************** Edited by Varun************
    public static String URL_PAGE_ACCESSIBILITY = URL + "Pages/AndroidAccessibility?storename=";

    public static String TOKEN_ID = "Shapath2_706$@$";
    public static Float _lPoints = 0.0f;
    public static boolean filter_click = false;
    public static boolean IsComeFromSplash = false;

    @SuppressLint("NewApi")
    static byte[] data = Constant.TOKEN_ID.getBytes(StandardCharsets.UTF_8);
    public static String ENCODE_TOKEN_ID ="/"+ Base64.encodeToString(data, Base64.DEFAULT);

//     If transcation  is approve with UASePAY, This API should be called after successful transaction complete and TokenID on Base64encode format.
//     If User select Save card check box then only called this API.
    public static String SAVE_CUSTOMER_DATA_IN_USAEPAY_VAULT="SaveCustomerDataInUSAePayVault/";
//    to get card details for UASePAY
    public static String GET_CUSTOMER_DATA_FROM_USAEPAY_VAULT="GetCustomerDataFromUSAePayVault/";
    public static String UPADATE_CUSTMOER_IN_USAEPAY_VAULT="UpdateCustomerInUSAePayVault/";
    public static String USAPAY_DELETE_CUST_CREDITCARD="USAPayDeleteCustCreditCard/";
//     to check the status of the USAePAY
    public static String GET_USAEPAY_ACTIVE_STATUS="GetUSAePayActiveStatus/";

    public static boolean isUSAePAY = false;

    public static boolean pick_up = false;
    public static boolean hand_delivery = false;
    public static boolean x = false;
    public static boolean check = false;

    public static String BZip="";
    public static String BAddressOne="";
    public static String BState="";
    public static String BCity="";
    public static String Custormer_Id="";
    public static String additional_charges="";
    public static String Country_code="";
    public static String Selected_ID="";
    public static String Service_name="";


    public static String Zip="";
    public static String AddressOne="";
    public static String State="";
    public static String City="";


    public static boolean isCHECKOUT = false;
    public static Float currentPromoPrice= 0.0f;
    public static Float currentPrice=0.0f;
    public static Float pPromoPrice=0.0f;
    public static Float pPrice=0.0f;


    public static List<ShoppingCardModel> Test = new ArrayList<ShoppingCardModel>();

    public static int check_multi_position = -1;
    public static String Test_SKU="";
    public static int Test_Ounces = 0;
    public static String Card_Fargment_Inv_type = "";

    public static ItemDescModel TestitemDescModel = new ItemDescModel();

    public static Dialog dialog_Delete_My_Online_Account;


//    Edited by Varun for pick up hours and location
    public static Boolean rbPayAtStore  = false;
    public static Boolean rbPickUpAtStore  = false;
    public static Boolean iscomefromSignuployalty  = false;

    public static String today="";
    public static String tomorrow="";
    public static String pick_up_time="";


    public static String LockStoreName="";
    public static String LockStoreAddress="";
    public static String LockCity="";
    public static String LockState="";
    public static String LockZip="";
    public static String PhoneNumber="";

    public static Boolean isloyaltyreward  = false;
    // *************END *************

    public static int cartcount = 0;

}

