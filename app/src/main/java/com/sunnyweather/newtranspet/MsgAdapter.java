package com.sunnyweather.newtranspet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

//Recycler 适配器
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private ArrayList<Message> messageList;
    private Context mContext;
    private Boolean isUser;
    public ItemOnclick itemOnclick;
    public DatabaseReference mDatabase;

    public MsgAdapter(Context mContext, ArrayList<Message> messageList, Boolean isUser, DatabaseReference mDatabase) {
        this.messageList = messageList;
        this.mContext = mContext;
        this.isUser = isUser;
        this.mDatabase = mDatabase;
    }

    public void setItemOnclick(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        //layout集合
        ArrayList<LinearLayout> layouts;
        /****/

        TextView leftMsg;
        TextView rightMsg;
        TextView messageType;
        ImageView rightImg;
        ImageView leftImg;

        public ViewHolder(View view) {
            super(view);
            layouts = new ArrayList<>();
            leftLayout = view.findViewById(R.id.left_layout);
            messageType = view.findViewById(R.id.message_type);
            /****/
            layouts.add(leftLayout);
            /****/
            rightLayout = view.findViewById(R.id.right_layout);
            layouts.add(rightLayout);
            leftMsg = view.findViewById(R.id.left_msg);
            rightMsg = view.findViewById(R.id.right_msg);
            rightImg = view.findViewById(R.id.right_msg_img);
            leftImg = view.findViewById(R.id.left_msg_img);

            /****/
        }

        /****/
        public void setVisibility(LinearLayout layout) {
            for (LinearLayout layout1 : layouts) {
                if (layout1 == layout) {
                    layout1.setVisibility(View.VISIBLE);
                } else {
                    layout1.setVisibility(View.GONE);
                }
            }
        }
        /****/
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.msg_item, parent, false);

        return new ViewHolder(view);
    }

    /****/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messageList.get(position);
        //用户
        if (isUser) {
            holder.leftImg.setImageResource(R.drawable.kefu);
            holder.rightImg.setImageResource(R.drawable.sanny);
            if (message.getUser()) {
                holder.setVisibility(holder.rightLayout);
                holder.rightMsg.setText(message.getMessage());
                if (message.getType() == 0) {
                    holder.messageType.setText("已发送");
                } else {
                    holder.messageType.setText("已读");
                }
            } else {
                holder.setVisibility(holder.leftLayout);
                holder.leftMsg.setText(message.getMessage());
                if (message.getType()==0){
                    message.setType(1);
                    mDatabase.child("message").child(message.getMessageId()).setValue(message);
                }
            }
            //客服
        } else {
            holder.leftImg.setImageResource(R.drawable.sanny);
            holder.rightImg.setImageResource(R.drawable.kefu);
            if (!message.getUser()) {
                holder.setVisibility(holder.rightLayout);
                holder.rightMsg.setText(message.getMessage());
                if (message.getType() == 0) {
                    holder.messageType.setText("已发送");
                } else {
                    holder.messageType.setText("已读");
                }
            } else {
                holder.setVisibility(holder.leftLayout);
                holder.leftMsg.setText(message.getMessage());
                if (message.getType()==0){
                    message.setType(1);
                    mDatabase.child("message").child(message.getMessageId()).setValue(message);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public interface ItemOnclick {
        void imageOnClick(int position);
    }
}
