package eNews.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import eNews.app.R;
import eNews.bean.CollectModel;
import eNews.bean.NewsDetailModel;
import eNews.bean.PictureModel;
import eNews.common.DataBaseHelper;
import eNews.customview.MorePopupWindow;
import eNews.dao.CollectManage;
import eNews.getContent.GetMeiTuDetailContent;
import eNews.thirdParty.AppConstant;
import eNews.thirdParty.TencentThirdParty;

/**
 * 
 * @author ����
 * @date 2016-9-12 ��ͼactivity
 */
public class MeiTuDetailActivity extends Activity implements OnClickListener,
		CollectNewsInterface {

	private TextView newsDetailText;
	private NewsDetailModel newsDetailModel;
	private ArrayList<Drawable> arrayDrawable;
	private ImageButton backBtn;
	private int windowWidth;
	ProgressDialog dialog;
	private MorePopupWindow morePopupWindow;
	private ImageButton actionbar_more;
	String strList[];
	private PictureModel model;
	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setOpenId(TencentThirdParty.getInstance(getApplicationContext())
				.getOpenId());
		setContentView(R.layout.news_detail);
		getActionBar().hide();
		morePopupWindow = new MorePopupWindow(this);
		actionbar_more = (ImageButton) findViewById(R.id.actionbar_more);
		actionbar_more.setOnClickListener(this);

		TextView titleTv = (TextView) findViewById(R.id.title);
		titleTv.setText("ͼƬ����");

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		windowWidth = outMetrics.widthPixels;

		backBtn = (ImageButton) findViewById(R.id.backBtn);

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});
		init();

	}

	private void init() {

		dialog = ProgressDialog.show(this, "��ʾ", "���ڼ���,����ɷ���");
		dialog.setCancelable(true);
		dialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				finish();

			}
		});
		newsDetailText = (TextView) findViewById(R.id.news_detail_text);
		model = (PictureModel) getIntent().getSerializableExtra("picModel");
		arrayDrawable = new ArrayList<Drawable>();

		GetMeiTuDetailContent.getNewsContent(model.getDocid(), this);
	}

	// �������
	public void setContent(NewsDetailModel newsDetailModel) {
		this.newsDetailModel = newsDetailModel;
		String body = newsDetailModel.getBody() + " ʱ�� "
				+ newsDetailModel.getPtime();
		strList = body.split("<!--IMG#\\d*-->");
		System.out.println("setContent");
		getDetailImages();
	}

	// ��ȡ����ͼƬ
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

	// ��ʾ��Ϣ
	private void setNewsDetailText() {

		System.out.println(strList.length + "<----->" + arrayDrawable.size());
		for (int i = 0; i < strList.length; i++) {
			if (i < strList.length - 1)
				newsDetailText.append(Html.fromHtml(strList[i] + "<img src="
						+ String.valueOf(i) + "></img><br>",
						new MyImageGetter(), null));
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
				// System.out.println("�������� ->arrayDrawable ->"
				// + arrayDrawable.size() + " getImg().size()-1->"
				// + (newsDetailModel.getImg().size() - 1));
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
			System.out.println("�����shareLy");
			if (morePopupWindow.isShowing()) {

				if (newsDetailModel != null) {

					ArrayList<String> arrayList = new ArrayList<String>();
					arrayList.add(AppConstant.logoUrl);

					TencentThirdParty.getInstance(this).shareToQzone(this,
							newsDetailModel.getTitle(),
							newsDetailModel.getTitle(),
							newsDetailModel.getShareLink(), arrayList);
				}

				morePopupWindow.dismiss();
			}
			break;

		case R.id.shareLyqq:

			System.out.println("�����shareLyQQ");
			if (morePopupWindow.isShowing()) {

				if (model != null) {

					TencentThirdParty.getInstance(this)
							.shareToQQ(this, newsDetailModel.getTitle(),
									newsDetailModel.getTitle(),
									newsDetailModel.getShareLink(),
									AppConstant.logoUrl);
				}

				morePopupWindow.dismiss();
			}

			break;

		case R.id.collectLy:
			System.out.println("�����collectLy");
			collectNews();
			break;
		case R.id.actionbar_more:
			System.out.println("�����actionbar_more");
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
				new AlertDialog.Builder(this).setTitle("���ȵ�¼")
						.setPositiveButton("��¼", new NegativeButtonListener())
						.setNegativeButton("�ٵȵ�", new PositiveButtonListener())
						.setIcon(android.R.drawable.ic_dialog_info).show();
			}

		}
	}

	// ȡ��
	public class PositiveButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

		}
	}

	// ��¼
	public class NegativeButtonListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

			TencentThirdParty.getInstance(getApplicationContext()).userLogin(
					MeiTuDetailActivity.this);

		}

	}

	@Override
	public void collectNewsAfterLogin(String openId) {
		// TODO Auto-generated method stub

		setOpenId(openId);

		collect();

	}

	private void collect() {
		// TODO Auto-generated method stub
		CollectManage manage = CollectManage.getInstance(this);
		CollectModel collectModel = new CollectModel();

		collectModel.setUserId(getOpenId());
		collectModel.setDesc(model.getDesc());
		collectModel.setTitle(model.getTitle());
		collectModel.setType(DataBaseHelper.MEITU);
		collectModel.setImgurl(model.getImgsrc());
		collectModel.setId(model.getDocid());
		manage.insertCollect(collectModel);

		Toast.makeText(getApplicationContext(), "�ղسɹ�!!!", Toast.LENGTH_SHORT)
				.show();

	}

}
