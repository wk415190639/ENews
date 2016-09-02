package eNews.common;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache {

	private LruCache<String, Bitmap> mCache;

	private static BitmapCache bitmapCache;

	private BitmapCache() {
		// TODO Auto-generated constructor stub
		int maxSize = (int) Runtime.getRuntime().maxMemory();
		mCache = new LruCache<String, Bitmap>(maxSize/6) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight();
			}

		};

	}

	static public BitmapCache instance() {
		if (bitmapCache == null)
			bitmapCache = new BitmapCache();

		return bitmapCache;
	}

	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
	
		
		mCache.put(url, bitmap);

	}

}
