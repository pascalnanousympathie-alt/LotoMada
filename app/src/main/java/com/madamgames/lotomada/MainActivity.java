package com.madamgames.lotomada;

import android.app.Activity;import android.os.Bundle;import android.graphics.Color;import android.view.Gravity;import android.widget.*;import android.content.SharedPreferences;

public class MainActivity extends Activity{
 private LotoGame game;private TextView numbers;private SharedPreferences prefs; private CalmMusic music; private Button musicButton;
 @Override public void onCreate(Bundle b){super.onCreate(b);music=new CalmMusic(this);prefs=getSharedPreferences("loto",0);build();if(!restore())reset();}
 private TextView label(String text,int size){TextView v=new TextView(this);v.setText(text);v.setTextSize(size);v.setGravity(Gravity.CENTER);v.setPadding(0,10,0,10);return v;}
 private void build(){
  LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setGravity(Gravity.CENTER_HORIZONTAL);root.setPadding(20,22,20,16);
  TextView title=label("LotoMada · 1–90",27);title.setTextColor(Color.rgb(35,45,55));numbers=label("",18);
  Button ticket=new Button(this);ticket.setText("Nouveau ticket (5)");Button draw=new Button(this);draw.setText("Tirer 5 numéros");Button reset=new Button(this);reset.setText("Réinitialiser");
  root.addView(title);root.addView(numbers);root.addView(ticket);root.addView(draw);root.addView(reset);musicButton=new Button(this);musicButton.setText(musicLabel());musicButton.setOnClickListener(v->{music.toggle();musicButton.setText(musicLabel());});root.addView(musicButton);setContentView(root);
  ticket.setOnClickListener(v->{game.newTicket(5);persist();show();});
  draw.setOnClickListener(v->{try{game.draw(5);persist();show();}catch(Exception e){Toast.makeText(this,"Créez d'abord un ticket",Toast.LENGTH_SHORT).show();}});
  reset.setOnClickListener(v->{reset();persist();});
 }
 private void reset(){game=new LotoGame();show();}
 private boolean restore(){String s=prefs.getString("state","");if(s.isEmpty())return false;game=new LotoGame();if(game.loadState(s)){show();return true;}return false;}
 private void persist(){prefs.edit().putString("state",game.saveState()).apply();}
 private void show(){if(game.ticket().isEmpty())numbers.setText("Aucun ticket\nCréez un ticket pour commencer");else{String draw=game.draw().isEmpty()?"Aucun tirage":"Tirage: "+game.draw();numbers.setText("Ticket: "+game.ticket()+"\n"+draw+"\nCorrespondances: "+game.matches()+"/5");}}
 @Override protected void onStart(){super.onStart();if(music!=null)music.start();}
 @Override protected void onStop(){if(music!=null)music.stop();super.onStop();if(game!=null)persist();}
 private String musicLabel(){return music.enabled()?"🎵 Mozika: MISOKATRA":"🎵 Mozika: NOKATONA";}

}
