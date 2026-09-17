package com.madamgames.lotomada;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
  private TextView numbers;
  private final List<Integer> draw = new ArrayList<>();

  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    LinearLayout root = new LinearLayout(this);
    root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER);
    root.setPadding(32, 32, 32, 32);
    TextView title = new TextView(this); title.setText("LotoMada");
    title.setTextSize(30); title.setTextColor(Color.DKGRAY);
    numbers = new TextView(this); numbers.setTextSize(24); numbers.setGravity(Gravity.CENTER);
    Button drawButton = new Button(this); drawButton.setText("Tirer 5 numéros");
    root.addView(title); root.addView(numbers); root.addView(drawButton);
    setContentView(root); showEmpty();
    drawButton.setOnClickListener(v -> drawNumbers());
  }

  private void showEmpty() { numbers.setText("Prêt pour le tirage\n1–90"); }

  private void drawNumbers() {
    List<Integer> pool = new ArrayList<>();
    for (int i = 1; i <= 90; i++) pool.add(i);
    Collections.shuffle(pool); draw.clear(); draw.addAll(pool.subList(0, 5));
    Collections.sort(draw); numbers.setText("Numéros gagnants\n" + draw.toString());
  }
}
