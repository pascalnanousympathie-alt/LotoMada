package com.madamgames.lotomada;
import java.util.*;
public final class LotoGame{
 private final Random random;private final List<Integer>ticket=new ArrayList<>(),draw=new ArrayList<>();
 public LotoGame(){this(new Random());}public LotoGame(Random r){random=Objects.requireNonNull(r);}
 public List<Integer>newTicket(int count){validate(count);List<Integer>p=pool();Collections.shuffle(p,random);ticket.clear();ticket.addAll(p.subList(0,count));Collections.sort(ticket);draw.clear();return ticket();}
 public List<Integer>draw(int count){validate(count);if(ticket.isEmpty())throw new IllegalStateException("ticket required");List<Integer>p=pool();Collections.shuffle(p,random);draw.clear();draw.addAll(p.subList(0,count));Collections.sort(draw);return draw();}
 public int matches(){int n=0;for(Integer x:draw)if(ticket.contains(x))n++;return n;}public List<Integer>ticket(){return new ArrayList<>(ticket);}public List<Integer>draw(){return new ArrayList<>(draw);}
 private static List<Integer>pool(){List<Integer>p=new ArrayList<>();for(int i=1;i<=90;i++)p.add(i);return p;}private static void validate(int n){if(n<1||n>90)throw new IllegalArgumentException("count");}
 public boolean isComplete(){return !ticket.isEmpty()&&matches()==ticket.size();}
 public String saveState(){return join(ticket)+";"+join(draw);}public boolean loadState(String state){try{String[]p=state.split(";",-1);if(p.length!=2)return false;ticket.clear();ticket.addAll(parse(p[0]));draw.clear();draw.addAll(parse(p[1]));for(Integer n:ticket)if(n<1||n>90)return false;for(Integer n:draw)if(n<1||n>90)return false;return ticket.size()<=90&&draw.size()<=90&&unique(ticket)&&unique(draw);}catch(Exception e){return false;}}
 private static String join(List<Integer>v){StringBuilder s=new StringBuilder();for(int i=0;i<v.size();i++){if(i>0)s.append(',');s.append(v.get(i));}return s.toString();}private static List<Integer>parse(String s){List<Integer>v=new ArrayList<>();if(s.isEmpty())return v;for(String x:s.split(","))v.add(Integer.parseInt(x));return v;}private static boolean unique(List<Integer>v){return new HashSet<>(v).size()==v.size();}
}
