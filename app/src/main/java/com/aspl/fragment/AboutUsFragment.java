package com.aspl.fragment;


import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspl.Utils.Constant;
import com.aspl.Utils.ObservableWebView;
import com.aspl.Utils.Utils;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.AboutusModel;
import com.aspl.task.TaskAbout;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment implements TaskAbout.TaskAboutEvent {

    public static final String TAG = "Aboutus";
    public static AboutUsFragment aboutUsFragment;
    TextView tv_aboutus_title,tv_aboutus_detail;
    LinearLayout ll_root_aboutuslayout;
    NestedScrollView nested_aboutus;
    ObservableWebView Container;

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        aboutUsFragment = this;
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.SCREEN_LAYOUT == 2){
            MainActivityDup.getInstance().RecHorizontalList.setVisibility(View.GONE);
            MainActivityDup.getInstance().showbackButton();
        }

        if (Constant.SCREEN_LAYOUT == 1) {

            MainActivity.getInstance().llsearch.setVisibility(View.VISIBLE);

            if(Constant.isFromMic){
                MainActivity.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivity.getInstance().mSearchedt.requestFocus();
                MainActivity.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMic = false;
            }else{
                MainActivity.getInstance().mSearchedt.clearFocus();
                Utils.hideKeyboard();
            }

        }else if (Constant.SCREEN_LAYOUT == 2) {

            MainActivityDup.getInstance().llsearch.setVisibility(View.GONE);

            if(Constant.isFromMicSeclayout){
                MainActivityDup.getInstance().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                MainActivityDup.getInstance().mSearchedt.requestFocus();
                MainActivityDup.getInstance().mSearchedt.setFocusableInTouchMode(true);
                Constant.isFromMicSeclayout = false;
            }else{
                MainActivityDup.getInstance().mSearchedt.clearFocus();
                Utils.hideKeyboard();
            }
        }

        Utils.hideKeyboard();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nested_aboutus = view.findViewById(R.id.nested_aboutus);
        nested_aboutus.setBackgroundColor(Color.parseColor(Constant.themeModel.Backgroundcolor));
        ll_root_aboutuslayout = view.findViewById(R.id.ll_root_aboutuslayout);
        tv_aboutus_title = (TextView) view.findViewById(R.id.tv_aboutus_title);
        tv_aboutus_title.setTextColor(Color.parseColor(Constant.themeModel.ThemeColor));
        tv_aboutus_detail = (TextView) view.findViewById(R.id.tv_aboutus_detail);

        Container = (ObservableWebView) view.findViewById(R.id.Container);

//        Container.clearHistory();
//        if (isAdded()) {
//            CookieSyncManager.createInstance(getActivity());
//            CookieManager cookieManager = CookieManager.getInstance();
//            cookieManager.removeAllCookie();
//        }

