package com.architter.widgets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.architter.connection.ConnectionManager;
import com.example.architter.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class IdeasScroll extends RelativeLayout implements ScrollViewListener {
	ObservableScrollView scroll;
	String tags = "";
	String search = "";
	int idUser = 0;
	int page = 1;
	private OnClickListener listener;
	public IdeasScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public IdeasScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public IdeasScroll(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		inflate(getContext(), R.layout.two_column_scroll, this);
		init();
	}

	private void init() {
		RelativeLayout loading = (RelativeLayout) this.findViewById(R.id.loadingView);
		AnimatedView loading_gif = new AnimatedView(loading.getContext());
		loading_gif.loadDrawable(R.drawable.loading_architter);
		loading.addView(loading_gif);

		scroll = (ObservableScrollView) this.findViewById(R.id.ideasScroll);
		scroll.setScrollViewListener(this);
	}
	public void loadIdeas(String tags) {
		this.tags = tags;
		loadIdeas();
	}
	public void searchIdeas(String search) {
		this.search = search;
		loadIdeas();
	}
	public void loadUserIdeas(String tags, int idUser) {
		this.tags = tags;
		this.idUser = idUser;
		loadUserIdeas();
	}
	public void searchUserIdeas(String search, int idUser) {
		this.search = search;
		this.idUser = idUser;
		loadUserIdeas();
	}
	public void loadIdeas(String tags, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
			this.findViewById(R.id.loadingView).setVisibility(VISIBLE);
		}
		loadIdeas(tags);
	}
	public void searchIdeas(String search, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
		}
		searchIdeas(search);
	}
	public void loadUserIdeas(String tags, int idUser, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
		}
		loadUserIdeas(tags, idUser);
	}
	public void searchUserIdeas(String search, int idUser, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
		}
		searchUserIdeas(search, idUser);
	}

	public void loadIdeas() {
		RequestParams params = new RequestParams();
		params.put("page", ""+page);
		params.put("tags", tags);
		params.put("search", search);
		ConnectionManager.getIdeas(params, new  JsonHttpResponseHandler() {
			@Override
			public void onFailure(Throwable arg0) {
				System.out.println(":(");
			}
			@Override
			public void onSuccess(JSONArray ideas) {
				System.out.println("loaded");
				findViewById(R.id.loadingView).setVisibility(GONE);
				loadElements(ideas);
			}
		});
	}

	public void loadUserIdeas() {
		RequestParams params = new RequestParams();
		params.put("page", ""+page);
		params.put("tags", tags);
		params.put("search", search);
		ConnectionManager.getUserIdeas(params, this.idUser, new  JsonHttpResponseHandler() {
			@Override
			public void onFailure(Throwable arg0) {
				System.out.println(":(");
			}
			@Override
			public void onSuccess(JSONArray ideas) {
				System.out.println("loaded");
				loadElements(ideas);
			}
		});
	}


	public void loadElements(JSONArray ideas){
		LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
		LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
		IdeaWidget idea = null;
		IdeaWidget cont = new IdeaWidget(getContext());
		int column = 1;
		if(ideas.length() == 0) {
			this.findViewById(R.id.notFound).setVisibility(VISIBLE);
		} else {
			this.findViewById(R.id.notFound).setVisibility(GONE);
			for (int i = 0; i < ideas.length(); i++) {
				try {
					JSONObject invention = ideas.getJSONObject(i);
					String img = invention.getString("img");
					if(!img.startsWith("./ideaboardImages/")) {
						img = "./ideaboardImages/" + img;
					};
					LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					idea = (IdeaWidget) inflater.inflate(R.layout.idea_component, cont, false);
					img = ConnectionManager.ASSET_BASE + img;
					String description = invention.getString("descr");
					String user = invention.getString("iduser");
					int idea_id = invention.getInt("id");
					idea.setImage(img);
					idea.setDescription(description+user);
					idea.setListener(listener);
					idea.setIdeaId(idea_id);
					switch (column) {
					case 1:
						column1.addView(idea);
						column++;
						break;
					case 2:
						column2.addView(idea);
						column++;
					default:
						column = 1;
						break;
					}
					page++;
				} catch (JSONException e) {
					e.printStackTrace();
					break;
				}

			}
		}
	}

	public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
			int oldx, int oldy) {
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear1);
	    if(linearLayout.getMeasuredHeight() <= scrollView.getScrollY() +
	           scrollView.getHeight()) {
	        loadIdeas();
	    }
	    else {
	        //do nothing
	    }
	}

	public void setListener(OnClickListener listener) {
		this.listener = listener;
	}
}
