package com.jindapeng.ke36.ketv;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private int currentIndex = -1;
    private int playPosition = -1;
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
        return keTvBean.getData().getPageSize();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final int mPosition = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_ke_tv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String title = keTvBean.getData().getData().get(position).getTv().getTitle();
        final String videoSource = keTvBean.getData().getData().get(position).getTv().getVideoSource();
        String imageUrl = keTvBean.getData().getData().get(position).getTv().getFeatureImg();
        holder.title.setText(title);
        holder.videoSource.setVideoURI(Uri.parse(videoSource));
        Picasso.with(context).load(imageUrl).into(holder.featureImg);
        holder.title.setVisibility(View.VISIBLE);
        holder.playBtn.setVisibility(View.VISIBLE);
        holder.featureImg.setVisibility(View.VISIBLE);

        MediaController controller = new MediaController(context, false);

        if(currentIndex == position){
            Log.d("3",currentIndex+"currentIndex == position");
            holder.playBtn.setVisibility(View.INVISIBLE);
            holder.featureImg.setVisibility(View.INVISIBLE);
            holder.title.setVisibility(View.INVISIBLE);

            if(isPlaying || playPosition==-1){
                if(holder.videoSource!=null){
                    Log.d("这段不让播了",holder.videoSource+"mVideoView!=null");
                    holder.videoSource.setVisibility(View.GONE);
                    holder.videoSource.stopPlayback();
                    holder.progressBar.setVisibility(View.GONE);
                }
            }

            holder.videoSource.setVisibility(View.VISIBLE);
            controller.setAnchorView(holder.videoSource);
            controller.setMediaPlayer(holder.videoSource);
            holder.videoSource.setMediaController(controller);
            holder.videoSource.requestFocus();
            holder.progressBar.setVisibility(View.VISIBLE);


            holder.videoSource.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(holder.videoSource!=null){
                        holder.videoSource.seekTo(0);
                        holder.videoSource.stopPlayback();
                        currentIndex=-1;
                        isPaused=false;
                        isPlaying=false;
                        holder.progressBar.setVisibility(View.GONE);
                        notifyDataSetChanged();
                    }
                }
            });
            holder.videoSource.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    holder.progressBar.setVisibility(View.GONE);
                    holder.videoSource.start();
                }
            });

        }else{
            holder.playBtn.setVisibility(View.VISIBLE);
            holder.featureImg.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.VISIBLE);
            holder.progressBar.setVisibility(View.GONE);
            Log.d("这段不播", "++___++"+holder.progressBar);
        }

        holder.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = position;
                playPosition=-1;
                if(playPosition>0 && isPaused){
                    isPaused=false;
                    isPlaying=true;
                    holder.videoSource.start();
                    holder.progressBar.setVisibility(View.GONE);
                }else{
                    isPaused=false;
                    isPlaying=true;
                    holder.videoSource.setVideoURI(Uri.parse(videoSource));
                    System.out.println("播放新的视频");
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

