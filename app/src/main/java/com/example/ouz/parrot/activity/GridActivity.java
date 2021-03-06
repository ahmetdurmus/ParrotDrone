package com.example.ouz.parrot.activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ouz.parrot.R;
import com.example.ouz.parrot.drone.MiniDrone;
import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_STATE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_TYPE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM;
import com.parrot.arsdk.arcontroller.ARCONTROLLER_DEVICE_STATE_ENUM;
import com.parrot.arsdk.arcontroller.ARControllerCodec;
import com.parrot.arsdk.arcontroller.ARFrame;
import com.parrot.arsdk.ardiscovery.ARDiscoveryDeviceService;

import java.io.Console;

import static com.example.ouz.parrot.R.drawable.bol31;
import static com.example.ouz.parrot.activity.DeviceListActivity.EXTRA_DEVICE_SERVICE;

public class GridActivity extends AppCompatActivity {

    AlertDialog alertDialog;
    TextView txtBatarya;
    Button yukari,asagi,sol,sag,btnAcil,uc,btndon;
    Dialog yukaripopup,asagipopup,solpopup,sagpopup,donmepopup;

    public static int kontrol =0;
    public ARDiscoveryDeviceService service;
    ImageView imageview;


    Thread thread,thread2;

    public MiniDrone mMiniDrone;

    private ProgressDialog mConnectionProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        Log.d("Grid","Gride girdi ");
        Intent intent = getIntent();
        Log.d("Grid","intente gitti ");
        service = PuzzleActivity.service;
        mMiniDrone = PuzzleActivity.mMiniDrone;
        mMiniDrone.addListener(mMiniDroneListener);

        imageview= findViewById(R.id.gorev3);
        txtBatarya = (TextView) findViewById(R.id.txtBatarya);
        yukari=findViewById(R.id.yukari);
        asagi=findViewById(R.id.asagi);
        sol=findViewById(R.id.sol);
        sag=findViewById(R.id.sag);
        btnAcil=findViewById(R.id.btnAcil);
        uc=findViewById(R.id.uc);
        btndon=findViewById(R.id.don3);

        yukaripopup=new Dialog(this);
        asagipopup=new Dialog(this);
        solpopup=new Dialog(this);
        sagpopup=new Dialog(this);
        donmepopup=new Dialog(this);


