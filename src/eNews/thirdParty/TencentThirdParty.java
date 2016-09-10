package eNews.thirdParty;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class TencentThirdParty {

	private static TencentThirdParty instance;
	private static Tencent mTencent;

	private TencentThirdParty(Context context) {
		mTencent = Tencent.createInstance(AppConstant.appId, context);
	}

	public static  TencentThirdParty getInstance(Context context) {

		if (instance == null)
			instance = new TencentThirdParty(context);

		return instance;
	}

	public  Tencent getTencentInstance() {

		return mTencent;
	}

	public void shareToQzone(Activity activity, final String title,
			final String summary, final String targetUrl,
			final ArrayList<String> imageUrlList/* , IUiListener shareUiListener */) {

		Bundle params = new Bundle();
		// 分享类型
		params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,
				QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);// 必填

		params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "新闻e眼客户端(第三方分享测试)->" + title);// 必填

		params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, targetUrl);// 必填

		params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, summary); // 选填

		params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL,
				imageUrlList); // 选填

		mTencent.shareToQzone(activity, params, new ShareUiListener());
		System.out.println("分享了");

	}

	class ShareUiListener implements IUiListener {

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			System.out.println("-------------onCancel");
		}

		@Override
		public void onComplete(Object arg0) {
			// TODO Auto-generated method stub
			System.out.println("------------------onComplete");

		}

		@Override
		public void onError(UiError error) {

			System.out.println("-----error >" + error.errorCode + " > "
					+ error.errorDetail + " > " + error.errorMessage);

		}

	}

}
