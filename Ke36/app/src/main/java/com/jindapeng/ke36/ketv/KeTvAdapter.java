package com.jindapeng.ke36.ketv;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.jindapeng.ke36.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/14.
 */
public class KeTvAdapter extends BaseAdapter {
    private Context context;
    private KeTvBean keTvBean;
    private boolean isPaused = false;
    private boolean isPlaying = false;

    public KeTvAdapter(Context context) {
        this.context = context;
    }

    public void setKeTvBean(KeTvBean keTvBean) {
        this.keTvBean = keTvBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return keTvBean == null ? 0 : keTvBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return keTvBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_ke_tv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            if (holder.videoSource.getVisibility() == View.VISIBLE) {
                holder.title.setVisibility(View.VISIBLE);
                holder.featureImg.setVisibility(View.VISIBLE);
                holder.videoSource.setVisibility(View.INVISIBLE);
            }
        }

        final String title = keTvBean.getData().getData().get(position).getTv().getTitle();
        String videoSource = keTvBean.getData().getData().get(position).getTv().getVideoSource();
        String imageUrl = keTvBean.getData().getData().get(position).getTv().getFeatureImg();

        holder.title.setText(title);
        holder.videoSource.setVideoURI(Uri.parse(videoSource));
        Picasso.with(context).load(imageUrl).into(holder.featureImg);

        MediaController controller = new MediaController(context, false);
        holder.videoSource.setVisibility(View.VISIBLE);
        controller.setAnchorView(holder.videoSource);
        controller.setMediaPlayer(holder.videoSource);
        holder.videoSource.setMediaController(controller);
        holder.videoSource.requestFocus();
        holder.progressBar.setVisibility(View.VISIBLE);

        holder.featureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaused && isPlaying) {
                    holder.videoSource.start();
                    holder.playBtn.setVisibility(View.INVISIBLE);
                    holder.featureImg.setVisibility(View.INVISIBLE);
                    holder.title.setVisibility(View.INVISIBLE);
                    holder.progressBar.setVisibility(View.GONE);
                    System.out.println("播放视频");
                }else {
                    holder.videoSource.pause();
                    holder.playBtn.setVisibility(View.VISIBLE);
                    holder.featureImg.setVisibility(View.VISIBLE);
                    holder.title.setVisibility(View.VISIBLE);
                    holder.progressBar.setVisibility(View.GONE);
                    System.out.println("暂停视频");
                }
                notifyDataSetChanged();

            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView featureImg, playBtn;
        TextView title;
        VideoView videoSource;
        ProgressBar progressBar;

        public ViewHolder(View view) {
            featureImg = (ImageView) view.findViewById(R.id.ke_tv_img);
            title = (TextView) view.findViewById(R.id.ke_tv_title);
            videoSource = (VideoView) view.findViewById(R.id.video_view);
            playBtn = (ImageView) view.findViewById(R.id.news_ke_tv_playBtn);
            progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        }
    }
}

