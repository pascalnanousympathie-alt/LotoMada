package com.madamgames.lotomada;
import java.util.*;
public final class LotoGame {
  private final Random random; private final List<Integer> ticket=new ArrayList<>(),draw=new ArrayList<>();
  public LotoGame(){this(new Random());} public LotoGame(Random r){random=Objects.requireNonNull(r);}
  public List<Integer> newTicket(int count){validate(count);List<Integer>p=pool();Collections.shuffle(p,random);ticket.clear();ticket.addAll(p.subList(0,count));Collections.sort(ticket);draw.clear();return ticket();}
  public List<Integer> draw(int count){validate(count);if(ticket.isEmpty())throw new IllegalStateException("ticket required");List<Integer>p=pool();Collections.shuffle(p,random);draw.clear();draw.addAll(p.subList(0,count));Collections.sort(draw);return draw();}
  public int matches(){int n=0;for(Integer x:draw)if(ticket.contains(x))n++;return n;} public List<Integer>ticket(){return new ArrayList<>(ticket);} public List<Integer>draw(){return new ArrayList<>(draw);}
  private static List<Integer>pool(){List<Integer>p=new ArrayList<>();for(int i=1;i<=90;i++)p.add(i);return p;} private static void validate(int n){if(n<1||n>90)throw new IllegalArgumentException("count");}
}
