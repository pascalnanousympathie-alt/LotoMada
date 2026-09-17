package com.madamgames.lotomada;

import java.util.*;

public final class LotoGame {
  private final Random random;
  private final List<Integer> ticket = new ArrayList<>();
  private final List<Integer> draw = new ArrayList<>();
  public LotoGame(){this(new Random());}
  public LotoGame(Random r){random=r;}
  public List<Integer> newTicket(int count){
    if(count<1||count>90) throw new IllegalArgumentException("count");
    List<Integer> pool=new ArrayList<>(); for(int i=1;i<=90;i++) pool.add(i);
    Collections.shuffle(pool,random); ticket.clear(); ticket.addAll(pool.subList(0,count)); Collections.sort(ticket); return ticket();
  }
  public List<Integer> draw(int count){
    if(count<1||count>90) throw new IllegalArgumentException("count");
    List<Integer> pool=new ArrayList<>(); for(int i=1;i<=90;i++) pool.add(i);
    Collections.shuffle(pool,random); draw.clear(); draw.addAll(pool.subList(0,count)); Collections.sort(draw); return draw();
  }
  public int matches(){int n=0; for(Integer x:draw) if(ticket.contains(x)) n++; return n;}
  public List<Integer> ticket(){return new ArrayList<>(ticket);}
  public List<Integer> draw(){return new ArrayList<>(draw);}
}