//        Container.clearView();
//        Container.setVisibility(View.VISIBLE);
//        WebSettings settings = Container.getSettings();
//        settings.setJavaScriptEnabled(true);
//        Container.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        Container.setWebChromeClient(new WebChromeClient());
//        Container.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        if (Build.VERSION.SDK_INT >= 19) {
//            Container.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else {
//            Container.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }

        loadAboutusWSdata();
    }

    private void loadAboutusWSdata() {
        String url = null;
//        if (UserModel.Cust_mst_ID != null) {
        //URL :- http://192.168.172.211:889/WebStoreRestService.svc/GetCustomerCartData/188856/wishlist/707
        url = Constant.WS_BASE_URL + Constant.GET_ABOUT_US + Constant.STOREID;
        TaskAbout taskReward = new TaskAbout(AboutUsFragment.this,getContext());
        taskReward.execute(url);
//        }
    }


    @Override
    public void onAboutResult(AboutusModel aboutusModel) {
        if(aboutusModel != null){
            if(aboutusModel.getPageContent()!=null && !aboutusModel.getPageContent().equals("")){
//                if(tv_aboutus_detail.getVisibility() != View.VISIBLE){
//                    tv_aboutus_detail.setVisibility(View.VISIBLE);
//                    tv_aboutus_detail.setText(Html.fromHtml(aboutusModel.getPageContent()));
//                }

                //corrected code start
                Container.loadDataWithBaseURL(null,aboutusModel.getPageContent(),"text/html","UTF-8","about:blank");
                //corrected code end

//                Spanned htmlAsSpanned = Html.fromHtml(aboutusModel.getPageContent());
//                tv_aboutus_detail.setText(htmlAsSpanned);

//                tv_aboutus_detail.setText(Html.fromHtml(aboutusModel.getPageContent()),null);

//                tv_aboutus_detail.setText(Html.fromHtml(aboutusModel.getPageContent(), new URLImageParser(tv_aboutus_detail, getActivity()), null));

//                Container.loadDataWithBaseURL(null, aboutusModel.getPageContent(), "text/html", "utf-8", null);

//                URLImageParser p = new URLImageParser(tv_aboutus_detail, getActivity());
//                Spanned htmlSpan = Html.fromHtml(aboutusModel.getPageContent(), p, null);
//                tv_aboutus_detail.setText(htmlSpan);

//                tv_aboutus_detail.setText(Html.fromHtml(aboutusModel.getPageContent()));

//                tv_aboutus_detail.setText(Html.fromHtml(aboutusModel.getPageContent(), imgGetter, null));

//                    Container.loadData(aboutusModel.getPageContent(), "text/html", "UTF-8");
//                if (aboutusModel.getPageContent() != null && aboutusModel.getPageContent() != null) {
//
//////                        writeToFile(aboutusModel.getPageContent(),getActivity());
//
////                    AssetManager mgr = getContext().getAssets();
////                    try {
////                        InputStream in = mgr.open(FileName,AssetManager.ACCESS_BUFFER);
////
////                        String sHTML = streamToString(in);
////                        in.close();
////
//////                        webviewAboutus.loadData(splitStringEvery("file:///android_asset/", sHTML, "text/html", "utf-8", null);
////
////                    } catch (IOException e) {
////                        // TODO Auto-generated catch block
////                        e.printStackTrace();
////                    }
////                    webviewAboutus.loadData(splitStringEvery(aboutusModel.getPageContent(),50), null, null);
//                Container.loadDataWithBaseURL(null,aboutusModel.getPageContent(),"text/html","UTF-8","about:blank");
//                }
            }
        }
    }

//    public static String StreamToString(InputStream in) throws IOException {
//        if(in == null) {
//            return "";
//        }
//
//        Writer writer = new StringWriter();
//        char[] buffer = new char[1024];
//
//        try {
//            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//
//            int n;
//            while ((n = reader.read(buffer)) != -1) {
//                writer.write(buffer, 0, n);
//            }
//
//        } finally {
//
//        }
//
//        return writer.toString();
//    }
//
//    public String[] splitStringEvery(String s, int interval) {
//        int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
//        String[] result = new String[arrayLength];
//
//        int j = 0;
//        int lastIndex = result.length - 1;
//        for (int i = 0; i < lastIndex; i++) {
//            result[i] = s.substring(j, j + interval);
//            j += interval;
//        } //Add the last bit
//        result[lastIndex] = s.substring(j);
//
//        return result;
//    }
//
//    private void writeToFile(String data, Context context) {
//
//        String fileName = "aboutus.txt";
//        // Store
//        try {
//            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
//            file.write(data);
//            file.flush();
//            file.close();
//        } catch (IOException e) {
//            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
//        }
//
//    }
//
//    private String readFromFile(Context context) {
//
//        String fileName = "aboutus.txt";
//
//// Retrieve
//            try {
//                File f = new File(context.getFilesDir().getPath() + "/" + fileName);
//                //check whether file exists
//                FileInputStream is = new FileInputStream(f);
//                int size = is.available();
//                byte[] buffer = new byte[size];
//                is.read(buffer);
//                is.close();
//                return new String(buffer);
//            } catch (IOException e) {
//                Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
//                return null;
//            }
//    }

}
