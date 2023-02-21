package com.sunnyweather.newtranspet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class activity_personnel extends AppCompatActivity {
    private ImageView perssonnel_turn;
    private DatabaseReference mDatabase;
    private RecyclerView msgRecycleView;
    private ArrayList<Message> dataList;
    private MsgAdapter adapter;
    private LinearLayoutManager layoutManager;
    private NestedScrollView scrollView;
    private EditText editSearch;
    private TextView personneltitle;
    private boolean isUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel);

        perssonnel_turn = findViewById(R.id.personnelturn);
        msgRecycleView = findViewById(R.id.msg_recycle_view);
        editSearch = findViewById(R.id.edit_search);
        personneltitle = findViewById(R.id.personneltitle);
        scrollView = findViewById(R.id.scrollView);
        isUser = getIntent().getBooleanExtra("isUser", false);
        if (!isUser) {
            personneltitle.setText("客服执勤");
        }
        perssonnel_turn.setOnClickListener(view -> {
            finish();
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        initRecycler();
        // 点击软键盘回车键发送聊天信息
        editSearch.setOnEditorActionListener((v, actionId, event) -> {
            //按下发送
            if (editSearch.getText() != null &&
                    !editSearch.getText().toString().isEmpty() &&
                    actionId == EditorInfo.IME_ACTION_SEND) {
                //保存消息
                Message message = new Message();
                message.setMessage(editSearch.getText().toString());
                message.setUser(isUser);
                message.setType(0);
                message.setTime(System.currentTimeMillis());
                String s = String.valueOf(System.currentTimeMillis());
                message.setMessageId(s);
                mDatabase.child("message").child(s).setValue(message);
                editSearch.getText().clear();
            }
            //返回true，保留软键盘。false，隐藏软键盘
            return true;
        });
        DatabaseReference myRef = mDatabase.getRef();
        //limit to the last data
        final Query lastSale = myRef.limitToLast(1);
        //add on child event listener
        lastSale.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                dataList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    dataList.add(postSnapshot.getValue(Message.class));
                }
                adapter.notifyDataSetChanged();
                scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                dataList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    dataList.add(postSnapshot.getValue(Message.class));
                }
                adapter.notifyDataSetChanged();
                scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("addData_", "onChildRemoved called");

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("addData_", "onChildMoved called");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("addData_", "onCancelled called");
            }
        });
    }

    private void initRecycler() {
        dataList = new ArrayList<>();
        adapter = new MsgAdapter(this, dataList, isUser,mDatabase);
        msgRecycleView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        msgRecycleView.setLayoutManager(layoutManager);
    }
}