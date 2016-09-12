package eNews.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import eNews.app.R;
import eNews.bean.CollectModel;
import eNews.bean.NewsDetailModel;
import eNews.bean.NewsModel;
import eNews.common.DataBaseHelper;
import eNews.customview.MorePopupWindow;
import eNews.dao.CollectManage;
import eNews.getContent.GetNewsDetailContent;
import eNews.thirdParty.AppConstant;
import eNews.thirdParty.TencentThirdParty;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 详细新闻activity
 */
public class NewsDetailActivity extends Activity implements OnClickListener,
		CollectNewsInterface {
	ProgressDialog dialog;
	private TextView newsDetailText;
	private String openId;

	private NewsDetailModel newsDetailModel;
	private ArrayList<Drawable> arrayDrawable;
	private ImageButton backBtn;
	private int windowWidth;
	String strList[];
	private ImageButton actionbar_more;
	private MorePopupWindow morePopupWindow;
	private NewsModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setOpenId(TencentThirdParty.getInstance(getApplicationContext())
				.getOpenId());
		getActionBar().hide();
		setContentView(R.layout.news_detail);

		actionbar_more = (ImageButton) findViewById(R.id.actionbar_more);
		actionbar_more.setOnClickListener(this);
		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		windowWidth = outMetrics.widthPixels;
		backBtn = (ImageButton) findViewById(R.id.backBtn);

		morePopupWindow = new MorePopupWindow(this);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});

		dialog = ProgressDialog.show(this, "提示", "正在加载,点击可返回");
		dialog.setCancelable(true);
		dialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		init();

	}

	private void init() {

		newsDetailText = (TextView) findViewById(R.id.news_detail_text);
		model = (NewsModel) getIntent().getSerializableExtra("model");

		arrayDrawable = new ArrayList<Drawable>();

		GetNewsDetailContent.getNewsContent(model.getDocid(), this);

	}

	public void setContent(NewsDetailModel newsDetailModel) {
		this.newsDetailModel = newsDetailModel;
		strList = newsDetailModel.getBody().split("<!--IMG#\\d*-->");
		// System.out.println("setContent");
		getDetailImages();
	}

	private void getDetailImages() {

		RequestQueue jr = Volley.newRequestQueue(getApplicationContext());
		// System.out.println("getDetailImages");

		if (newsDetailModel.getImg().size() == 0) {
			setNewsDetailText();
		} else
			for (int i = 0; i < newsDetailModel.getImg().size(); i++) {

				System.out.println(newsDetailModel.getImg().get(i));
				ImageRequest ir = new ImageRequest(newsDetailModel.getImg()
						.get(i), new ImageRequestListener(), 300, 300,
						Config.ARGB_8888, new ImageRequestError());

				jr.add(ir);
			}

	}

	private void setNewsDetailText() {

		System.out.println(strList.length + "<----->" + arrayDrawable.size());
		for (int i = 0; i < strList.length; i++) {
			if (i < strList.length - 1)
				newsDetailText.append(Html.fromHtml(strList[i] + "<img src="
						+ String.valueOf(i) + " ></img>", new MyImageGetter(),
						null));
			else
				newsDetailText.append(Html.fromHtml(strList[i]));

		}
		if (dialog.isShowing())
			dialog.dismiss();
	}

	class MyImageGetter implements ImageGetter {

		@Override
		public Drawable getDrawable(String position) {
			// TODO Auto-generated method stub

			// System.out.println("position->"+position);
			return arrayDrawable.get(Integer.valueOf(position));
		}

	}

	public class ImageRequestListener implements Listener<Bitmap> {

		@Override
		public void onResponse(Bitmap bitmap) {
			// TODO Auto-generated method stub
			Drawable drawable = new BitmapDrawable(getResources(), bitmap);

			int height = drawable.getIntrinsicHeight();
			int width = drawable.getIntrinsicWidth();
			double scale = (windowWidth - 40) / width;
			width = windowWidth - 40;
			height = (int) scale * height;
			drawable.setBounds(0, 0, width, height);
			drawable.setBounds(0, 0, width, height);

			arrayDrawable.add(drawable);

			if (arrayDrawable.size() == newsDetailModel.getImg().size()) {
				System.out.println("符合条件 ->arrayDrawable ->"
						+ arrayDrawable.size() + " getImg().size()-1->"
						+ (newsDetailModel.getImg().size() - 1));
				setNewsDetailText();
			}

		}

	}

	class ImageRequestError implements ErrorListener {

		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub

			System.out.println("ErrorListener " + arg0.toString());
			if (dialog.isShowing())
				dialog.dismiss();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.shareLy:
			System.out.println("点击了shareLy");
			if (morePopupWindow.isShowing()) {

				if (model != null) {

					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(AppConstant.logoUrl);

					TencentThirdParty.getInstance(this).shareToQzone(this,
							model.getTitle(), model.getDigest(),
							model.getUrl_3w(), arrayList);
				}

				morePopupWindow.dismiss();
			}
			break;
		case R.id.shareLyqq:

			System.out.println("点击了shareLyQQ");
			if (morePopupWindow.isShowing()) {

				if (model != null) {

					TencentThirdParty.getInstance(this).shareToQQ(this,
							model.getTitle(), model.getDigest(),
							model.getUrl_3w(), AppConstant.logoUrl);
				}

				morePopupWindow.dismiss();
			}

			break;

		case R.id.collectLy:
			System.out.println("点击了collectLy");
			collectNews();

			break;
		case R.id.actionbar_more:
			System.out.println("点击了actionbar_more");
			if (!morePopupWindow.isShowing()) {
				morePopupWindow.showAsDropDown(actionbar_more);
				System.out.println("popupwindow");
			}
			break;
		}

	}

	private void collectNews() {

		if (morePopupWindow.isShowing()) {
			morePopupWindow.dismiss();

			if (TencentThirdParty.getInstance(getApplicationContext())
					.checkIsLogged()) {

				collect();

			} else {
				new AlertDialog.Builder(this).setTitle("请先登录")
						.setPositiveButton("登录", new NegativeButtonListener())
						.setNegativeButton("再等等", new PositiveButtonListener())
						.setIcon(android.R.drawable.ic_dialog_info).show();
			}

		}
	}

	// 取消
	public class PositiveButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

		}
	}

	// 登录
	public class NegativeButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

			TencentThirdParty.getInstance(getApplicationContext()).userLogin(
					NewsDetailActivity.this);

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == Constants.REQUEST_LOGIN
				|| requestCode == Constants.REQUEST_APPBAR) {
			Tencent.onActivityResultData(
					requestCode,
					resultCode,
					data,
					TencentThirdParty.getInstance(getApplicationContext()).loginIUListener);
		}
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public void collectNewsAfterLogin(String openId) {
		// TODO Auto-generated method stub

		setOpenId(openId);
		collect();

	}

	private void collect() {
		CollectManage manage = CollectManage.getInstance(this);
		CollectModel collectModel = new CollectModel();

		collectModel.setUserId(getOpenId());
		collectModel.setDesc(model.getDigest());
		collectModel.setId(model.getDocid());
		collectModel.setTitle(model.getTitle());
		collectModel.setType(DataBaseHelper.TEXT);
		collectModel.setImgurl(model.getImagesrc());
		manage.insertCollect(collectModel);

		Toast.makeText(getApplicationContext(), "收藏成功!!!", Toast.LENGTH_SHORT)
				.show();
	}
}
