package com.madamgames.lotomada;

import android.app.Activity; import android.os.Bundle; import android.graphics.Color;
import android.view.Gravity; import android.widget.*;

public class MainActivity extends Activity {
  private LotoGame game; private TextView numbers;
  @Override public void onCreate(Bundle b){super.onCreate(b); build(); game=new LotoGame();}
  private void build(){
    LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER); root.setPadding(30,30,30,30);
    TextView title=new TextView(this); title.setText("LotoMada · 1–90"); title.setTextSize(28); title.setTextColor(Color.DKGRAY);
    numbers=new TextView(this); numbers.setTextSize(20); numbers.setGravity(Gravity.CENTER); numbers.setPadding(0,25,0,25);
    Button ticket=new Button(this); ticket.setText("Créer ticket (5)"); Button draw=new Button(this); draw.setText("Tirer 5 numéros");
    root.addView(title); root.addView(numbers); root.addView(ticket); root.addView(draw); setContentView(root); numbers.setText("Ticket prêt");
    ticket.setOnClickListener(v->numbers.setText("Ticket: "+game.newTicket(5)));
    draw.setOnClickListener(v->{game.draw(5); numbers.setText("Tirage: "+game.draw()+"\nCorrespondances: "+game.matches()+"/5");});
  }
}
