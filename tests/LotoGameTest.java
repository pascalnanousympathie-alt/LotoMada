package com.madamgames.lotomada;
public final class LotoGameTest{
 static void ok(boolean x,String m){if(!x)throw new AssertionError(m);}
 public static void main(String[]a){
  LotoGame g=new LotoGame(new java.util.Random(7));boolean blocked=false;try{g.draw(5);}catch(IllegalStateException e){blocked=true;}ok(blocked,"draw requires ticket");
  g.newTicket(5);g.draw(5);ok(g.ticket().size()==5,"ticket size");ok(g.draw().size()==5,"draw size");ok(new java.util.HashSet<>(g.ticket()).size()==5,"unique ticket");ok(new java.util.HashSet<>(g.draw()).size()==5,"unique draw");ok(g.matches()>=0&&g.matches()<=5,"match range");
  String saved=g.saveState();LotoGame r=new LotoGame(new java.util.Random(9));ok(r.loadState(saved),"state restores");ok(r.ticket().equals(g.ticket()),"ticket restored");ok(r.draw().equals(g.draw()),"draw restored");
  boolean invalid=false;try{g.newTicket(0);}catch(IllegalArgumentException e){invalid=true;}ok(invalid,"invalid count blocked");ok(!g.isComplete(),"incomplete draw not win");System.out.println("LOTO_TEST_PASS");
 }
}
