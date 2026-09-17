package com.madamgames.lotomada;

import android.app.Activity; import android.os.Bundle; import android.graphics.Color; import android.view.Gravity; import android.widget.*;

public class MainActivity extends Activity {
  private LotoGame game; private TextView numbers;
  @Override public void onCreate(Bundle b){super.onCreate(b); build(); reset();}
  private void build(){
    LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER); root.setPadding(25,30,25,25);
    TextView title=new TextView(this); title.setText("LotoMada · 1–90"); title.setTextSize(27); title.setTextColor(Color.DKGRAY);
    numbers=new TextView(this); numbers.setTextSize(19); numbers.setGravity(Gravity.CENTER); numbers.setPadding(0,20,0,20);
    Button ticket=new Button(this); ticket.setText("Nouveau ticket (5)"); Button draw=new Button(this); draw.setText("Tirer 5 numéros"); Button reset=new Button(this); reset.setText("Réinitialiser");
    root.addView(title); root.addView(numbers); root.addView(ticket); root.addView(draw); root.addView(reset); setContentView(root);
    ticket.setOnClickListener(v->{game.newTicket(5); showTicket();}); draw.setOnClickListener(v->{try{game.draw(5); showDraw();}catch(Exception e){Toast.makeText(this,"Créez d'abord un ticket",Toast.LENGTH_SHORT).show();}}); reset.setOnClickListener(v->reset());
  }
  private void reset(){game=new LotoGame(); numbers.setText("Aucun ticket");}
  private void showTicket(){numbers.setText("Ticket: "+game.ticket()+"\nPrêt pour le tirage");}
  private void showDraw(){numbers.setText("Ticket: "+game.ticket()+"\nTirage: "+game.draw()+"\nCorrespondances: "+game.matches()+"/5");}
}
