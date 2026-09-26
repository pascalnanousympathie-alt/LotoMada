package com.madamgames.lotomada;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.*;
import android.content.SharedPreferences;
public class MainActivity extends Activity{
 private LotoGame game;private TextView numbers;private SharedPreferences prefs;private CalmMusic music;private Button musicButton;
 @Override public void onCreate(Bundle b){super.onCreate(b);music=new CalmMusic(this);prefs=getSharedPreferences("loto",0);build();if(!restore())reset();}
 private void build(){
  ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);
  LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(BrandUi.dp(this,18),BrandUi.dp(this,16),BrandUi.dp(this,18),BrandUi.dp(this,22));root.setBackgroundColor(Color.rgb(7,19,38));scroll.addView(root);
  BrandUi.header(this,root,"LotoMada","Loto 1–90 • fluide • hors-ligne",Color.rgb(91,54,201));
  root.addView(BrandUi.hero(this,"Loto premium","Un ticket. Un tirage. Vos résultats.",Color.rgb(124,92,255),Color.rgb(45,29,97)),new LinearLayout.LayoutParams(-1,BrandUi.dp(this,104)));
  LinearLayout actions=new LinearLayout(this);actions.setPadding(0,12,0,6);actions.setGravity(17);
  Button ticket=BrandUi.button(this,"Créer mon ticket",Color.rgb(78,48,172),Color.rgb(126,91,224));Button draw=BrandUi.button(this,"Tirer 5 numéros",Color.rgb(216,132,20),Color.rgb(255,184,48));actions.addView(ticket,new LinearLayout.LayoutParams(0,BrandUi.dp(this,56),1f));actions.addView(draw,new LinearLayout.LayoutParams(0,BrandUi.dp(this,56),1f));root.addView(actions);
  Button reset=BrandUi.button(this,"Nouvelle partie",Color.rgb(42,45,58),Color.rgb(78,82,97));root.addView(reset,new LinearLayout.LayoutParams(-1,BrandUi.dp(this,52)));
  LinearLayout state=BrandUi.card(this,Color.rgb(224,217,245));state.addView(BrandUi.text(this,"Résultats",14,Color.rgb(102,73,203),true));numbers=BrandUi.text(this,"",14.5f,Color.rgb(55,65,81),false);numbers.setPadding(0,8,0,0);state.addView(numbers);root.addView(state,new LinearLayout.LayoutParams(-1,-2){});
  musicButton=BrandUi.button(this,"♫  Son • ON",Color.rgb(49,36,89),Color.rgb(82,67,126));root.addView(musicButton,new LinearLayout.LayoutParams(-1,BrandUi.dp(this,50)));
  ticket.setOnClickListener(v->{game.newTicket(5);persist();show();});draw.setOnClickListener(v->{try{game.draw(5);persist();show();}catch(Exception e){Toast.makeText(this,"Créez d'abord un ticket",Toast.LENGTH_SHORT).show();}});reset.setOnClickListener(v->{reset();persist();});musicButton.setOnClickListener(v->{music.toggle();musicButton.setText(musicLabel());});setContentView(scroll);
 }
 private void reset(){game=new LotoGame();show();}
 private boolean restore(){String s=prefs.getString("state","");if(s.isEmpty())return false;game=new LotoGame();if(game.loadState(s)){show();return true;}return false;}
 private void persist(){prefs.edit().putString("state",game.saveState()).apply();}
 private void show(){if(game.ticket().isEmpty())numbers.setText("Aucun ticket");else{String draw=game.draw().isEmpty()?"—":"Tirage : "+game.draw();numbers.setText("Ticket : "+game.ticket()+"\n"+draw+"\nCorrespondances : "+game.matches()+"/5");}}
 @Override protected void onStart(){super.onStart();if(music!=null)music.start();}
 @Override protected void onStop(){if(music!=null)music.stop();super.onStop();if(game!=null)persist();}
 private String musicLabel(){return music.enabled()?"Son • ON":"Son • OFF";}
}