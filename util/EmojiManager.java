//package com.eb.dianlianbao_server.util;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class EmojiManager {
//	private static final String TAG = "EmojiManager";
//	  private static final int UNZIP_SUCCESS = 11111;
//	  private final Map<Pattern, Emoji> emoticons = new HashMap<>();
//	  private HashMap<Object, Drawable> drawableCacheMap = new HashMap<>();
//
//	  private volatile static EmojiManager instance;
//	  private static List<File> emojiGifs = new ArrayList<>();
//	  private DefaultGifEmoji[] defaultGifEmojis;
//	  private boolean defaultEmojiInited = false;
//	  private Handler handler;
//	  private List<OnUnzipSuccessListener> onUnzipSuccessListeners = new ArrayList<>();
//
//	  private static String[] emojiList = new String[]{
//	      "[微笑]", "[伤心]", "[美女]", "[发呆]", "[墨镜]", "[哭]", "[羞]",
//	      "[哑]", "[睡]", "[大哭]", "[囧]", "[怒]", "[调皮]", "[笑]",
//	      "[惊讶]", "[难过]", "[酷]", "[汗]", "[抓狂]", "[吐]",
//
//	      "[偷笑]", "[快乐]", "[奇]", "[傲]", "[饿]", "[累]", "[惊吓]",
//	      "[流汗]", "[高兴]", "[闲]", "[努力]", "[骂]", "[疑问]", "[秘密]",
//	      "[乱]", "[疯]", "[衰]", "[鬼]", "[打击]", "[bye]",
//
//	      "[擦汗]", "[抠]", "[鼓掌]", "[糟糕]", "[恶搞]", "[左哼哼]", "[右哼哼]",
//	      "[哈欠]", "[看]", "[委屈]", "[快哭了]", "[坏]", "[亲]", "[吓]",
//	      "[可怜]", "[刀]", "[水果]", "[酒]", "[篮球]", "[乒乓]",
//
//	      "[咖啡]", "[美食]", "[动物]", "[鲜花]", "[枯]", "[唇]", "[爱]",
//	      "[分手]", "[生日]", "[电]", "[炸弹]", "[刀子]", "[足球]", "[瓢虫]",
//	      "[翔]", "[月亮]", "[太阳]", "[礼物]", "[抱抱]", "[拇指]",
//
//	      "[贬低]", "[握手]", "[剪刀手]", "[抱拳]", "[勾引]", "[拳头]", "[小拇指]",
//	      "[拇指八]", "[食指]", "[ok]", "[情侣]", "[爱心]", "[蹦哒]", "[颤抖]",
//	      "[怄气]", "[跳舞]", "[企鹅]", "[背着]", "[伸手]", "[耍帅]"
//	  };
//
//
//	  private EmojiManager() {
//	    handler = new Handler(new Callback() {
//	      @Override
//	      public boolean handleMessage(Message msg) {
//	        if (msg.what == UNZIP_SUCCESS) {
//	          onUnzipSuccess();
//	          return true;
//	        }
//	        return false;
//	      }
//	    });
//	  }
//
//
//	  public static EmojiManager getInstance() {
//	    if (instance == null) {
//	      synchronized (EmojiManager.class) {
//	        if (instance == null) {
//	          instance = new EmojiManager();
//	        }
//	      }
//	    }
//	    return instance;
//	  }
//
//	  public void getDefaultEmojiData(OnUnzipSuccessListener unzipSuccessListener) {
//	    if (defaultEmojiInited) {
//	      unzipSuccessListener.onUnzipSuccess(defaultGifEmojis);
//	    } else {
//	      onUnzipSuccessListeners.add(unzipSuccessListener);
//	    }
//	  }
//
//	  public void init(final Context context) {
//	    boolean unziped = PreferenceUtil.getEmojiInitResult(context);
//	    if (unziped) {
//	      initDefault(context);
//	    } else {
//	      new Thread(new Runnable() {
//	        @Override
//	        public void run() {
//	          boolean unzipResult = FileUtil .unzipFromAssets(context, FileUtil.getEmojiDir(context), "emoji");
//	          PreferenceUtil.setEmojiInitResult(context, unzipResult);
//	          if (unzipResult) {
//	            initDefault(context);
//	          }
//	        }
//	      }).start();
//	    }
//	  }
//
//	  private void initDefault(Context context) {
//	    for (int i = 1; i < 6; i++) {
//	      for (int j = 0; j < 3; j++) {
//	        for (int k = 0; k < 7; k++) {
//	          if (j == 2 && k == 6) {
//	            continue;
//	          }
//	          File gifFile;
//	          gifFile = new File(FileUtil.getEmojiDir(context),
//	              "/emoji/e" + i + "/" + k + "_" + j + ".gif");
//	          emojiGifs.add(gifFile);
//	        }
//	      }
//	    }
//	    for (int i = 0; i < emojiList.length; i++) {
//	      emoticons.put(Pattern.compile(Pattern.quote(emojiList[i])), new DefaultGifEmoji(emojiGifs.get(i), emojiList[i]));
//	    }
//	    defaultGifEmojis = new DefaultGifEmoji[emojiList.length];
//	    for (int i = 0; i < emojiList.length; i++) {
//	      defaultGifEmojis[i] = new DefaultGifEmoji(emojiGifs.get(i), emojiList[i]);
//	    }
//	    defaultEmojiInited = true;
//	    handler.sendEmptyMessage(UNZIP_SUCCESS);
//	  }
//
//	  public Spannable getSpannableByEmoji(Context context, Emoji emoji) {
//	    SpannableString spannableString = new SpannableString(emoji.getEmojiText());
//	    ImageSpan imageSpan;
//	    Drawable drawable;
//	    if (drawableCacheMap.containsKey(emoji.getCacheKey())) {
//	      drawable = drawableCacheMap
//	          .get(emoji.getCacheKey());
//	      imageSpan = new EqualHeightSpan(drawable);
//	    } else {
//
//	      drawable = emoji.getDrawable(context);
//	      imageSpan = new EqualHeightSpan(drawable);
//	      drawableCacheMap
//	          .put(emoji.getCacheKey(), drawable);
//
//	    }
//	    spannableString
//	        .setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    return spannableString;
//	  }
//
//	  public Drawable getDrawableByEmoji(Context context, Emoji emoji) {
//	    Drawable drawable;
//	    if (drawableCacheMap.containsKey(emoji.getCacheKey())) {
//	      drawable = drawableCacheMap
//	          .get(emoji.getCacheKey());
//	    } else {
//	      drawable = emoji.getDrawable(context);
//	      drawableCacheMap
//	          .put(emoji.getCacheKey(), drawable);
//	    }
//	    return drawable;
//	  }
//
//
//	  public Spannable getSpannableByPattern(Context context, String text) {
//	    SpannableString spannableString = new SpannableString(text);
//	    for (Entry<Pattern, Emoji> entry : emoticons.entrySet()) {
//	      Matcher matcher = entry.getKey().matcher(text);
//	      while (matcher.find()) {
//	        ImageSpan imageSpan = null;
//	        Emoji emoji = entry.getValue();
//
//	        if (emoji instanceof DefaultGifEmoji) {
//	          imageSpan = getImageSpanByEmoji(context, emoji);
//	        }
////	        Object value = emoji.getRes();
////	        if (value instanceof String && !((String) value).startsWith("http")) {
////	          //本地路径
////	          File file = new File((String) value);
////	          if (file.exists() && !file.isDirectory()) {
////	            imageSpan = new ImageSpan(context, Uri.fromFile(file));
////	          }
////	        } else {
////	          imageSpan = new ImageSpan(context, (Integer) value);
////	        }
//	        if (imageSpan != null) {
//	          spannableString.setSpan(imageSpan,
//	              matcher.start(), matcher.end(),
//	              Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//	        }
//	      }
//	    }
//	    return spannableString;
//	  }
//
//	  private ImageSpan getImageSpanByEmoji(Context context, Emoji emoji) {
//	    ImageSpan imageSpan;
//	    Drawable gifDrawable;
//	    if (drawableCacheMap.containsKey(emoji.getCacheKey())) {
//	      gifDrawable = drawableCacheMap.get(emoji.getCacheKey());
//	      imageSpan = new EqualHeightSpan(gifDrawable);
//	    } else {
//	      gifDrawable = emoji.getDrawable(context);
//	      imageSpan = new EqualHeightSpan(gifDrawable);
//	      drawableCacheMap
//	          .put(emoji.getCacheKey(), gifDrawable);
//
//	    }
//	    return imageSpan;
//	  }
//
//
//	  private void onUnzipSuccess() {
//		LogUtils.logDebug(message);
//	    Log.i(TAG, "onUnzipSuccess: " + Thread.currentThread().getName());
//	    for (OnUnzipSuccessListener onUnzipSuccessListener : onUnzipSuccessListeners) {
//	      onUnzipSuccessListener.onUnzipSuccess(defaultGifEmojis);
//	    }
//	    onUnzipSuccessListeners.clear();
//	  }
//
//	  public void displayImage(ImageView imageView, Emoji emoji) {
//	    Glide.with(imageView)
//	        .load(emoji.getRes())
//	        .apply(new RequestOptions().placeholder(emoji.getDefaultResId()))
//	        .into(imageView);
//	  }
//
//
//	  public interface OnUnzipSuccessListener {
//
//	    void onUnzipSuccess(DefaultGifEmoji[] defaultGifEmojis);
//	  }
//}