        btndon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMiniDrone.takeOff();
                GeriDon();

            }
        });

        btnAcil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (mMiniDrone.getFlyingState()) {
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_LANDED:
                        btnAcil.setEnabled(true);
                        mMiniDrone.takeOff();
                        break;
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_FLYING:
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_HOVERING:
                        btnAcil.setEnabled(true);
                        mMiniDrone.land();
                        break;
                    default:
                }
            }
        });

        yukari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yukaripopup.setContentView(R.layout.yukaripopup);

                Button btntamam;
                final EditText popupgiris;
                popupgiris=yukaripopup.findViewById(R.id.editText);
                btntamam=yukaripopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(popupgiris.getText().toString().equals("5") && kontrol==0)
                        {
                            //imageview.setBackgroundResource(R.drawable.bol31);
                            imageview.setImageResource(R.drawable.bol31);
                            yukaripopup.dismiss();
                            kontrol=1;
                        }
                        else if(popupgiris.getText().toString().equals("4") && kontrol==2)
                        {
                            imageview.setImageResource(R.drawable.bol33);
                            yukaripopup.dismiss();
                            kontrol=3;
                        }
                        else if (popupgiris.getText().toString().equals("6") && kontrol==4)
                        {
                            imageview.setImageResource(R.drawable.bol35);
                            yukaripopup.dismiss();
                            kontrol=5;
                        }
                        else {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                yukaripopup.show();
            }
        });

        asagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asagipopup.setContentView(R.layout.asagipopup);

                Button btntamam;
                final EditText popupgiris;
                popupgiris=asagipopup.findViewById(R.id.editText);
                btntamam=asagipopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        asagipopup.dismiss();
                    }
                });
                asagipopup.show();
            }
        });

        sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solpopup.setContentView(R.layout.solpopup);

                Button btntamam;
                final EditText popupgiris;
                popupgiris=solpopup.findViewById(R.id.editText);
                btntamam=solpopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String log=popupgiris.getText().toString();
                        Log.w(log,"asdsd");
                        if(popupgiris.getText().toString().equals("2") && kontrol==3)
                        {
                            imageview.setImageResource(R.drawable.bol34);
                            solpopup.dismiss();
                            kontrol=4;
                        }
                        else {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                solpopup.show();
            }
        });

        sag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sagpopup.setContentView(R.layout.sagpopup);

                Button btntamam;
                final EditText popupgiris;
                popupgiris=sagpopup.findViewById(R.id.editText);
                btntamam=sagpopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(popupgiris.getText().toString().equals("4") && kontrol==1)
                        {
                            imageview.setImageResource(R.drawable.bol32);
                            sagpopup.dismiss();
                            kontrol=2;
                        }
                        else if (popupgiris.getText().toString().equals("5") && kontrol==5)
                        {
                            imageview.setImageResource(R.drawable.bol36);
                            uc.setVisibility(View.VISIBLE);
                            sagpopup.dismiss();
                            kontrol=6;
                        }
                        else
                        {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                sagpopup.show();
            }
        });

        uc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMiniDrone.takeOff();
                Uc();

            }

        });

    }

    public void GeriDon(){
        thread2 = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized (this){

                        wait(500);
                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) -35); //ileri
                        sleep(4000);
                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        wait(1500);

                        mMiniDrone.land();
                    }
                }
                catch (Exception e){
                    Log.d("hata","xd");
                }finally {
                    mMiniDrone.land();
                    thread2.interrupt();

                }
            }
        };
        thread2.start();
        FinishMission();
    }

    public void Uc(){
        thread = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized (this){

                        wait(1500);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //ileri
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        wait(750);
                        mMiniDrone.setYaw((byte) 45); //sağ dön
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //sağa doğru ilerle
                        sleep(1000);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setYaw((byte) -45); //sola dön
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); // ilerle
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) -45); //sola dönme
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35); //ilerle
                        sleep(750);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) 45); //sağa  dönme
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35);
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) 45); //sağa  dönme
                        sleep(1500);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 35);
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.flip(ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM.BACK);

                        wait(1500);

                        mMiniDrone.land();
                    }
                }
                catch (Exception e){
                    Log.d("hata","xd");
                }finally {
                    mMiniDrone.land();
                    thread.interrupt();
                }
            }
        };
        thread.start();
        CDAlert();
    }

    //Countdown alert;
    public void CDAlert(){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Tebrikler Doğru Kombinasyon!");
        alertDialog.setMessage("Lütfen bekleyiniz. 00:24");
        alertDialog.setCancelable(false);
        WindowManager.LayoutParams wmlp = alertDialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.x = 250;   //x position
        wmlp.y = 2000;   //y position
        alertDialog.show();


        new CountDownTimer(24000, 1000) {//24
            @Override
            public void onTick(long millisUntilFinished) {
                alertDialog.setMessage("Lütfen bekleyiniz. 00:"+ (millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                alertDialog.cancel();
                uc.setVisibility(View.INVISIBLE);
                uc.setWidth(0);
                btndon.setWidth(250);
                btndon.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void FinishMission(){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("MISSION COMPLETED");
        alertDialog.setMessage("Lütfen bekleyiniz. 00:15");
        alertDialog.setCancelable(false);
        WindowManager.LayoutParams wmlp = alertDialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.x = 220;   //x position
        wmlp.y = 2000;   //y position
        alertDialog.show();


        new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                alertDialog.setMessage("Lütfen bekleyiniz. 00:"+ (millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                alertDialog.cancel();

                PuzzleActivity.mMiniDrone.disconnect();
            }
        }.start();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // show a loading view while the minidrone is connecting
        if ((mMiniDrone != null) && !(ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING.equals(mMiniDrone.getConnectionState())))
        {
            mConnectionProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            mConnectionProgressDialog.setIndeterminate(true);
            mConnectionProgressDialog.setMessage("Bağlanmaya çalışıyor ...");
            mConnectionProgressDialog.setCancelable(false);
            mConnectionProgressDialog.show();

            // if the connection to the MiniDrone fails, finish the activity
            if (!mMiniDrone.connect()) {
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
         /*   if (mMiniDrone != null)
        {
            mConnectionProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            mConnectionProgressDialog.setIndeterminate(true);
            mConnectionProgressDialog.setMessage("Bağlantı Kesiliyor ...");
            mConnectionProgressDialog.setCancelable(false);
            mConnectionProgressDialog.show();

            if (!mMiniDrone.disconnect()) {
                PuzzleActivity.mMiniDrone.disconnect();
            }

        } else {
            finish();
        }*/
    }

    @Override
    public void onDestroy()
    {
        mMiniDrone.dispose();
        super.onDestroy();
    }

    private final MiniDrone.Listener mMiniDroneListener = new MiniDrone.Listener() {
        @Override
        public void onDroneConnectionChanged(ARCONTROLLER_DEVICE_STATE_ENUM state) {
            switch (state)
            {
                case ARCONTROLLER_DEVICE_STATE_RUNNING:
                    mConnectionProgressDialog.dismiss();
                    btnAcil.setEnabled(true);
                    break;

                case ARCONTROLLER_DEVICE_STATE_STOPPED:
                    // if the deviceController is stopped, go back to the previous activity
                    //mConnectionProgressDialog.dismiss();
                    btnAcil.setEnabled(true);

                    Intent intent = new Intent(GridActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;

                default:
                    break;
            }
        }

        @Override
        public void onBatteryChargeChanged(int batteryPercentage) {
            txtBatarya.setText(String.format("%d%%", batteryPercentage));
        }

        @Override
        public void onPilotingStateChanged(ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM state) {
            switch (state) {
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_LANDED:
                    btnAcil.setText("Kalk");
                    btnAcil.setEnabled(true);
                    break;
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_FLYING:
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_HOVERING:
                    btnAcil.setEnabled(true);
                    btnAcil.setText("İn");
                    break;
                default:
                    btnAcil.setEnabled(false);
            }
        }

        @Override
        public void onPictureTaken(ARCOMMANDS_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM error) {

        }

        @Override
        public void configureDecoder(ARControllerCodec codec) {

        }

        @Override
        public void onFrameReceived(ARFrame frame) {

        }

        @Override
        public void onMatchingMediasFound(int nbMedias) {

        }

        @Override
        public void onDownloadProgressed(String mediaName, int progress) {

        }

        @Override
        public void onDownloadComplete(String mediaName) {

        }


        //ARAYÜZE KENDİ TAKLALARIMIZI KOYMAK İÇİN BURAYA YAZMAMIZ LAZIM
        @Override
        public void onAnimationTypeChanged(ARCOMMANDS_ANIMATION_TYPE_ENUM type, byte percent) {

        }

        @Override
        public void onAnimationStateChanged(ARCOMMANDS_ANIMATION_STATE_ENUM state) {

        }
    };

    //Geri Butonu
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

        }
        return super.onKeyDown(keyCode, event);
    }
}