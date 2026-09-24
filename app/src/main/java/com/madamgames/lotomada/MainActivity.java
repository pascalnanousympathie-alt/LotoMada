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
  LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(20,18,20,24);root.setBackgroundColor(Color.rgb(247,246,253));scroll.addView(root);
  BrandUi.header(this,root,"LotoMada","Chance • 1 à 90 • Jeu local",Color.rgb(91,54,201));
  root.addView(BrandUi.badge(this,"TIRAGE HORS-LIGNE",Color.rgb(102,73,203)),new LinearLayout.LayoutParams(-1,44));
  LinearLayout hero=BrandUi.card(this,Color.rgb(231,226,248));hero.addView(BrandUi.text(this,"Votre ticket, votre chance",20,Color.rgb(39,31,77),true));hero.addView(BrandUi.text(this,"Créez 5 numéros, lancez un tirage puis consultez vos correspondances.",14,Color.rgb(93,84,119),false),new LinearLayout.LayoutParams(-1,70));root.addView(hero);
  LinearLayout row=new LinearLayout(this);row.setGravity(17);row.setPadding(0,12,0,4);
  Button ticket=BrandUi.button(this,"Créer un ticket",Color.rgb(78,48,172),Color.rgb(126,91,224));Button draw=BrandUi.button(this,"Tirer 5 numéros",Color.rgb(232,143,22),Color.rgb(255,190,52));row.addView(ticket,new LinearLayout.LayoutParams(0,58,1f));row.addView(draw,new LinearLayout.LayoutParams(0,58,1f));root.addView(row);
  Button reset=BrandUi.button(this,"Nouvelle session",Color.rgb(49,49,76),Color.rgb(82,74,105));root.addView(reset,new LinearLayout.LayoutParams(-1,54));
  LinearLayout state=BrandUi.card(this,Color.rgb(231,226,248));state.addView(BrandUi.text(this,"Résultat",15,Color.rgb(102,73,203),true));numbers=BrandUi.text(this,"",15,Color.rgb(70,84,102),false);numbers.setPadding(0,10,0,0);state.addView(numbers);root.addView(state);
  musicButton=BrandUi.button(this,"",Color.rgb(49,36,89),Color.rgb(82,67,126));musicButton.setOnClickListener(v->{music.toggle();musicButton.setText(musicLabel());});root.addView(musicButton,new LinearLayout.LayoutParams(-1,54));
  ticket.setOnClickListener(v->{game.newTicket(5);persist();show();});draw.setOnClickListener(v->{try{game.draw(5);persist();show();}catch(Exception e){Toast.makeText(this,"Créez d'abord un ticket",Toast.LENGTH_SHORT).show();}});reset.setOnClickListener(v->{reset();persist();});musicButton.setText(musicLabel());setContentView(scroll);
 }
 private void reset(){game=new LotoGame();show();}
 private boolean restore(){String s=prefs.getString("state","");if(s.isEmpty())return false;game=new LotoGame();if(game.loadState(s)){show();return true;}return false;}
 private void persist(){prefs.edit().putString("state",game.saveState()).apply();}
 private void show(){if(game.ticket().isEmpty())numbers.setText("Aucun ticket\nCréez un ticket pour commencer");else{String draw=game.draw().isEmpty()?"Aucun tirage":"Tirage : "+game.draw();numbers.setText("Ticket : "+game.ticket()+"\n"+draw+"\nCorrespondances : "+game.matches()+"/5");}}
 @Override protected void onStart(){super.onStart();if(music!=null)music.start();}
 @Override protected void onStop(){if(music!=null)music.stop();super.onStop();if(game!=null)persist();}
 private String musicLabel(){return music.enabled()?"Musique · ACTIVÉE":"Musique · DÉSACTIVÉE";}
}
